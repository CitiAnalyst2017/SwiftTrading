package com.citi.swifttrading.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.citi.swifttrading.domain.User;
import com.citi.swifttrading.service.UserService;
@Repository
public class UserServiceImpl implements UserService {

	@Autowired 
	com.citi.swifttrading.daoImpl.UserDaoImpl UserDaoImpl;
	
	@Override
	public User queryById(int id) {
		return UserDaoImpl.queryById(id);
	}

	@Override
	public void save(User u) {
		UserDaoImpl.save(u);;
	}

	@Override
	public void update(User u) {
		UserDaoImpl.update(u);;
	}

	@Override
	public void delete(int id) {
		UserDaoImpl.delete(id);;
	}

	@Override
	public List<User> queryAll() {
		List<User> users = UserDaoImpl.queryAll();
		return users;
	}

}
