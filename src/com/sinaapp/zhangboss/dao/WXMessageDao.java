package com.sinaapp.zhangboss.dao;

import java.util.List;

import com.sinaapp.zhangboss.pojo.User;
import com.sinaapp.zhangboss.pojo.UserTextMessage;


public interface WXMessageDao {
	
	public void deleteById(long id);
	
	public void inseartMessage(UserTextMessage message);
	
	public List<UserTextMessage> findByLimit(int limit);
	
	public List<UserTextMessage> findByUserId(String userid);
	
	public UserTextMessage findById(long id);
	
}
