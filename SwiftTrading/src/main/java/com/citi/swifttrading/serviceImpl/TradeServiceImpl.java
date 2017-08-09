package com.citi.swifttrading.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.daoImpl.TradeDaoImpl;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.service.TradeService;

@Repository
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeDaoImpl tradeDaoImpl;

	@Override
	public Trade queryById(int id) {
		return tradeDaoImpl.queryById(id);
	}

	@Override
	public void save(Trade t) {
		tradeDaoImpl.save(t);
	}

	@Override
	public void update(Trade t) {
		tradeDaoImpl.update(t);
	}

	@Override
	public void delete(int id) {
		tradeDaoImpl.delete(id);
	}

	@Override
	public List<Trade> queryAll() {
		List<Trade> trade = tradeDaoImpl.queryAll();
		return trade;
	}

	@Override
	public List<Trade> queryByStarus(TradeStatus status) {
		List<Trade> trade = tradeDaoImpl.queryByStarus(status);
		return trade;
	}

}
