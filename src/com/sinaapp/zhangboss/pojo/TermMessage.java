package com.sinaapp.zhangboss.pojo;

import javax.validation.constraints.NotNull;

public class TermMessage {
	
	@NotNull(message="id 不能为空。")
	private long id;
	
	private String term;
	private long messageid;
	private int termtimes;
	
	//表的后缀
	private String sufixstr;

	public String getSufixstr() {
		return sufixstr;
	}
	public void setSufixstr(String sufixstr) {
		this.sufixstr = sufixstr;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public long getMessageid() {
		return messageid;
	}
	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}
	public int getTermtimes() {
		return termtimes;
	}
	public void setTermtimes(int termtimes) {
		this.termtimes = termtimes;
	}
	
	
}
