package com.yc.examin.bean;

public class Lesson {
	private Integer id;
	private String name;
	private String joinTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	@Override
	public String toString() {
		return "Lesson [id=" + id + ", name=" + name + ", joinTime=" + joinTime + "]";
	}
	

	
	
}
