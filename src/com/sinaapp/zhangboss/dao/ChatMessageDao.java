package com.sinaapp.zhangboss.dao;

import java.util.List;

import com.sinaapp.zhangboss.pojo.ChatMessage;


public interface ChatMessageDao {
	
	public void deleteById(ChatMessage message);
	
	public void inseartChatMessage(ChatMessage message);
		
	public List<ChatMessage> findByPeople(ChatMessage message);
	
	public List<ChatMessage> findByLimit(ChatMessage message);
	
	public ChatMessage findById(ChatMessage message);
	
}
