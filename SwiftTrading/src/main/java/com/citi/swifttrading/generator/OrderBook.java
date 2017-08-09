package com.citi.swifttrading.generator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBook {
	 
	private List<OrderBookItem> orderItem = new ArrayList<OrderBookItem>();	
	private double offerPrice;
	public OrderBook() {
		// TODO Auto-generated constructor stub
	}
	public OrderBook(List<OrderBookItem> orderItem, double offerPrice) {
		super();
		this.orderItem = orderItem;
		this.offerPrice = offerPrice;
	}
	@Override
	public String toString() {
		return "OrderBook [orderItem=" + orderItem + ", offerPrice=" + offerPrice + "]";
	}
	
	
	
	
}
