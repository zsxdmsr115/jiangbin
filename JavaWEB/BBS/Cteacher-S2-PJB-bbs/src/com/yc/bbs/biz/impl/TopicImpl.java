package com.yc.bbs.biz.impl;

import java.util.List;

import com.yc.bbs.bean.Topic;
import com.yc.bbs.biz.TopicBiz;
import com.yc.bbs.dao.TopicDao;
import com.yc.bbs.dao.impl.TopicDaoImpl;


public class TopicImpl implements TopicBiz {
	TopicDao td = new TopicDaoImpl();
	@Override
	public void addTopic(Topic t) {
       td.addTopic(t);
	}
	@Override
	public List<Topic> findTopicByTitle(String title) {
		return td.findTopicByTitle(title);
	}

}
