package com.citi.swifttrading.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovingAverage extends Strategy {

	private static final long serialVersionUID = 1L;

	private int longPeriod;
	private int shortPeriod;

	public MovingAverage(String strategyName, String description, Security security, int longPeriod, int shortPeriod,
			double exit) {
		super(strategyName, description, security, exit);
		this.longPeriod = longPeriod;
		this.shortPeriod = shortPeriod;
	}

	public MovingAverage() {
		super();
	}

}
