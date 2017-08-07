package com.citi.swifttrading.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.SecurityDao222;
import com.citi.swifttrading.domain.Security222;

@Repository
public class SecurityDaoImpl implements SecurityDao222{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void save(Security222 s) {
		sqlSessionTemplate.insert("insert-security", s);	
	}

	@Override
	public Security222 queryById(String nameAbbreviation) {
		return (Security222) sqlSessionTemplate.selectOne("queryByID", nameAbbreviation);
	}

	@Override
	public void update(Security222 s) {
		sqlSessionTemplate.insert("update-security", s);	
		
	}

	@Override
	public void delete(String nameAbbreviation) {
		sqlSessionTemplate.delete("delete-security", nameAbbreviation);	
	}

	@Override
	public List<Security222> queryAll() {
		List<Security222> security = sqlSessionTemplate.selectList("query_All");
		return security;
	}

}
