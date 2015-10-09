package com.sinaapp.zhangboss.dao.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sinaapp.zhangboss.dao.SuperDao;
import com.sinaapp.zhangboss.dao.UserDao;
import com.sinaapp.zhangboss.dao.WXMessageDao;
import com.sinaapp.zhangboss.pojo.User;
import com.sinaapp.zhangboss.pojo.UserTextMessage;

@Component("wxMessageDao")
public class WXTextMessageDaoImpl extends SuperDao implements WXMessageDao {

	@Override
	public void deleteById(long id) {
		this.template.delete("WXTextMessage.deleteById", id);
		
	}

	@Override
	public void inseartMessage(UserTextMessage message) {
		this.template.insert("WXTextMessage.inseartMessage", message);
		
	}

	@Override
	public List<UserTextMessage> findByLimit(int limit) {
		return this.template.selectList("WXTextMessage.findByLimit", limit);
	}

	@Override
	public List<UserTextMessage> findByUserId(String userid) {
		return this.template.selectList("WXTextMessage.findByUserId", userid);
	}

	@Override
	public UserTextMessage findById(long id) {
		return this.template.selectOne("WXTextMessage.findById", id);
	}


	
}
