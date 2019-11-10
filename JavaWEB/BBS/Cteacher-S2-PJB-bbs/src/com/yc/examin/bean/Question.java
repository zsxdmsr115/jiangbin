package com.yc.examin.bean;

public class Question {
	private Integer qid;
	private String subject;
	private String type;
	private String joinTime;
	private Integer lessonid;
	private Integer taotiid;
	private String optiona;
	private String optionb;
	private String optionc;
	private String optiond;
	private String answer;
	

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	private String note;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		if (joinTime != null) {
			this.joinTime = joinTime.substring(0, 16);
		} else {
			joinTime = "";
		}

	}

	public Integer getLessonid() {
		return lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	public Integer getTaotiid() {
		return taotiid;
	}

	public void setTaotiid(Integer taotiid) {
		this.taotiid = taotiid;
	}

	public String getOptiona() {
		return optiona;
	}

	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}

	public String getOptionb() {
		return optionb;
	}

	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}

	public String getOptionc() {
		return optionc;
	}

	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}

	public String getOptiond() {
		return optiond;
	}

	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Question [qid=" + qid + ", subject=" + subject + ", type=" + type + ", joinTime=" + joinTime
				+ ", lessonid=" + lessonid + ", taotiid=" + taotiid + ", optiona=" + optiona + ", optionb=" + optionb
				+ ", optionc=" + optionc + ", optiond=" + optiond + ", answer=" + answer + ", note=" + note + "]";
	}

	
	

}
