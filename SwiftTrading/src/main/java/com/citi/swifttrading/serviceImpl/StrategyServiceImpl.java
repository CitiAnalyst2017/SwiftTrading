package com.citi.swifttrading.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.daoImpl.StrategyDaoImpl;
import com.citi.swifttrading.domain.Strategy222;
import com.citi.swifttrading.service.StrategyService;

@Repository
public class StrategyServiceImpl implements StrategyService{

	@Autowired
	private StrategyDaoImpl strategyDaoImpl;
	
	@Override
	public Strategy222 queryById(int id) {
		return strategyDaoImpl.queryById(id);
	}

	@Override
	public void save(Strategy222 s) {
		strategyDaoImpl.save(s);
	}

	@Override
	public void update(Strategy222 s) {
		strategyDaoImpl.update(s);
	}

	@Override
	public void delete(int id) {
		strategyDaoImpl.delete(id);
	}

	@Override
	public List<Strategy222> queryAll() {
		List<Strategy222> Strategy = strategyDaoImpl.queryAll();
		return Strategy;
	}

}
