package com.sinaapp.zhangboss.dao.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sinaapp.zhangboss.dao.ChatMessageDao;
import com.sinaapp.zhangboss.dao.SuperDao;
import com.sinaapp.zhangboss.dao.TermMessageDao;
import com.sinaapp.zhangboss.pojo.ChatMessage;
import com.sinaapp.zhangboss.pojo.TermMessage;


@Component("termMessageDao")
public class TermMessageDaoImpl extends SuperDao implements TermMessageDao {


	@Override
	public void deleteById(TermMessage message) {
		this.template.delete("TermMessage.deleteById", message);
		
	}

	@Override
	public void inseartTermMessage(TermMessage message) {
		this.template.insert("TermMessage.inseartTermMessage", message);
		
	}

	@Override
	public List<TermMessage> findByTerm(TermMessage message) {
		return this.template.selectList("TermMessage.findByTerm", message);
	}


	
}
