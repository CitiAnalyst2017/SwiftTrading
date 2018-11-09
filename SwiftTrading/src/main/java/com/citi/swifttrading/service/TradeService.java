package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.TradeStatus;

public interface TradeService {

	public Trade queryById(int id);

	public int save(Trade t);

	public void update(Trade t);

	public void delete(int id);

	public List<Trade> queryAll();
	
	public List<Trade> queryByStatus(TradeStatus status);
}
