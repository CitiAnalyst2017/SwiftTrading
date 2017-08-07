package com.citi.swifttrading.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.daoImpl.TradeDaoImpl;
import com.citi.swifttrading.domain.Trade222;
import com.citi.swifttrading.service.TradeService;

@Repository
public class TradeServiceImpl implements TradeService{

	@Autowired
	private TradeDaoImpl tradeDaoImpl;
	
	@Override
	public Trade222 queryById(int id) {
		return tradeDaoImpl.queryById(id);
	}

	@Override
	public void save(Trade222 t) {
		tradeDaoImpl.save(t);
	}

	@Override
	public void update(Trade222 t) {
		tradeDaoImpl.update(t);
	}

	@Override
	public void delete(int id) {
		tradeDaoImpl.delete(id);
	}

	@Override
	public List<Trade222> queryAll() {
		List<Trade222> trade = tradeDaoImpl.queryAll();
		return trade;
	}

}
