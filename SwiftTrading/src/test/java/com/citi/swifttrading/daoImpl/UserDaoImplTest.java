package com.citi.swifttrading.daoImpl;

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
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoImplTest {

	@Autowired
	UserDaoImpl userDaoImpl;

	User user;

	List<User> users;

	@Test
	public void testSave() {
		user = new User("Smith", "831276323@qq.com", "18739490544", "Gold Road No.1");
		userDaoImpl.save(user);
	}

	@Test
	public void testQueryAll() {
		users = userDaoImpl.queryAll();
		assertNotNull(users);
		assertEquals(3, users.size());
	}

}
