package com.yc.examin.biz.impl;

import java.util.List;

import com.yc.examin.bean.Lesson;
import com.yc.examin.bean.Question;
import com.yc.examin.biz.LessonBiz;
import com.yc.examin.dao.LessonDao;
import com.yc.examin.dao.impl.LessonDaoImpl;

public class LessonBizeImp implements LessonBiz {
    LessonDao ldao = new LessonDaoImpl();
	@Override
	public List<Lesson> getAllLesson() {
		return ldao.getAllLesson();
	}

	@Override
	public List<Question> getQuestionById(int id) {
		return ldao.getQuestionById(id);
	}

	@Override
	public void addQuestion(Question qs) {
		   ldao.addQuestion( qs);
	}

}
