package com.citi.swifttrading.util;

import java.util.List;

public class MathUtil {
	public static double average(List<Double> numbers) {
		double sum=0;
		for(double price: numbers) {
			sum+=price;
		}
		return sum/numbers.size();
	}
	
	public static double std(List<Double> numbers) {
		double average = average(numbers);
		double diff=0;
		for(double price: numbers) {
			diff+=Math.pow(price-average,2);
		}
		return diff/numbers.size();
	}
}
