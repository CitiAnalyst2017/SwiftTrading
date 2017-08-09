package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.List;

import com.citi.swifttrading.generator.GetOrderBook;
import com.citi.swifttrading.generator.OrderBook;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Setter
public class SecurityUpdater extends Thread {

	List<Double> prices = new ArrayList<>();
	OrderBook orderBook = new OrderBook();
	GetOrderBook getOrderBook = new GetOrderBook();
	public SecurityUpdater(List<Double> prices,OrderBook orderBook) {
		this.prices=prices;
		this.orderBook= orderBook;
	}
	
	@Override
	public void run() {	
		while (true) {			
				try {
					orderBook=getOrderBook.getOrderBook();
					prices.add(orderBook.getOfferPrice());
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
