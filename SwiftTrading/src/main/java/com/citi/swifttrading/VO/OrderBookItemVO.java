package com.citi.swifttrading.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBookItemVO {
	public OrderBookItemVO() {
		// TODO Auto-generated constructor stub
	}
	private String symbol;
	private String side;
	private double price;
	private int qty;
}
