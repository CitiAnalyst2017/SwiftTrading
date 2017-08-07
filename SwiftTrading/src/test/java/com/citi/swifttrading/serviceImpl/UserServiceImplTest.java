package com.citi.swifttrading.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.citi.swifttrading.daoImpl.UserDaoImpl;
import com.citi.swifttrading.domain.User;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="/applicationContext.xml")  
public class UserServiceImplTest {
	
	@Autowired  
	UserServiceImpl userServiceImpl;  
	
	User user;
	
	List<User> users;
	
	@Test
	public void testSave() {
		user = new User("PAT", "111276323@qq.com", "17739490544", "Gold Road No.5");
		userServiceImpl.save(user);
	}

	@Test
	public void testQueryAll() {
		users = userServiceImpl.queryAll();
		assertNotNull(users);
		assertEquals(4, users.size());
	}

}
