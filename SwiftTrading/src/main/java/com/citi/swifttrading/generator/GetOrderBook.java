package com.citi.swifttrading.generator;

import java.util.ArrayList;
import java.util.List;

public class GetOrderBook {


	public OrderBook getOrderBook(){
		List<OrderBookItem> orderBookItems = new ArrayList<OrderBookItem>();
		OrderBook orderBook = new OrderBook();
		GetOrderBookItem getOrderBookItem = new GetOrderBookItem();
		 orderBookItems =getOrderBookItem.generateBook();
		orderBook.setOrderItem(orderBookItems);	
		orderBook.setOfferPrice(orderBookItems.get(4).getPrice());
		return orderBook;
	}
	
/*	public static List<OrderBookItem> getItemPrices() {
		GetOrderBookItem getOrderBookItem = new GetOrderBookItem();
		List<OrderBookItem> orderBookItems = new ArrayList<OrderBookItem>();
		orderBookItems = getOrderBookItem.generateBook();
		for (int i = 0; i < orderBookItems.size(); i++) {
			if (i < 5) {
				orderBookItems.get(i).setBid(orderBookItems.get(i).getBid() + (5 - i));
			} else {
				orderBookItems.get(i).setBid(orderBookItems.get(i).getBid() + (i - 4));
			}
		}
		return orderBookItems;
	}*/

}
