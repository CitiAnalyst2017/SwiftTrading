package com.citi.swifttrading.domain;

import java.io.Serializable;

import com.citi.swifttrading.strategy.StrategyRunner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Strategy implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	protected String status;
	protected String strategyName;
	protected String description;
	protected Security security;
	protected double exit;
	protected StrategyRunner runner;

	public Strategy(String strategyName, String description, Security security, double exit) {
		super();
		this.strategyName = strategyName;
		this.description = description;
		this.security = security;
		this.exit = exit;
	}

	public Strategy() {
		super();
	}

	public String getStatus() {
		return runner.getState().toString();
	}

}
