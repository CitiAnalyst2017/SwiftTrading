package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.citi.swifttrading.generator.OrderBook;
import com.citi.swifttrading.util.MathUtil;

import lombok.Data;

@Data
public class Security implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected List<Double> prices=new ArrayList<>();
	private String securityName;
	private String nameAbbreviation;
	private OrderBook orderBook;

	public Security() {
		super();
	}

	public Security(String securityName, String nameAbbreviation) {
		super();
		this.securityName = securityName;
		this.nameAbbreviation = nameAbbreviation;
	}
	
	public double getAverage(int period) {
		List<Double> recentPrices = getRecentPrices(period);
		return MathUtil.average(recentPrices);
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

	
	public double getStd(int period) {
		List<Double> recentPrices = getRecentPrices(period);
		return MathUtil.std(recentPrices);
	}
	
	private List<Double> getRecentPrices(int period){
		List<Double> recentPrices=new ArrayList<>();
		for(int i=prices.size()-period;i<prices.size();i++) {
			recentPrices.add(prices.get(i));
		}
		return recentPrices;
	}
}
