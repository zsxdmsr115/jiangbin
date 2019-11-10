package com.yc.bbs.dao;

import java.util.List;

import com.yc.bbs.bean.Topic;

public interface TopicDao {
	 public void addTopic(Topic t);
	 public List<Topic> findTopicByTitle(String title);
}
