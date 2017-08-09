package com.citi.swifttrading.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.PriceRepo;
import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.TradeStatus;

@Repository
public class TradeDaoImpl implements TradeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	PriceRepo priceRepo;

	@Override
	public Trade queryById(int id) {
		Trade trade =(Trade)sqlSessionTemplate.selectOne("queryByTradeID", id);
		priceRepo.bind(trade.getSecurity());
		return trade;
	}

	@Override
	public int save(Trade t) {
		sqlSessionTemplate.insert("insert-trade", t);
		return t.getId();
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
		List<Trade> trades = sqlSessionTemplate.selectList("query_AllTrade");
		trades.forEach(trade->priceRepo.bind(trade.getSecurity()));
		return trades;
	}

	@Override
	public List<Trade> queryByStatus(TradeStatus status) {
		List<Trade> trades = sqlSessionTemplate.selectList("queryByStarus", status);
		trades.forEach(trade->priceRepo.bind(trade.getSecurity()));
		return trades;
	}

}
