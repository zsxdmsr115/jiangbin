package com.yc.examin.biz;

import java.util.List;

import com.yc.examin.bean.Lesson;
import com.yc.examin.bean.Question;

public interface LessonBiz {
	//查询课程
  public List<Lesson> getAllLesson();
  
  //查询试题
  public List<Question> getQuestionById(int id);
  //添加试题
  public void addQuestion(Question qs);
}
