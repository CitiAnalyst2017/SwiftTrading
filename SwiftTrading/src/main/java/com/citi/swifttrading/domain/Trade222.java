package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Trade222 implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private int quantity;
	private double buyPrice;
	private Date expiration;
	private Date start_time;
	private Date end_time;
	private int status;
	private double loss_price;
	private double profit_price;
	private String operationType;
	private String ccy;
	private String oderType;
	private String position;
	private double salePrice;

	public Trade222() {
		super();
	}

	public Trade222(String code, int quantity, Double buyPrice, Date expiration, Date start_time, Date end_time,
			int status, double loss_price, double profit_price, String operationType, String ccy, String oderType,
			String position, double salePrice) {
		super();
		this.code = code;
		this.quantity = quantity;
		this.buyPrice = buyPrice;
		this.expiration = expiration;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
		this.loss_price = loss_price;
		this.profit_price = profit_price;
		this.operationType = operationType;
		this.ccy = ccy;
		this.oderType = oderType;
		this.position = position;
		this.salePrice = salePrice;
	}

}
