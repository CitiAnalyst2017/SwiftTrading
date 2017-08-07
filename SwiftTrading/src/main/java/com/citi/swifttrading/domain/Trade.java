package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Trade implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private int quantity;
	private LocalDateTime expiration;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private TradeStatus status;
	private double price;
	private double loss_price;
	private double profit_price;
	private TradeType type;
	private Security security;
	private Position position;

	public Trade() {
		super();
	}

	public Trade(TradeType type, Security security, int quantity, LocalDateTime start_time, LocalDateTime expiration,
			double loss_price, double profit_price, Position position,double price) {
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
		if(type==TradeType.LIMIT) {
			this.price=price;
		}
		else {
			this.price=security.latestPrice();
		}
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", code=" + code + ", quantity=" + quantity + ", expiration=" + expiration
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", status=" + status + ", price=" + price
				+ ", loss_price=" + loss_price + ", profit_price=" + profit_price + ", type=" + type + ", security="
				+ security + ", position=" + position + "]";
	}

}
