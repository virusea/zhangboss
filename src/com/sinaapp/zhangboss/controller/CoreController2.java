package com.sinaapp.zhangboss.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinaapp.zhangboss.aes.AesException;
import com.sinaapp.zhangboss.aes.WXBizMsgCrypt;
import com.sinaapp.zhangboss.weixin.service.CoreService;

@Controller
public class CoreController2 {
	private String token = "123456789weixin"; // ssitxihc@qq.com weixin123456789
	//private String encodingAESKey = "PGAeV5ImyjJ4lL8tQDGg7KHsRgTUkz5LJMHIUE9C69y";// haibo_lin@163.com
	private String encodingAESKey = "7H7U5el4Rf5yQAjeQK14f7IKuTQoaDSzxUxJXD99YOR";//ssitxihc@qq.com	 CorpID wxe4b0ecae5903230f
	//private String corpId = "wxe4b0ecae5903230f";
	private String corpId = "wxe4b0ecae5903230f";//

	@RequestMapping(value = "/coreJoin2", method = RequestMethod.GET)
	public void coreJoinGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 微信加密签名
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		System.out.println("request=" + request.getRequestURL());

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		String result = null;
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,
					corpId);
			result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
		} catch (AesException e) {
			e.printStackTrace();
		}
		if (result == null) {
			result = token;
		}
		out.print("悠惠科技企业号："+result);
		out.close();
		out = null;
	}

	@RequestMapping(value ="/coreJoin2", method = RequestMethod.POST)
	public void coreJoinPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 微信加密签名
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		
		//从请求中读取整个post数据
		InputStream inputStream = request.getInputStream();
		String postData = IOUtils.toString(inputStream, "UTF-8");
		System.out.println(postData);
		
		String msg = "";
		WXBizMsgCrypt wxcpt = null;
		try {
			wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
			//解密消息
			msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
		} catch (AesException e) {
			e.printStackTrace();
		}
		System.out.println("msg=" + msg);
		
		// 调用核心业务类接收消息、处理消息
		String respMessage = CoreService.processRequest(msg);
		System.out.println("respMessage=" + respMessage);
		
		String encryptMsg = "";
		try {
			//加密回复消息
			encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
		} catch (AesException e) {
			e.printStackTrace();
		}
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(encryptMsg);
		out.close();

	}
}
