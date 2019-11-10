package com.yc.examin.dao;

import java.util.List;

import com.yc.examin.bean.Lesson;
import com.yc.examin.bean.Question;

public interface LessonDao {
	 public List<Lesson> getAllLesson();



	public List<Question> getQuestionById(int id);



	public void addQuestion(Question qs);
}
