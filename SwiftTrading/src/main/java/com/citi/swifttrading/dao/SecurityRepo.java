package com.citi.swifttrading.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.service.trade.SecurityUpdater;

@Repository
public class SecurityRepo {
	public static Map<String,Security> map=new HashMap<>();
	SecurityUpdater updater;
	public void save(Security s) {
		map.put(s.getNameAbbreviation(), s);
	}
	
	public Security get(String abbr) {
		return map.get(abbr);
	}
	
	private Security security;
	
	SecurityRepo(){
		
		security = new Security();
		security.setNameAbbreviation("APPL");
		updater = new SecurityUpdater(security);
		updater.start();
		map.put(security.getNameAbbreviation(), security);
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		updater.stop();
	}
}
