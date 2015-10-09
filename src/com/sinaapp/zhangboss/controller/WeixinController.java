package com.sinaapp.zhangboss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinaapp.zhangboss.weixin.service.CoreService;
import com.sinaapp.zhangboss.weixin.util.SignUtil;



@Controller
public class WeixinController {


	@Resource(name="coreService")
	private CoreService coreService;
	
	/**
	 * 确认请求来自微信服务器
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	public void getEntrance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	public void postEntrance(String request, HttpServletResponse response) throws IOException {
		/*// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");*/

		// 调用核心业务类接收消息、处理消息
		String respMessage = this.coreService.processRequest(request);
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		
		out.close();
	}

	public CoreService getCoreService() {
		return coreService;
	}

	public void setCoreService(CoreService coreService) {
		this.coreService = coreService;
	}
}