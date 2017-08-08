package com.citi.swifttrading.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Trade;

@Repository
public class TradeDaoImpl implements TradeDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Trade queryById(int id) {
//		Trade trade = sqlSessionTemplate.selectOne("queryByTradeID", id);
//		return trade;
		
//		TODO
		
		List<Trade> trade = sqlSessionTemplate.selectList("query_AllTrade");
		Iterator<Trade> iter = trade.iterator();
		Trade tra = null;
		while(iter.hasNext()) {
			Trade t= iter.next();
			if(t.getId() == id)
				tra = t;
		}
		return tra;
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

}
