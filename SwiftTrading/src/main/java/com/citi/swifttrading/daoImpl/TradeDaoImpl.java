package com.citi.swifttrading.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Trade222;

@Repository
public class TradeDaoImpl implements TradeDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Trade222 queryById(int id) {
//		Trade trade = sqlSessionTemplate.selectOne("queryByTradeID", id);
		
		//TODO
		
		List<Trade222> trade = sqlSessionTemplate.selectList("query_AllTrade");
		Iterator<Trade222> iter = trade.iterator();
		Trade222 tra = null;
		while(iter.hasNext()) {
			Trade222 t= iter.next();
			if(t.getId() == id)
				tra = t;
		}
		return tra;
	}

	@Override
	public void save(Trade222 t) {
		sqlSessionTemplate.insert("insert-trade", t);
	}

	@Override
	public void update(Trade222 t) {
		sqlSessionTemplate.update("update-trade", t);
	}

	@Override
	public void delete(int id) {
		sqlSessionTemplate.delete("delete-trade", id);
	}

	@Override
	public List<Trade222> queryAll() {
		List<Trade222> trade = sqlSessionTemplate.selectList("query_AllTrade");
		return trade;
	}

}
