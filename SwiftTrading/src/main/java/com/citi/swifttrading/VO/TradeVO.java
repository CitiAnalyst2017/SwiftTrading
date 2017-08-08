package com.citi.swifttrading.VO;

import java.util.Date;

import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

import lombok.Data;

@Data
public class TradeVO {
	private int id;
	private TradeStatus tradeStatus;
	private TradeType tradeType;
	private Position position;
	private String symbol;
	private double price;
	private double currentPrice;
	private int quantity;
	private double lossPrice;
	private double profitPrice;
	private int expiration;
	private Date startTime;
	private double profit;
}
