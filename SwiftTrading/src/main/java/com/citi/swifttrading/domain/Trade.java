package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.util.Date;

import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

import lombok.Data;

@Data
public class Trade implements Serializable,Comparable<Trade> {

	private static final long serialVersionUID = 1L;

	private int id;
	private int quantity;
	private Date expiration;
	private Date start_time;
	private Date end_time;
	private TradeStatus status;
	private double loss_price;
	private double profit_price;
	private TradeType type;
	private Security security;
	private Position position;
	private String ccy;
	private double salePrice;
	private double salePriceReal;
	private double buyPrice;
	private double buyPriceReal;
	private int strategyId;

	public Trade() {
		super();
	}

	public Trade(TradeType type, Security security, int quantity, Date start_time, Date expiration, double loss_price,
			double profit_price, Position position, double price) {
		super();
		this.security = security;
		this.quantity = quantity;
		this.expiration = expiration;
		this.start_time = start_time;
		this.loss_price = loss_price;
		this.profit_price = profit_price;
		this.type = type;
		this.status = TradeStatus.CREATED;
		this.position = position;
		if (type == TradeType.LIMIT) {
			this.buyPrice = price;
		} else {
			this.buyPrice = security.latestPrice();
		}
	}
	
	public double calProfit() {
		double profit;
		if(status==TradeStatus.CLOSED) {
			profit=(salePriceReal-buyPriceReal)*quantity;
		}
		else {
			profit=(security.latestPrice()-buyPriceReal)*quantity;
		}
		if(position==Position.SHORT)
			profit*=-1;
		return profit;
	}

	@Override
	public int compareTo(Trade other) {
		return this.id-other.id;
	}
}
