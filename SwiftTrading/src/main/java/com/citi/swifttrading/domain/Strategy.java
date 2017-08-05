package com.citi.swifttrading.domain;

import java.io.Serializable;

public class Strategy implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String strategyName;
	private String Description;
	
	public Strategy(String strategyName, String description) {
		super();
		this.strategyName = strategyName;
		Description = description;
	}

	public Strategy() {
		super();
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "Strategy [strategyName=" + strategyName + ", Description=" + Description + "]";
	}

}
