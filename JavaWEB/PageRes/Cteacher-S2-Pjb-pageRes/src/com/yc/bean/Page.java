package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {


	private static final long serialVersionUID = 432734846662739826L;
	private boolean flag;

	private Integer pagesize;
	private Integer totalPage;

	private List<T> list;
	private int pageNo;
	public int getTotalPageNum(){//获取总页数
		
		return totalPage%pagesize!=0?totalPage/pagesize+1:totalPage/pagesize;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean getFlag() {
		return flag;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPrePage() {
		return (pageNo<=1?1:pageNo-1);
	}
	
	public Integer getNextPage() {
		return (pageNo<totalPage?pageNo+1:pageNo);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	

	
	public void setFlag(boolean flag) {
		this.flag=flag;
	}


}
