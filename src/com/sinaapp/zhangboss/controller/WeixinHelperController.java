package com.sinaapp.zhangboss.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ansj.domain.Term;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sinaapp.zhangboss.helper.ANSJSegHelper;
import com.sinaapp.zhangboss.helper.TimeTransfer;
import com.sinaapp.zhangboss.pojo.ChatMessage;
import com.sinaapp.zhangboss.pojo.TermMessage;
import com.sinaapp.zhangboss.pojo.User;
import com.sinaapp.zhangboss.service.ChatMessageService;
import com.sinaapp.zhangboss.service.TermChatMessageService;
import com.sinaapp.zhangboss.service.WXTextMessageService;
import com.sinaapp.zhangboss.weixin.service.CoreService;
import com.sinaapp.zhangboss.weixin.util.SignUtil;


@Controller
public class WeixinHelperController {
	
	@Resource(name="chatMessageService")
	private ChatMessageService cmService;
	
	@Resource(name="termMessageService")
	private TermChatMessageService tcmService;
	
	
	@RequestMapping("/uploadfilepage")
	public ModelAndView uploadFilePage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("uploadchatfile");
		return mv;
	}

	@RequestMapping("/chatwithaipage")
	public ModelAndView getChatAIPage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("chatwithai");
		return mv;
	}	
	
	
	
	@RequestMapping("/fenci")
	@ResponseBody
	public String startFenci(@RequestParam("surfix")String surfix,@RequestParam("lines")int lines){
		return "分词已经结束，等待后续工作吧。";
//		Date startTime = new Date();
//		boolean flag = true;
//		int first = 0;
//		Map<String,Integer> termTimes;
//		while(flag){
//			
//			if(first > lines){
//				flag = false;
//				break;
//			}
//			
//			//下面是循环获取500句的代码
//			//获取多梦无忧数据库里面的每一句话
//			ChatMessage message = new ChatMessage();
//			message.setStart(first);
//			message.setNum(500);
//			message.setSufixstr(surfix);
//			List<ChatMessage> limitMessages = this.cmService.findByLimit(message);
//			//对每一句话进行分词，根据分词后词项的长短存入对应的数据库中
//			if(limitMessages != null){
//				for(ChatMessage cm : limitMessages){
//					long messageid = cm.getId();
//					List<Term> terms = ANSJSegHelper.parseStr(cm.getContent());
//					termTimes = new HashMap<String, Integer>();
//					if(terms != null){
//						for(Term t : terms){
//							String name = t.getName();
//							if(termTimes.containsKey(name)){
//								termTimes.put(name, termTimes.get(name)+1);
//							}else{
//								termTimes.put(name, 1);
//							}
//						}
//					}
//					//遍历Map，然后把不同的分词存入不同词语-对话表中
//					for(Map.Entry<String, Integer> item : termTimes.entrySet()){
//						String term = item.getKey();
//						int termTs = item.getValue();
//						int termLength = term.length();
//						TermMessage newTM = new TermMessage();
//						newTM.setMessageid(messageid);
//						newTM.setTerm(term);
//						newTM.setTermtimes(termTs);
//						switch (termLength) {
//							case 1:
//								newTM.setSufixstr(surfix+"_one");
//								break;
//		
//							case 2:
//								int termHash = term.hashCode()%100;
//								newTM.setSufixstr(surfix+"_two_"+termHash);
//								this.tcmService.inseartTermMessage(newTM);
//								break;
//							
//							case 3:
//								newTM.setSufixstr(surfix+"_three");
//								this.tcmService.inseartTermMessage(newTM);
//								break;
//								
//							case 4:
//								newTM.setSufixstr(surfix+"_four");
//								this.tcmService.inseartTermMessage(newTM);
//								break;
//		
//							case 5:
//								newTM.setSufixstr(surfix+"_five");
//								this.tcmService.inseartTermMessage(newTM);
//								break;
//								
//							default:
//								newTM.setSufixstr(surfix+"_other");
//								this.tcmService.inseartTermMessage(newTM);
//								break;
//						}	
//					}
//
//				}
//			}
//			first += 500;
//		}
//		Date endTime = new Date();
//		return "fenci success! spend time:"+((endTime.getTime()-startTime.getTime())/1000/60)+" minutes";
	}	
	

	@RequestMapping("/chatwithai")
	public ModelAndView chatWithAI(@RequestParam("message")String message,@RequestParam("fromuser")String fromuser) throws IOException{
		ModelAndView mv=new ModelAndView();
		List<String> aiResps = new ArrayList<String>();
		aiResps.add(cmService.getZBZDRMessage(message,fromuser));
		aiResps.add(cmService.getTuLingMessage(message));
		aiResps.add(cmService.getCHSIMIMessage(message));
		aiResps.add(cmService.getENSIMIMessage(message));
		mv.addObject("aiResps", aiResps);
		mv.addObject("message", message);
		mv.setViewName("chatwithai");
		return mv;
	}
	

	@RequestMapping(value = "/uploadchatfile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadChatFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException, ParseException {
		String pathDir = "/chatfiles/";
		String localPath = request.getSession().getServletContext().getRealPath(pathDir);
		
		File file = new File(localPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		MultipartFile multipartFile = request.getFile("chatFile");		
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String name = System.currentTimeMillis() + suffix;
		String fileName = localPath + File.separator + name; 
		File saveFile = new File(fileName);
		
		try {  
			multipartFile.transferTo(saveFile);  
        } 
		catch (IllegalStateException e) {  
			e.printStackTrace();
			return "wrong";
        } 
		catch (IOException e) {  
			e.printStackTrace();
			return "wrong";
		}
		
		//通过以上方式已经将上传的文件保存到服务器端  下面就是解读文件的内容
		BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8")); 
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	if(tempString.isEmpty()){
            		continue;
            	}else if(tempString.startsWith("2014-") || tempString.startsWith("2015-")){
            		
            		String time = tempString.substring(1, 20);
            		String people = tempString.substring(20);
            		String content = reader.readLine();
            		String realContent = (content != null && content.isEmpty())?null:content;
            		System.out.println(realContent);
            		if(realContent != null && realContent.length() < 255){
            			ChatMessage newMessage = new ChatMessage();
            			newMessage.setId(line);
            			newMessage.setContent(realContent);
            			newMessage.setNextContents(String.valueOf(line+1));
            			newMessage.setPeople(people);
            			//通过设置后缀决定调用哪个数据表
            			//newMessage.setSufixstr("test");
            			newMessage.setTime(TimeTransfer.getDateByString(time));
            			this.cmService.inseartChatMessage(newMessage);
            		}
            		line++;
            	
            	}
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

		
		//删除文件
		if (saveFile.exists()) {
			saveFile.delete();
		}
		
		return "success";
	}



	public ChatMessageService getCmService() {
		return cmService;
	}

	public void setCmService(ChatMessageService cmService) {
		this.cmService = cmService;
	}

	public TermChatMessageService getTcmService() {
		return tcmService;
	}

	public void setTcmService(TermChatMessageService tcmService) {
		this.tcmService = tcmService;
	}

}