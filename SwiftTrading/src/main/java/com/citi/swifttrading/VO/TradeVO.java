package com.citi.swifttrading.VO;

import java.util.Date;

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
	private double buyprice;
	private double nowprice;
	private int quantity;
	private double lossprice;
	private double profitprice;
	private int expiration;
	private Date starttime;
	private double profit;
}
