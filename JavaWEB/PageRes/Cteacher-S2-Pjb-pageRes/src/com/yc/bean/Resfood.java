package com.yc.bean;

import java.io.Serializable;

public class Resfood implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int fid;
	private String fname;//菜品名
	private double normprice;//打折价
	private double realprice;//原价
	private String detail;//详情
	private String fphoto;//图片
	
	private Long parise;//最新的点赞数
	private Long visited;//访问次数

	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getNormprice() {
		return normprice;
	}
	public void setNormprice(double normprice) {
		this.normprice = normprice;
	}
	public double getRealprice() {
		return realprice;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFphoto() {
		return fphoto;
	}
	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}
	public Long getParise() {
		return parise;
	}
	public void setParise(Long parise) {
		this.parise = parise;
	}
	public Long getVisited() {
		return visited;
	}
	public void setVisited(Long visited) {
		this.visited = visited;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Resfood [fid=" + fid + ", fname=" + fname + ", normprice=" + normprice + ", realprice=" + realprice
				+ ", detail=" + detail + ", fphoto=" + fphoto + ", parise=" + parise + ", visited=" + visited + "]\n";
	}
	
}
