package com.citi.swifttrading.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.service.trade.SecrityUpdater;

@Repository
public class SecurityRepo {
	public static Map<Integer,Security> map=new HashMap<>();
	SecrityUpdater updater;
	public void save(Security s) {
		map.put(map.size()+1, s);
	}
	
	public Security get(int index) {
		return map.get(index);
	}
	
	private Security security;
	
	SecurityRepo(){
		
		security = new Security();
		security.setISIN("APPL");
		updater = new SecrityUpdater(security);
		updater.start();
		map.put(map.size()+1, security);
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		updater.stop();
	}
}
