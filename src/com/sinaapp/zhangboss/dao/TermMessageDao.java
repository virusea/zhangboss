package com.sinaapp.zhangboss.dao;

import java.util.List;

import com.sinaapp.zhangboss.pojo.ChatMessage;
import com.sinaapp.zhangboss.pojo.TermMessage;


public interface TermMessageDao {
	
	public void deleteById(TermMessage message);
	
	public void inseartTermMessage(TermMessage message);
		
	public List<TermMessage> findByTerm(TermMessage message);
	
}
