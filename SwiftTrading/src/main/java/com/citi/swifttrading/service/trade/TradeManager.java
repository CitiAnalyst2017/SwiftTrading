package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.dao.PriceRepo;
import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@Service("tradeManager")
public class TradeManager {
	@Autowired
	TradeDao tradeDao;
	@Autowired
	SecurityDao securityDao;
	@Autowired
	PriceRepo priceRepo;

	
	List<Trade> trades = new ArrayList<>();
	Date start_time = new Date();
	Date expiration;
	Calendar c = Calendar.getInstance();

	public Trade createMarketTrade(Position position, Security target, double exit) {
		this.setTime();
		Trade trade = new Trade(TradeType.MARKET, target, 100, start_time, expiration, 15, exit, position, 0);
		trades.add(trade);
		return trade;
	}

	public void closeTrade(Trade trade) {
		trade.setStatus(TradeStatus.CLOSED);
		trade.setEnd_time(new Date());
		trade.setSalePrice(trade.getSecurity().latestPrice());
		tradeDao.save(trade);
	}

	public Trade getTradeById(int id) {
		return tradeDao.queryById(id);
	}


	public List<TradeVO> getTradeVOs() {
		List<TradeVO> tradeVOs=new ArrayList<>();
		tradeDao.queryAll().forEach(trade->{
			tradeVOs.add(toVO(trade));
		});
		return tradeVOs;
	}

	public TradeVO getTradeVOById(int id) {
		Trade trade = getTradeById(id);
		return toVO(trade);
	}

	public TradeVO createTrade(TradeVO tradeVO) {
		Trade trade = new Trade();
		trade.setId(tradeVO.getId());
		trade.setSecurity(securityDao.queryById(tradeVO.getSymbol()));
		trade.setStart_time(new Date());
		trade.setExpiration(new Date());
		trade.setQuantity(tradeVO.getQuantity());
		trade.setPosition(tradeVO.getPosition());
		trade.setType(tradeVO.getTradeType());
		trade.setLoss_price(tradeVO.getLossPrice());
		trade.setProfit_price(tradeVO.getProfitPrice());
		trade.setStatus(TradeStatus.CREATED);
		if (tradeVO.getTradeType() == TradeType.LIMIT)
			trade.setPrice(tradeVO.getPrice());
		tradeDao.save(trade);
		return toVO(trade);
	}
	private void setTime() {
		c.setTime(start_time);
		c.add(Calendar.MINUTE, 15);
		expiration = c.getTime();
	}
	
	public  TradeVO toVO(Trade trade) {
		TradeVO tradeVO = new TradeVO();
		tradeVO.setId(trade.getId());
		tradeVO.setTradeStatus(trade.getStatus());
		tradeVO.setTradeType(trade.getType());
		tradeVO.setPosition(trade.getPosition());
		tradeVO.setSymbol(trade.getSecurity().getNameAbbreviation());
		tradeVO.setPrice(trade.getPrice());
		tradeVO.setCurrentPrice(trade.getSecurity().latestPrice());
		tradeVO.setQuantity(trade.getQuantity());
		tradeVO.setLossPrice(trade.getLoss_price());
		tradeVO.setProfitPrice(trade.getProfit_price());
		tradeVO.setExpiration(-trade.getExpiration().compareTo(new Date()));
		tradeVO.setStartTime(trade.getStart_time());
		//TODO this.profit = trade.getProfit();
		return tradeVO;
	}
}
