package com.yc.bbs.bean;

import java.io.Serializable;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2679393543900629170L;
	private Integer replyid;
	private String title;
	private String content;
	private String publishtime;
	private String modifytime;
	private Integer uid;
	private Integer topicid;
	private String uname;
	private String head;
	private String regtime;

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
		if (regtime.length() > 1 && regtime != null) {
			this.regtime = regtime.substring(0, 16);
		} else {
			this.regtime = "";
		}
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getReplyid() {
		return replyid;
	}

	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
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
		if (publishtime != null && publishtime.length() > 1) {
			this.publishtime = publishtime.substring(0, 16);
		} else {
			this.publishtime = "";
		}
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		if (modifytime != null && modifytime.length() > 1) {
			this.modifytime = modifytime.substring(0, 16);
		} else {
			this.modifytime = "";
		}

	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getTopicid() {
		return topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}

	@Override
	public String toString() {
		return "Reply [replyid=" + replyid + ", title=" + title + ", content=" + content + ", publishtime="
				+ publishtime + ", modifytime=" + modifytime + ", uid=" + uid + ", topicid=" + topicid + ", uname="
				+ uname + "]";
	}

}
