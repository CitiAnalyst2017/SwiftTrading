package com.citi.swifttrading.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.TradeStatus;

@Repository
public class TradeDaoImpl implements TradeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	SecurityDaoImpl securityDaoImpl;

	@Override
	public Trade queryById(int id) {
		return (Trade) sqlSessionTemplate.selectOne("queryByTradeID", id);
	}

	@Override
	public void save(Trade t) {
		sqlSessionTemplate.insert("insert-trade", t);
	}

	@Override
	public void update(Trade t) {
		sqlSessionTemplate.update("update-trade", t);
	}

	@Override
	public void delete(int id) {
		sqlSessionTemplate.delete("delete-trade", id);
	}

	@Override
	public List<Trade> queryAll() {
		List<Trade> trade = sqlSessionTemplate.selectList("query_AllTrade");
		return trade;
	}

	@Override
	public List<Trade> queryByStarus(TradeStatus status) {
		List<Trade> trade = sqlSessionTemplate.selectList("queryByStarus", status);
		return trade;
	}

}
