package com.yc.bean;

import java.io.Serializable;

public class Resorderitem implements Serializable {
	private static final long serialVersionUID = 4415732811555252222L;
	private int roiid ;
	private int roid ;
	private int fid   ;
	private double dealprice ;
	private int num ;
	private String address;
	private String fname;
	private double subtotal;
	
	public double getSubtotal() {
		return  this.subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getRoiid() {
		return roiid;
	}
	public void setRoiid(int roiid) {
		this.roiid = roiid;
	}
	public int getRoid() {
		return roid;
	}
	public void setRoid(int roid) {
		this.roid = roid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public double getDealprice() {
		return dealprice;
	}
	public void setDealprice(double dealprice) {
		this.dealprice = dealprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Resorderitem [roiid=" + roiid + ", roid=" + roid + ", fid=" + fid + ", dealprice=" + dealprice
				+ ", num=" + num + "]";
	}
	
	
}
