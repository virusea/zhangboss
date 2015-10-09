package com.sinaapp.zhangboss.pojo;

import javax.validation.constraints.NotNull;

public class UserTextMessage {
	
	@NotNull(message="id 不能为空。")
	private long id;
	
	private String fromuserid;
	private String content;
	private String delivertime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromuserid() {
		return fromuserid;
	}
	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDelivertime() {
		return delivertime;
	}
	public void setDelivertime(String delivertime) {
		this.delivertime = delivertime;
	}

}
