package com.citi.swifttrading.VO;

import lombok.Data;

@Data
public class StrategyVO {
	private int id;
	private String status;
	private String code;
	private double exit;
	private int longperiod;
	private int shortperiod;
	private int period;
	private double std;
}
