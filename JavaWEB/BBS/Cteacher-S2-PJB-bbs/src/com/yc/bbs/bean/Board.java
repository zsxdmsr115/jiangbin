package com.yc.bbs.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	private int boardid;
	private String boardname;
	private int parentid;
	private String uname;
	private String modifytime;
	
	
	private int total;
	private String title;
	
	private String author;
	
	

	public String getModifytime() {
	
	 
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		 
		  if(modifytime!=null && modifytime.length()>1){
			  this.modifytime= modifytime.substring(0,16);
		  }else{
			  this.modifytime="";
		  }
	}
	public String getAuthor(){
		author = getUname();
		return author;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public String getBoardname() {
		return boardname;
	}

	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
