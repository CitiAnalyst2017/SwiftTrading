package com.citi.swifttrading.service;

import java.util.List;

import com.citi.swifttrading.domain.User;

public interface UserService {

	public User queryById(int id);

	public void save(User u);

	public void update(User u);

	public void delete(int id);

	public List<User> queryAll();

}
