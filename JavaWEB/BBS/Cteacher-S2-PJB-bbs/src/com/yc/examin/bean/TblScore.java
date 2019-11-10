package com.yc.examin.bean;

public class TblScore {
    private Integer uid;
    private Integer qid;
    private String completime;
    private String selfanswer;
    private Integer errorcode;
    private int nonum;
    
	
	public Integer getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}
	public int getNonum() {
		return nonum;
	}
	public void setNonum(int nonum) {
		this.nonum = nonum;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getCompletime() {
		return completime;
	}
	public void setCompletime(String completime) {
		this.completime = completime;
	}
	public String getSelfanswer() {
		return selfanswer;
	}
	public void setSelfanswer(String selfanswer) {
		this.selfanswer = selfanswer;
	}
}
