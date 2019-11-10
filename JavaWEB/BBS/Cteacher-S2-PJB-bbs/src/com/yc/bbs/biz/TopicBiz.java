package com.yc.bbs.biz;

import java.util.List;

import com.yc.bbs.bean.Topic;

public interface TopicBiz {
   public void addTopic(Topic t);
   //²éÑ¯Ìû×Ó
   public List<Topic> findTopicByTitle(String title);
}
