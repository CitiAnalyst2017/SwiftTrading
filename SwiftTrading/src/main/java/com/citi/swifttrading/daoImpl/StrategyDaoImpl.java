package com.citi.swifttrading.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.StrategyDao;
import com.citi.swifttrading.domain.Strategy222;
import com.citi.swifttrading.domain.Trade222;

@Repository
public class StrategyDaoImpl implements StrategyDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void save(Strategy222 s) {
		sqlSessionTemplate.insert("insert-strategy", s);	
	}

	@Override
	public Strategy222 queryById(int id) {
//		return (Strategy) sqlSessionTemplate.selectOne("queryByStrategyID", id);
		
//		TODO
		
		List<Strategy222> strategy = sqlSessionTemplate.selectList("query_All_Strategy");
		Iterator<Strategy222> iter = strategy.iterator();
		Strategy222 ste = null;
		while(iter.hasNext()) {
			Strategy222 s= iter.next();
			if(s.getId() == id)
				ste = s;
		}
		return ste;
	}

	@Override
	public void update(Strategy222 s) {
		sqlSessionTemplate.insert("update-strategy", s);	
		
	}

	@Override
	public void delete(int id) {
		sqlSessionTemplate.delete("delete-strategy", id);	
	}

	@Override
	public List<Strategy222> queryAll() {
		List<Strategy222> strategy = sqlSessionTemplate.selectList("query_All_Strategy");
		return strategy;
	}

}
