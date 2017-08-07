package com.citi.swifttrading.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Strategy222 implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String strategyName;
	private String description;
	private int tradeId;

	public Strategy222(String strategyName, String description, int tradeId) {
		super();
		this.strategyName = strategyName;
		this.description = description;
		this.tradeId = tradeId;
	}

	public Strategy222() {
		super();
	}

}
