package com.yc.bbs.bean;

import java.io.Serializable;

public class Topic  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4358027205999608156L;
	private Integer topicid;
	private String title;;

	private String content;
	private String publishtime;
	private String modifytime;
	private Integer uid;
	private Integer boardid;
	private String uname;
	private String head;
	
	private String replcount;
	

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getReplcount() {
		return replcount;
	}

	public void setReplcount(String replcount) {
		this.replcount = replcount;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getTopicid() {
		return topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		if(publishtime.length()>1&& publishtime!=null){
			this.publishtime=publishtime.substring(0,16);
		}else{
			this.publishtime="";
		}
		
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		if( publishtime!=null && publishtime.length()>1){
			this.modifytime=modifytime.substring(0,16);
		}else{
			this.modifytime="";
		}
		
		
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getBoardid() {
		return boardid;
	}

	public void setBoardid(Integer boardid) {
		this.boardid = boardid;
	}

	@Override
	public String toString() {
		return "Topic [topicid=" + topicid + ", title=" + title + ", content=" + content + ", publishtime="
				+ publishtime + ", modifytime=" + modifytime + ", uid=" + uid + ", boardid=" + boardid + ", uname="
				+ uname + ", head=" + head + ", replcount=" + replcount + "]";
	}


	
}
