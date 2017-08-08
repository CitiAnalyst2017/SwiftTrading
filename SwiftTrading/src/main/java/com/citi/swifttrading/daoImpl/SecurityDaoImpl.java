package com.citi.swifttrading.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.domain.Security;

@Repository
public class SecurityDaoImpl implements SecurityDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void save(Security s) {
		sqlSessionTemplate.insert("insert-security", s);	
	}

	@Override
	public Security queryById(String nameAbbreviation) {
		return (Security) sqlSessionTemplate.selectOne("queryByID", nameAbbreviation);
	}

	@Override
	public void update(Security s) {
		sqlSessionTemplate.insert("update-security", s);	
		
	}

	@Override
	public void delete(String nameAbbreviation) {
		sqlSessionTemplate.delete("delete-security", nameAbbreviation);	
	}

	@Override
	public List<Security> queryAll() {
		List<Security> security = sqlSessionTemplate.selectList("query_All");
		return security;
	}

}
