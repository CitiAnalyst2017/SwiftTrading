package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.Security222;

public interface SecurityService {

	public void save(Security222 s);

	public Security222 queryById(String nameAbbreviation);

	public void update(Security222 s);
	
	public void delete(String nameAbbreviation);

	public List<Security222> queryAll();
}
