package com.citi.swifttrading.dao;

import java.util.List;

import com.citi.swifttrading.domain.Security;

public interface SecurityDao {

	public void save(Security s);

	public Security queryById(String nameAbbreviation);

	public void update(Security s);
	
	public void delete(String nameAbbreviation);

	public List<Security> queryAll();
}
