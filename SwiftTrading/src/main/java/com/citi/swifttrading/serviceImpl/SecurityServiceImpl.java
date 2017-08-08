package com.citi.swifttrading.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.daoImpl.SecurityDaoImpl;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.service.SecurityService;

@Repository
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private SecurityDaoImpl securityDaoImpl;

	@Override
	public void save(Security s) {
		securityDaoImpl.save(s);
	}

	@Override
	public Security queryById(String nameAbbreviation) {
		return securityDaoImpl.queryById(nameAbbreviation);
	}

	@Override
	public void update(Security s) {
		securityDaoImpl.update(s);
	}

	@Override
	public void delete(String nameAbbreviation) {
		securityDaoImpl.delete(nameAbbreviation);
	}

	@Override
	public List<Security> queryAll() {
		List<Security> security = securityDaoImpl.queryAll();
		return security;
	}

}
