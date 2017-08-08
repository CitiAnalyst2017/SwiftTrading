package com.citi.swifttrading.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.StrategyDao;
import com.citi.swifttrading.domain.Strategy;

@Repository
public class StrategyDaoImpl implements StrategyDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void save(Strategy s) {
		sqlSessionTemplate.insert("insert-strategy", s);	
	}

	@Override
	public Strategy queryById(int id) {
//		return (Strategy) sqlSessionTemplate.selectOne("queryByStrategyID", id);
		
//		TODO
		
		List<Strategy> strategy = sqlSessionTemplate.selectList("query_All_Strategy");
		Iterator<Strategy> iter = strategy.iterator();
		Strategy ste = null;
		while (iter.hasNext()) {
			Strategy s = iter.next();
			if (s.getId() == id)
				ste = s;
		}
		return ste;
	}

	@Override
	public void update(Strategy s) {
		sqlSessionTemplate.insert("update-strategy", s);	
		
	}

	@Override
	public void delete(int id) {
		sqlSessionTemplate.delete("delete-strategy", id);	
	}

	@Override
	public List<Strategy> queryAll() {
		List<Strategy> strategy = sqlSessionTemplate.selectList("query_All_Strategy");
		return strategy;
	}

}
