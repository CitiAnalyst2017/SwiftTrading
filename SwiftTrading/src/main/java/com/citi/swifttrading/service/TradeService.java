package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.Trade;

public interface TradeService {
	
	public Trade queryById(int id);

	public void save(Trade t);

	public void update(Trade t);

	public void delete(int id);

	public List<Trade> queryAll();

}
