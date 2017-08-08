package com.citi.swifttrading.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import com.citi.swifttrading.dao.UserDao;
import com.citi.swifttrading.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public User queryById(int id) {
		return (User) sqlSessionTemplate.selectOne("queryById", id);
	}

	@Override
	public void save(User u) {
		sqlSessionTemplate.insert("insert-user", u);
	}

	@Override
	public void update(User u) {
		sqlSessionTemplate.update("update-user", u);
	}

	@Override
	public void delete(int id) {
		sqlSessionTemplate.delete("delete-user", id);
	}

	@Override
	public List<User> queryAll() {
		List<User> users = sqlSessionTemplate.selectList("queryAll");
		return users;
	}

}
