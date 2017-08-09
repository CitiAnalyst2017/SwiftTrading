package com.citi.swifttrading.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.generator.OrderBook;
import com.citi.swifttrading.service.trade.SecurityUpdater;

@Repository
public class PriceRepo {
	public static Map<String, List<Double>> map = new HashMap<>();
	 public static Map<String,OrderBook> orderMap = new HashMap<>();
	public List<Double> updatePrice(String abbr) {
		SecurityUpdater updater;
		List<Double> prices=new ArrayList<>();
		OrderBook orderBook = new OrderBook();
		updater = new SecurityUpdater(prices,orderBook);
		updater.start();
		orderMap.put(abbr,orderBook);
		return prices;
	}

	public List<Double> add(String abbr) {
		SecurityUpdater updater;
		List<Double> prices=new ArrayList<>();
		OrderBook orderBook = new OrderBook();
		updater = new SecurityUpdater(prices,orderBook);
		updater.start();
		map.put(abbr, prices);
		return prices;
	}
	public List<Double> get(String abbr) {
		if (map.containsKey(abbr)) {
			return map.get(abbr);
		} else {
			return add(abbr);
		}
	}

	PriceRepo() {
		add("APPL");
		add("GOOG");
	}

	public void stop() {
		// TODO Auto-generated method stub

	}

}
