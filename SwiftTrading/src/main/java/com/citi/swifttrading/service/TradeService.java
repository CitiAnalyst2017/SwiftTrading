package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.Trade222;

public interface TradeService {
	
	public Trade222 queryById(int id);

	public void save(Trade222 t);

	public void update(Trade222 t);

	public void delete(int id);

	public List<Trade222> queryAll();

}
