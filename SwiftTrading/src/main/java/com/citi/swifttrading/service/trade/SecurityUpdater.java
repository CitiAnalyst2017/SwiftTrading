package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.List;


import com.citi.swifttrading.generator.GetOrderBook;
import com.citi.swifttrading.generator.OrderBook;

import lombok.Setter;

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
/*	@Override
	public void run() {
		ListIterator<Double> iter = prices.listIterator();
		while (true) {

			
			while (iter.hasNext()) {
				target.add(iter.next());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (iter.hasPrevious()) {
				target.add(iter.previous());
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}*/

}
