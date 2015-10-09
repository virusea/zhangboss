package com.sinaapp.zhangboss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinaapp.zhangboss.dao.UserDao;
import com.sinaapp.zhangboss.dao.WXMessageDao;
import com.sinaapp.zhangboss.pojo.User;
import com.sinaapp.zhangboss.pojo.UserTextMessage;

@Service("wxTextMessageService")
public class WXTextMessageService {
	
	@Resource(name="wxMessageDao")
	private WXMessageDao dao;

	public WXMessageDao getDao() {
		return dao;
	}
	public void setDao(WXMessageDao dao) {
		this.dao = dao;
	}
	

	@Transactional
	public void deleteById(long id) {
		dao.deleteById(id);	
	}

	@Transactional
	public void inseartMessage(UserTextMessage message) {
		dao.inseartMessage(message);
	}

	@Transactional
	public List<UserTextMessage> findByLimit(int limit) {
		return dao.findByLimit(limit);
	}

	@Transactional
	public List<UserTextMessage> findByUserId(String userid) {
		return dao.findByUserId(userid);
	}

	@Transactional
	public UserTextMessage findById(long id) {
		return dao.findById(id);
	}

	

}
