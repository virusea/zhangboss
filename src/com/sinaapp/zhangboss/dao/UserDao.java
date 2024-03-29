package com.sinaapp.zhangboss.dao;

import java.util.List;

import com.sinaapp.zhangboss.pojo.User;


public interface UserDao {
	public void deleteById(long id);
	public void inseartUser(User user);
	public void updateUser(User user);
	public List<User> findByLimit(int limit);
	public List<User> findByName(String name);
	public User findById(long id);
	
}
