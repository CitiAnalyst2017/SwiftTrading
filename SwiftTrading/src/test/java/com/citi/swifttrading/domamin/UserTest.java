package com.citi.swifttrading.domamin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.User;

public class UserTest {
	
	User user;
	
	@Before
	public void setUp() {
		user = new User("John Smith", "31276323@qq.com", "18739490544", "Gold Road No.1");
	}

	@Test
	public void testUserGet() {
		assertEquals("John Smith", user.getName());
		assertEquals("31276323@qq.com", user.getEmail());
		assertEquals("18739490544", user.getPhoneNumber());
		assertEquals("Gold Road No.1", user.getAddress());
	}
	
	@Test
	public void testUserSet() {
		user.setName("Mike Smith");
		user.setEmail("123456789@qq.com");
		user.setPhoneNumber("1234567800");
		user.setAddress("This is new Address");
		
		assertEquals("Mike Smith", user.getName());
		assertEquals("123456789@qq.com", user.getEmail());
		assertEquals("1234567800", user.getPhoneNumber());
		assertEquals("This is new Address", user.getAddress());
	}

}
