package com.yc.bbs.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int uid;
	private String uname;
	private String upass;
	private String head;
	private String regtime;
	private int gender;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		if(regtime.length()>1&& regtime!=null){
			this.regtime=regtime.substring(0,16);
		}else{
			this.regtime="";
		}
		
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upass=" + upass + ", head=" + head + ", regtime=" + regtime
				+ ", gender=" + gender + "]";
	}
	

}
