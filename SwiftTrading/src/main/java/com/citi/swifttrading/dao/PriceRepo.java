package com.citi.swifttrading.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.generator.OrderBook;
import com.citi.swifttrading.service.trade.SecurityUpdater;

@Repository
public class PriceRepo {
	private Map<String, List<Double>> prices = new HashMap<>();
	private Map<String, OrderBook> orderBooks = new HashMap<>();

	public void add(String abbr) {
		SecurityUpdater updater;
		List<Double> prices = new ArrayList<>();
		OrderBook orderBook = new OrderBook();
		updater = new SecurityUpdater(abbr,prices,orderBooks);
		updater.start();
		orderBooks.put(abbr, orderBook);
		this.prices.put(abbr, prices);
	}

	public List<Double> getPrices(String abbr) {
		return prices.get(abbr);
	}

	public OrderBook getOrderBook(String abbr) {
		return orderBooks.get(abbr);
	}

	PriceRepo() {
		add("APPL");
		add("GOOG");
		add("CITI");
		add("ABAB");
		add("APPL");
		add("C");
		add("CARR.PA");
		add("F");
		add("GOOG");
		add("NSRGY");
		add("SNE");
		add("WMT");
	}

	public void bind(Security security) {
		security.setPrices(getPrices(security.getNameAbbreviation()));
		security.setOrderBook(getOrderBook(security.getNameAbbreviation()));
	}

}
