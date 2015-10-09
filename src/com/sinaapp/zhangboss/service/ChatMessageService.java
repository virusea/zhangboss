package com.sinaapp.zhangboss.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ansj.domain.Term;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinaapp.zhangboss.dao.ChatMessageDao;
import com.sinaapp.zhangboss.dao.TermMessageDao;
import com.sinaapp.zhangboss.dao.UserDao;
import com.sinaapp.zhangboss.dao.WXMessageDao;
import com.sinaapp.zhangboss.helper.ANSJSegHelper;
import com.sinaapp.zhangboss.helper.GetMessageFromAI;
import com.sinaapp.zhangboss.pojo.ChatMessage;
import com.sinaapp.zhangboss.pojo.TermMessage;
import com.sinaapp.zhangboss.pojo.User;
import com.sinaapp.zhangboss.pojo.UserTextMessage;

@Service("chatMessageService")
public class ChatMessageService {
	
	@Resource(name="chatMessageDao")
	private ChatMessageDao dao;


	public ChatMessageDao getDao() {
		return dao;
	}

	public void setDao(ChatMessageDao dao) {
		this.dao = dao;
	}
	
	
	@Resource(name="termMessageDao")
	private TermMessageDao tmdao;

	public TermMessageDao getTmdao() {
		return tmdao;
	}

	public void setTmdao(TermMessageDao tmdao) {
		this.tmdao = tmdao;
	}

	public String getZBZDRMessage(String message,String surfix) throws IOException{
		//首先解析单词并指导其单词的次数
		Map<String,Integer> termTimes = new HashMap<String, Integer>();
		List<Term> terms = ANSJSegHelper.parseStr(message);
		if(terms != null){
			for(Term t : terms){
				String name = t.getName();
				if(termTimes.containsKey(name)){
					termTimes.put(name, termTimes.get(name)+1);
				}else{
					termTimes.put(name, 1);
				}
			}
		}
		
		//遍历Map，然后把从不同的单词-对话表中获取到所有的messageid
		long targetMessageId = -1; //记录查询到的话语
		int maxNum = -1; //记录最大出现次数
		Map<Long,Integer> messageTimes = new HashMap<Long, Integer>();
		
		
		for(Map.Entry<String, Integer> item : termTimes.entrySet()){
			String term = item.getKey();
			int termTs = item.getValue();
			int termLength = term.length();
			
			TermMessage newTM = new TermMessage();
			newTM.setTerm(term);
			switch (termLength) {
				case 1:
					break;
				case 2:
					int termHash = term.hashCode()%100;
					newTM.setSufixstr(surfix+"_two_"+termHash);
					List<TermMessage> twoResult = this.tmdao.findByTerm(newTM);
					for(TermMessage tm : twoResult){
						long mid = tm.getMessageid();
						if(messageTimes.get(mid) != null){
							int newVal = messageTimes.get(mid)+1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}else{
							int newVal = 1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}
					}
					
					break;
				
				case 3:
					newTM.setSufixstr(surfix+"_three");
					List<TermMessage> threeResult = this.tmdao.findByTerm(newTM);
					for(TermMessage tm : threeResult){
						long mid = tm.getMessageid();
						if(messageTimes.get(mid) != null){
							int newVal = messageTimes.get(mid)+1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}else{
							int newVal = 1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}
					}
					break;
					
				case 4:
					newTM.setSufixstr(surfix+"_four");
					List<TermMessage> fourResult = this.tmdao.findByTerm(newTM);
					for(TermMessage tm : fourResult){
						long mid = tm.getMessageid();
						if(messageTimes.get(mid) != null){
							int newVal = messageTimes.get(mid)+1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}else{
							int newVal = 1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}
					}
					break;

				case 5:
					newTM.setSufixstr(surfix+"_five");
					List<TermMessage> fiveResult = this.tmdao.findByTerm(newTM);
					for(TermMessage tm : fiveResult){
						long mid = tm.getMessageid();
						if(messageTimes.get(mid) != null){
							int newVal = messageTimes.get(mid)+1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}else{
							int newVal = 1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}
					}
					break;
					
				default:
					newTM.setSufixstr(surfix+"_other");
					List<TermMessage> otherResult = this.tmdao.findByTerm(newTM);
					for(TermMessage tm : otherResult){
						long mid = tm.getMessageid();
						if(messageTimes.get(mid) != null){
							int newVal = messageTimes.get(mid)+1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}else{
							int newVal = 1;
							messageTimes.put(mid, newVal);
							if(maxNum < newVal){
								maxNum = newVal;
								targetMessageId = mid;
							}
						}
					}
					break;
			}	
		}
		//根据不同的单词项获取每一个词语的所有messageid
		ChatMessage searchMessage = new ChatMessage();
		searchMessage.setId(targetMessageId);
		searchMessage.setSufixstr(surfix);
		ChatMessage curMessage = this.dao.findById(searchMessage);
		if(curMessage != null){
			long duifangMessage = Long.parseLong(curMessage.getNextContents());
			searchMessage.setId(duifangMessage);
			searchMessage.setSufixstr("zdr");
			ChatMessage duifangMessObj = this.dao.findById(searchMessage);
			if(duifangMessObj != null){
				return duifangMessObj.getContent();
			}else{
				return "张老板在思考。";
			}
		}
		
		return "张老板睡觉中。";
	}
	
	public String getTuLingMessage(String message) throws IOException{
		return GetMessageFromAI.getTULINGRespMessage(message);
	}
	
	public String getCHSIMIMessage(String message) throws IOException{
		return GetMessageFromAI.getCHSimiRespMessage(message);
	}
	
	public String getENSIMIMessage(String message) throws IOException{
		return GetMessageFromAI.getENSimiRespMessage(message);
	}

	@Transactional
	public void deleteById(ChatMessage message) {
		dao.deleteById(message);
	}

	@Transactional
	public void inseartChatMessage(ChatMessage message) {
		dao.inseartChatMessage(message);	
	}

	@Transactional
	public List<ChatMessage> findByPeople(ChatMessage message) {
		return dao.findByPeople(message);
	}
	
	@Transactional
	public List<ChatMessage> findByLimit(ChatMessage message) {
		return dao.findByLimit(message);
	}
	

}
