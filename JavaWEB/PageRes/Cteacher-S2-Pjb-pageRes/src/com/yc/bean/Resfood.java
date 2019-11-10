package com.yc.bean;

import java.io.Serializable;

public class Resfood implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int fid;
	private String fname;//��Ʒ��
	private double normprice;//���ۼ�
	private double realprice;//ԭ��
	private String detail;//����
	private String fphoto;//ͼƬ
	
	private Long parise;//���µĵ�����
	private Long visited;//���ʴ���

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
