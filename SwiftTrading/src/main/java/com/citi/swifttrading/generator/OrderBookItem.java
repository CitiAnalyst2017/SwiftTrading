package com.citi.swifttrading.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBookItem {
	 
	  private String bid;
	  private Double price;
	  private int qty;
	public OrderBookItem(String bid, double price, int qty) {
		super();
		this.bid = bid;
		this.price = price;
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "OrderBookItem [bid=" + bid + ", price=" + price + ", qty=" + qty + "]";
	}
	public OrderBookItem() {	
	}										
}
