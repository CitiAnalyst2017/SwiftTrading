package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Trade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String code;
	private int quantity;
	private LocalDateTime expiration;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private int status;
	private double price;
	private double loss_price;
	private double profit_price;
	private String type;
	
	public Trade() {
		super();
	}

	public Trade(String code, int quantity, LocalDateTime expiration, LocalDateTime start_time, LocalDateTime end_time,
			int status, double price, double loss_price, double profit_price, String type) {
		super();
		this.code = code;
		this.quantity = quantity;
		this.expiration = expiration;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
		this.price = price;
		this.loss_price = loss_price;
		this.profit_price = profit_price;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLoss_price() {
		return loss_price;
	}

	public void setLoss_price(double loss_price) {
		this.loss_price = loss_price;
	}

	public double getProfit_price() {
		return profit_price;
	}

	public void setProfit_price(double profit_price) {
		this.profit_price = profit_price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", code=" + code + ", quantity=" + quantity + ", expiration=" + expiration
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", status=" + status + ", price=" + price
				+ ", loss_price=" + loss_price + ", profit_price=" + profit_price + ", type=" + type + "]";
	}

}
