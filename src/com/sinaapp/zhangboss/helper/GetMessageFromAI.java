package com.sinaapp.zhangboss.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class GetMessageFromAI {

	private static String TULING_URL = "http://www.tuling123.com/openapi/api?key=b1fe76372c99d7d86215c547ab2869fd&info=";
	private static String SIMSIMI_URL = "http://sandbox.api.simsimi.com/request.p?key=0275679a-f941-4ae8-8a9f-d8c0de252cd3&lc=ch&ft=1.0&text=";
	private static String SIMSIMI_EN_URL = "http://sandbox.api.simsimi.com/request.p?key=0275679a-f941-4ae8-8a9f-d8c0de252cd3&lc=en&ft=1.0&text=";	
	
	public static String getTULINGRespMessage(String userMessage) throws IOException{
		
	    String INFO = URLEncoder.encode(userMessage, "utf-8"); 
	    String getURL = TULING_URL + INFO; 
	    URL getUrl = new URL(getURL); 
	    
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    connection.connect(); 

	    // 取得输入流，并使用Reader读取 
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
	    StringBuffer sb = new StringBuffer(); 
	    String line = ""; 
	    while ((line = reader.readLine()) != null) { 
	        sb.append(line); 
	    } 
	    reader.close(); 
	   	    
	    String tulingRes = sb.toString();	    
	    JSONObject json=JSONObject.fromObject(tulingRes);
	    String realTLResp = json.getString("text");

	    // 断开连接 
	    connection.disconnect(); 
    
	    return realTLResp;		
	}
	
	
	public static String getCHSimiRespMessage(String userMessage) throws IOException{
		
	    String INFO = URLEncoder.encode(userMessage, "utf-8"); 
	    String getURL = SIMSIMI_URL + INFO; 
	    URL getUrl = new URL(getURL); 
	    
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    connection.connect(); 

	    // 取得输入流，并使用Reader读取 
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
	    StringBuffer sb = new StringBuffer(); 
	    String line = ""; 
	    while ((line = reader.readLine()) != null) { 
	        sb.append(line); 
	    } 
	    reader.close(); 
	   	    
	    String simiRes = sb.toString();	    
	    JSONObject json=JSONObject.fromObject(simiRes);
	    String realSIMIResp = "我生病了，现在无言以对！ ^_^";
	    if(json.containsKey("response")){
	    	realSIMIResp = json.getString("response");
	    }

	    // 断开连接 
	    connection.disconnect(); 
    
	    return realSIMIResp;		
	}
	
	
	public static String getENSimiRespMessage(String userMessage) throws IOException{
		
	    String INFO = URLEncoder.encode(userMessage, "utf-8"); 
	    String getURL = SIMSIMI_EN_URL + INFO; 
	    URL getUrl = new URL(getURL); 
	    
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    connection.connect(); 

	    // 取得输入流，并使用Reader读取 
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
	    StringBuffer sb = new StringBuffer(); 
	    String line = ""; 
	    while ((line = reader.readLine()) != null) { 
	        sb.append(line); 
	    } 
	    reader.close(); 
	   	    
	    String simiRes = sb.toString();	    
	    JSONObject json=JSONObject.fromObject(simiRes);
	    String realSIMIResp = "我生病了，现在无言以对！ ^_^";
	    if(json.containsKey("response")){
	    	realSIMIResp = json.getString("response");
	    }

	    // 断开连接 
	    connection.disconnect(); 
    
	    return realSIMIResp;		
	}
	
}
