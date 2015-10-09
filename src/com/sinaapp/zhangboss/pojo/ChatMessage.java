package com.sinaapp.zhangboss.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ChatMessage {
	
	@NotNull(message="id 不能为空。")
	private long id;
	
	private Date time;
	private String people;
	private String content;
	private String nextContents;
	
	//表的后缀
	private String sufixstr;

	public String getSufixstr() {
		return sufixstr;
	}
	public void setSufixstr(String sufixstr) {
		this.sufixstr = sufixstr;
	}
	
	//limit查询时候的开始和num
	private int start;
	private int num;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNextContents() {
		return nextContents;
	}
	public void setNextContents(String nextContents) {
		this.nextContents = nextContents;
	}

	public String toString(){
		return id+" "+time+" "+people+" "+content+" "+nextContents;
	}
}
