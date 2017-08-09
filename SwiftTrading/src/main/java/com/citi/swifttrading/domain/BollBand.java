package com.citi.swifttrading.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BollBand extends Strategy {

	private static final long serialVersionUID = 1L;

	private int period;
	private double std;

	public BollBand(String strategyName, String description, Security security, int period, double Std,
			double exit) {
		super(strategyName, description, security, exit);
		this.period = period;
		this.std = Std;
	}

	public BollBand() {
		super();
	}
	

}
