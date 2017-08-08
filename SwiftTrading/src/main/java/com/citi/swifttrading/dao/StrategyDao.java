package com.citi.swifttrading.dao;

import java.util.List;

import com.citi.swifttrading.domain.Strategy;

public interface StrategyDao {
	public Strategy queryById(int id);

	public void save(Strategy s);

	public void update(Strategy s);

	public void delete(int id);

	public List<Strategy> queryAll();

}
