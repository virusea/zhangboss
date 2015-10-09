package com.sinaapp.zhangboss.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinaapp.zhangboss.dao.TermMessageDao;
import com.sinaapp.zhangboss.pojo.TermMessage;


@Service("termMessageService")
public class TermChatMessageService {
	
	@Resource(name="termMessageDao")
	private TermMessageDao dao;


	public TermMessageDao getDao() {
		return dao;
	}

	public void setDao(TermMessageDao dao) {
		this.dao = dao;
	}

		
	@Transactional
	public void deleteById(TermMessage message) {
		dao.deleteById(message);
		
	}

	@Transactional
	public void inseartTermMessage(TermMessage message) {
		dao.inseartTermMessage(message);
		
	}

	@Transactional
	public List<TermMessage> findByTerm(TermMessage message) {
		return dao.findByTerm(message);
	}
	

}
