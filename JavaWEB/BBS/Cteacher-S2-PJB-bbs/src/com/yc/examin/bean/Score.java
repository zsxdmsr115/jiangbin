package com.yc.examin.bean;

public class Score {
	private Integer id;
	private Integer score;
	private Integer lessonid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getLessonid() {
		return lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", score=" + score + ", lessonid=" + lessonid + "]";
	}

}
