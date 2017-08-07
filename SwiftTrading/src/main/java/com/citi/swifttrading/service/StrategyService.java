package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.Strategy222;

public interface StrategyService {
	
	public Strategy222 queryById(int id);

	public void save(Strategy222 s);

	public void update(Strategy222 s);

	public void delete(int id);

	public List<Strategy222> queryAll();

}
