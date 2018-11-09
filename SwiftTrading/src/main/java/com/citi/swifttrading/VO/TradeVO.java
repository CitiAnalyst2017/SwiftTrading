package com.citi.swifttrading.VO;

import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

import lombok.Data;

@Data
public class TradeVO {
	private int id;
	private TradeStatus status;
	private TradeType type;
	private Position position;
	private String code;
	private String buyprice;
	private String nowprice;
	private int quantity;
	private double lossprice;
	private double profitprice;
	private int expiration;
