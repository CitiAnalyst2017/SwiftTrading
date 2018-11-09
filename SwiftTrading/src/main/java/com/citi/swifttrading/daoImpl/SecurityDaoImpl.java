package com.citi.swifttrading.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.dao.PriceRepo;
import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.domain.Security;

@Repository
public class SecurityDaoImpl implements SecurityDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private PriceRepo priceRepo;
	
	@Override
	public void save(Security s) {
		sqlSessionTemplate.insert("insert-security", s);	
	}

	@Override
	public Security queryById(String nameAbbreviation) {
		Security security = (Security) sqlSessionTemplate.selectOne("queryByID", nameAbbreviation);
		priceRepo.bind(security);
		return security;
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
		List<Security> securitys = sqlSessionTemplate.selectList("query_All");
		securitys.forEach(security->priceRepo.bind(security));
		return securitys;
	}

}
