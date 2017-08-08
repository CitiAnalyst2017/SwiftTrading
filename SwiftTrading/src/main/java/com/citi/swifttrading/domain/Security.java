package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Security implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected List<Double> prices=new ArrayList<>();
	private String securityName;
	private String nameAbbreviation;

	public Security() {
		super();
	}

	public Security(String securityName, String nameAbbreviation) {
		super();
		this.securityName = securityName;
		this.nameAbbreviation = nameAbbreviation;
	}
	
	public double getAverage(int period) {
		if(period>prices.size())
			return -1;
		double sum=0;
		for(int i=prices.size()-period;i<prices.size();i++) {
			sum+=prices.get(i);
		}
		return sum/period;
	}

	public void addPrice(double price) {
		prices.add(price);
	}

	public double latestPrice() {
		return prices.get(prices.size()-1);
	}

	@Override
	public String toString() {
		return "Security [securityName=" + securityName + ", nameAbbreviation=" + nameAbbreviation + "]";
	}
}
