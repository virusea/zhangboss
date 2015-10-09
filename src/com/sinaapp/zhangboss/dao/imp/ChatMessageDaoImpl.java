package com.sinaapp.zhangboss.dao.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sinaapp.zhangboss.dao.ChatMessageDao;
import com.sinaapp.zhangboss.dao.SuperDao;
import com.sinaapp.zhangboss.paramobj.SQLLimit;
import com.sinaapp.zhangboss.pojo.ChatMessage;


@Component("chatMessageDao")
public class ChatMessageDaoImpl extends SuperDao implements ChatMessageDao {

	@Override
	public void deleteById(ChatMessage message) {
		this.template.delete("ChatMessage.deleteById", message);
		
	}

	@Override
	public void inseartChatMessage(ChatMessage message) {
		this.template.insert("ChatMessage.insertMessage", message);
		
	}

	@Override
	public List<ChatMessage> findByPeople(ChatMessage message) {
		return this.template.selectList("ChatMessage.findByPeople", message);
	}

	@Override
	public List<ChatMessage> findByLimit(ChatMessage message) {
		return this.template.selectList("ChatMessage.findByLimit", message);
	}

	@Override
	public ChatMessage findById(ChatMessage message) {
		return this.template.selectOne("ChatMessage.findById", message);
	}


	
}
