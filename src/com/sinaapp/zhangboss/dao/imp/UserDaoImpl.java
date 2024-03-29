package com.sinaapp.zhangboss.dao.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sinaapp.zhangboss.dao.SuperDao;
import com.sinaapp.zhangboss.dao.UserDao;
import com.sinaapp.zhangboss.pojo.User;

@Component("userDao")
public class UserDaoImpl extends SuperDao implements UserDao {

	@Override
	public void deleteById(long id) {
		this.template.delete("User.deleteById", id);
	}

	@Override
	public void inseartUser(User user) {
		this.template.insert("User.saveUser", user);
	}

	@Override
	public void updateUser(User user) {
		this.template.update("User.updateUser", user);
	}

	@Override
	public List<User> findByLimit(int limit) {
		return this.template.selectList("User.findByLimit", limit);
		
	}

	@Override
	public List<User> findByName(String name) {
		return this.template.selectList("User.findByName", name);
	}

	@Override
	public User findById(long id) {
		return this.template.selectOne("User.findById", id);
	}
	
}
