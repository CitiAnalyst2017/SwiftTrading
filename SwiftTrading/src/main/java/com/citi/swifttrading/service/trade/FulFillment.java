package com.citi.swifttrading.service.trade;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.daoImpl.SecurityDaoImpl;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;
import com.citi.swifttrading.generator.OrderBook;
import com.citi.swifttrading.generator.OrderBookItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FulFillment extends Thread {

	private TradeDao tradeDao;

	private TradeType tradeType;
	private OrderBook orderBook;
	private int totalOfferQuantity = 0;
	private int totalBidQuantity = 0;

	private DecimalFormat df = new DecimalFormat("#.00");

	private Trade trade;

	public FulFillment(Trade trade, TradeDao tradeDao) {
		this.trade = trade;
		this.tradeDao = tradeDao;
	}

	@Override
	public void run() {
		try {
			doFulFillment(trade);
			tradeDao.update(trade);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doFulFillment(Trade trade) throws InterruptedException {
		log.info("Thread will stop for 5s!!!");
		Thread.sleep(5000);
		log.info("5s later!!!");
		if (!isRightStatus(trade))
			return;
		else {
			orderBook = trade.getSecurity().getOrderBook();
			totalOfferQuantity = getTotalOfferQuantity(orderBook);
			totalBidQuantity = getTotalBidQuantity(orderBook);

			int requiredQuantity = trade.getQuantity();
			log.info("Quantity: " + requiredQuantity);
			Position position = trade.getPosition();
			tradeType = trade.getType();
			switch (tradeType) {
			case MARKET:
				log.info("This is a MARKET tarde!!");
				if (Position.LONG == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity <= totalOfferQuantity) {
					setPriceForBuy(requiredQuantity, trade);
					trade.setStatus(TradeStatus.OPEN);
				} else if (Position.SHORT == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity <= totalBidQuantity) {
					setPriceForSell(requiredQuantity, trade);
					trade.setStatus(TradeStatus.OPEN);
				} else if (Position.LONG == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity <= totalBidQuantity) {
					setPriceForSell(requiredQuantity, trade);
					trade.setStatus(TradeStatus.CLOSED);

				} else if (Position.LONG == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity > totalBidQuantity) {
					break;
				} else if (Position.SHORT == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity <= totalOfferQuantity) {
					setPriceForBuy(requiredQuantity, trade);
					trade.setStatus(TradeStatus.CLOSED);
				} else if (Position.SHORT == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity <= totalOfferQuantity) {
					break;
				} else {
					trade.setStatus(TradeStatus.REJECTED);
				}
				break;
			case IOC:
				log.info("This is a IOC trade!!");
				if (Position.LONG == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity <= totalOfferQuantity) {
					setPriceForBuy(requiredQuantity, trade);
					trade.setStatus(TradeStatus.OPEN);
				} else if (Position.LONG == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity <= totalBidQuantity) {
					setPriceForSell(requiredQuantity, trade);
					trade.setStatus(TradeStatus.CLOSED);
				} else if (Position.LONG == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity > totalOfferQuantity) {
					setPriceForBuy(totalOfferQuantity, trade);
					trade.setQuantity(totalOfferQuantity);
					trade.setStatus(TradeStatus.OPEN);
				} else if (Position.LONG == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity > totalBidQuantity) {
					setPriceForSell(totalBidQuantity, trade);
					trade.setQuantity(totalBidQuantity);
					trade.setStatus(TradeStatus.CLOSED);
				} else if (Position.SHORT == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity <= totalBidQuantity) {
					setPriceForSell(requiredQuantity, trade);
					trade.setStatus(TradeStatus.OPEN);
				} else if (Position.SHORT == position && TradeStatus.OPEN == trade.getStatus()
						&& requiredQuantity <= totalOfferQuantity) {
					setPriceForBuy(requiredQuantity, trade);
					trade.setStatus(TradeStatus.CLOSED);
				} else if (Position.SHORT == position && TradeStatus.CREATED == trade.getStatus()
						&& requiredQuantity > totalBidQuantity) {
					setPriceForSell(totalBidQuantity, trade);
					trade.setQuantity(totalBidQuantity);
					trade.setStatus(TradeStatus.OPEN);
				} else {
					setPriceForBuy(totalOfferQuantity, trade);
					trade.setQuantity(totalOfferQuantity);
					trade.setStatus(TradeStatus.CLOSED);
				}
				break;
			case LIMIT:
				while (true) {
					log.info("This is a LIMIT trade!!");
					if (Position.LONG == position && TradeStatus.CREATED == trade.getStatus()) {
						if (checkIsGoodToBuy(orderBook, trade)) {
							setPriceForBuy(requiredQuantity, trade);
							trade.setStatus(TradeStatus.OPEN);
							break;
						}
						// else
						// doFulFillment(trade);
					} else if (Position.LONG == position && TradeStatus.OPEN == trade.getStatus()) {
						log.info("In Sell!!!");
						if (checkIsGoodToSell(orderBook, trade)) {
							setPriceForSell(requiredQuantity, trade);
							trade.setStatus(TradeStatus.CLOSED);
							break;
						}
						// else
						// doFulFillment(trade);
					} else if (Position.SHORT == position && TradeStatus.OPEN == trade.getStatus()) {
						if (checkIsGoodToBuy(orderBook, trade)) {
							setPriceForBuy(requiredQuantity, trade);
							trade.setStatus(TradeStatus.CLOSED);
							break;
						}
						// else
						// doFulFillment(trade);
					} else {
						log.info("In Sell!!!");
						if (checkIsGoodToSell(orderBook, trade)) {
							setPriceForSell(requiredQuantity, trade);
							trade.setStatus(TradeStatus.OPEN);
							break;
						}
						// else
						// doFulFillment(trade);
					}
				}
				break;
			default:
				log.info("This is default case!!");
				break;
			}
		}
	}

	public boolean checkIsGoodToSell(OrderBook orderBook, Trade trade) {
		double price = trade.getSalePrice();
		int quantity = trade.getQuantity();
		int temp = 0;

		for (int i = 5; i < 10; i++) {
			if (orderBook.getOrderItem().get(i).getPrice() > price) {
				temp += orderBook.getOrderItem().get(i).getQty();
			}
		}
		if (quantity <= temp)
			return true;
		return false;
	}

	public boolean checkIsGoodToBuy(OrderBook orderBook, Trade trade) {
		double price = trade.getBuyPrice();
		int quantity = trade.getQuantity();
		int temp = 0;

		for (int i = 4; i >= 0; i--) {
			if (orderBook.getOrderItem().get(i).getPrice() < price) {
				temp += orderBook.getOrderItem().get(i).getQty();
			}
		}
		if (quantity <= temp)
			return true;
		return false;
	}

	public void setPriceForSell(int requiredQuantity, Trade trade) {
		OrderBookItem Item1 = orderBook.getOrderItem().get(5);
		OrderBookItem Item2 = orderBook.getOrderItem().get(6);
		OrderBookItem Item3 = orderBook.getOrderItem().get(7);
		OrderBookItem Item4 = orderBook.getOrderItem().get(8);
		OrderBookItem Item5 = orderBook.getOrderItem().get(9);

		if (requiredQuantity <= Item1.getQty()) {
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(Item1.getPrice())));
			}else {
				trade.setSalePriceReal(new Double(df.format(Item1.getPrice())));
			}
			
		} else if ((requiredQuantity > Item1.getQty()) && (requiredQuantity <= (Item1.getQty() + Item2.getQty()))) {
			double price = (Item1.getQty() * Item1.getPrice() + Item2.getPrice() * (requiredQuantity - Item1.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
			
		} else if ((requiredQuantity > (Item1.getQty() + Item2.getQty()))
				&& (requiredQuantity <= (Item1.getQty() + Item2.getQty() + Item3.getQty()))) {
			double price = (Item1.getQty() * Item1.getPrice() + Item2.getPrice() * Item2.getQty()
					+ Item3.getPrice() * (requiredQuantity - Item1.getQty() - Item2.getQty())) / requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		} else if ((requiredQuantity > (Item1.getQty() + Item2.getQty() + Item3.getQty()))
				&& (requiredQuantity <= (Item1.getQty() + Item2.getQty() + Item3.getQty() + Item4.getQty()))) {
			double price = (Item1.getQty() * Item1.getPrice() + Item2.getPrice() * Item2.getQty()
					+ Item3.getPrice() * Item3.getQty()
					+ Item4.getPrice() * (requiredQuantity - Item1.getQty() - Item2.getQty() - Item3.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		} else {
			double price = (Item1.getQty() * Item1.getPrice() + Item2.getPrice() * Item2.getQty()
					+ Item3.getPrice() * Item3.getQty() + Item4.getPrice() * Item4.getQty()
					+ Item5.getPrice()
							* (requiredQuantity - Item1.getQty() - Item2.getQty() - Item3.getQty() - Item4.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		}
	}

	public void setPriceForBuy(int requiredQuantity, Trade trade) {
		OrderBookItem Item1 = orderBook.getOrderItem().get(0);
		OrderBookItem Item2 = orderBook.getOrderItem().get(1);
		OrderBookItem Item3 = orderBook.getOrderItem().get(2);
		OrderBookItem Item4 = orderBook.getOrderItem().get(3);
		OrderBookItem Item5 = orderBook.getOrderItem().get(4);
		if (requiredQuantity <= Item5.getQty()) {
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(Item5.getPrice()));
			}else {
				trade.setSalePriceReal(new Double(Item5.getPrice()));
			}
			
		} else if ((requiredQuantity > Item5.getQty()) && (requiredQuantity <= (Item5.getQty() + Item4.getQty()))) {
			double price = (Item5.getQty() * Item5.getPrice() + Item4.getPrice() * (requiredQuantity - Item5.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
			
		} else if ((requiredQuantity > (Item5.getQty() + Item4.getQty()))
				&& (requiredQuantity <= (Item5.getQty() + Item4.getQty() + Item3.getQty()))) {
			double price = (Item5.getQty() * Item5.getPrice() + Item4.getPrice() * Item4.getQty()
					+ Item3.getPrice() * (requiredQuantity - Item5.getQty() - Item4.getQty())) / requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		} else if ((requiredQuantity > (Item5.getQty() + Item4.getQty() + Item3.getQty()))
				&& (requiredQuantity <= (Item5.getQty() + Item4.getQty() + Item3.getQty() + Item2.getQty()))) {
			double price = (Item5.getQty() * Item5.getPrice() + Item4.getPrice() * Item4.getQty()
					+ Item3.getPrice() * Item3.getQty()
					+ Item2.getPrice() * (requiredQuantity - Item5.getQty() - Item4.getQty() - Item3.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		} else {
			double price = (Item5.getQty() * Item5.getPrice() + Item4.getPrice() * Item4.getQty()
					+ Item3.getPrice() * Item3.getQty() + Item2.getPrice() * Item2.getQty()
					+ Item1.getPrice()
							* (requiredQuantity - Item5.getQty() - Item4.getQty() - Item3.getQty() - Item2.getQty()))
					/ requiredQuantity;
			if(trade.getStatus()==TradeStatus.CREATED) {
				trade.setBuyPriceReal(new Double(df.format(price)));
			}else {
				trade.setSalePriceReal(new Double(df.format(price)));
			}
		}

	}

	public int getTotalOfferQuantity(OrderBook orderBook) {
		int totalQuantity = 0;
		for (int i = 0; i < 5; i++) {
			totalQuantity += orderBook.getOrderItem().get(i).getQty();
		}
		return totalQuantity;

	}

	public int getTotalBidQuantity(OrderBook orderBook) {
		int totalQuantity = 0;
		for (int i = 5; i < orderBook.getOrderItem().size(); i++) {
			totalQuantity += orderBook.getOrderItem().get(i).getQty();
		}
		return totalQuantity;

	}

	public boolean isRightStatus(Trade trade) {
		if (TradeStatus.CANCLED == trade.getStatus()) {
			log.info("This trade was CANCLED before it compelete!!!");
			return false;
		} else if (TradeStatus.CREATED == trade.getStatus() || TradeStatus.OPEN == trade.getStatus()) {
			log.info("The status of this trade is correct!");
			return true;
		} else {
			log.error("The status of this trade is incorrect!!!!!");
		}
		return false;
	}

}
