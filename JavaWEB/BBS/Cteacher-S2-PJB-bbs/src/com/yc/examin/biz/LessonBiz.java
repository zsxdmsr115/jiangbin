package com.yc.examin.biz;

import java.util.List;

import com.yc.examin.bean.Lesson;
import com.yc.examin.bean.Question;

public interface LessonBiz {
	//��ѯ�γ�
  public List<Lesson> getAllLesson();
  
  //��ѯ����
  public List<Question> getQuestionById(int id);
  //�������
  public void addQuestion(Question qs);
}
