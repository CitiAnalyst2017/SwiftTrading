package com.citi.swifttrading.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.Security;

@Repository
public class SecurityDAO {
	public static Map<Integer,Security> map=new HashMap<>();
	
	public void save(Security s) {
		map.put(map.size()+1, s);
	}
	
	public Security get(int index) {
		return map.get(index);
	}
}
