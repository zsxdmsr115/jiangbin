package com.yc.examin.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.yc.examin.bean.Lesson;
import com.yc.examin.bean.Question;
import com.yc.examin.dao.LessonDao;
import com.yc.util.DBHelper;


public class LessonDaoImpl extends DBHelper implements LessonDao {
	
	@Override
	public List<Lesson> getAllLesson() {
		 dbIndex=1;
		String sql ="select * from tb_lesson";
	
		try {
			return 	find(sql,Lesson.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<Question>  getQuestionById(int id) {
		 dbIndex=1;
		 String sql ="SELECT  qid,subject,type,joinTime,lessonid,taotiid ,optiona,optionb,optionc,optiond,answer,note FROM tb_questions WHERE lessonid=?";
		try {
			
			return find(sql, Question.class, id);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public void addQuestion(Question qs) {
		 dbIndex=1;
		 String sql ="insert into tb_questions values(null,?,?,now(),?,?,?,?,?,?,?,?)";
		  List<Object> params = new ArrayList<Object>();
		  params.add(qs.getSubject());
		  params.add(qs.getType());
		 
		  params.add(qs.getLessonid());
		  params.add(1);
		//  params.add(qs.getTaotiid());
		  params.add(qs.getOptiona());
		  params.add(qs.getOptionb());
		  params.add(qs.getOptionc());
		  params.add(qs.getOptiond());
		  params.add(qs.getAnswer());
          params.add(qs.getNote());
		  doUpdate(sql, params);
	}
	

}
