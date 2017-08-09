package com.citi.swifttrading.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBookItemVO {
	private String code;
	private String side;
	private double price;
	private int qty;
}
