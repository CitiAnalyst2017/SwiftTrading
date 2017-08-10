package com.citi.swifttrading.service.trade;

import java.util.List;
import java.util.Map;

import com.citi.swifttrading.generator.GetOrderBook;
import com.citi.swifttrading.generator.OrderBook;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Setter
public class SecurityUpdater extends Thread {

	private List<Double> prices;
	private Map<String, OrderBook> orderBooks;
	
	private String abbr;
	private GetOrderBook getOrderBook = new GetOrderBook();
	public SecurityUpdater(String abbr,List<Double> prices,Map<String, OrderBook> orderBooks) {
		this.prices=prices;
		this.orderBooks= orderBooks;
		this.abbr=abbr;
	}
	
	@Override
	public void run() {	
		while (true) {			
				try {
					OrderBook orderBook = getOrderBook.getOrderBook();
					orderBooks.replace(abbr, orderBook);
					prices.add(orderBook.getOfferPrice());
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
