package com.yc.bbs.dao.impl;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yc.bbs.bean.Topic;
import com.yc.bbs.dao.TopicDao;
import com.yc.util.DBHelper;

public class TopicDaoImpl extends DBHelper implements TopicDao {

	@Override
	public void addTopic(Topic t) {
		dbIndex=0;
		String sql ="insert into tbl_topic values(null,?,?,now(),now(),?,?)";
		List<Object> paramsList = new ArrayList<>();
		 paramsList.add(t.getTitle());
		 paramsList.add(t.getContent());
		
		 paramsList.add(t.getUid());
		 paramsList.add(t.getBoardid());
		 doUpdate(sql, paramsList);
		
		}

	@Override
	public List<Topic> findTopicByTitle(String title) {
		dbIndex=0;
		String sql = "SELECT tp.title,tuser.uname,tuser.head,COUNT(tr.replyid) replcount FROM tbl_topic tp LEFT JOIN tbl_reply tr ON tp.topicid = tr.topicid LEFT JOIN tbl_user tuser ON tp.uid=tuser.uid WHERE tp.title LIKE '%"+title+"%' GROUP BY tp.topicid,tp.title,tuser.uname,tuser.head ";
		 try {
			 List<Topic> list = find(sql, Topic.class);
			 System.out.println("³¤¶È="+list.size());
		return	find(sql, Topic.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
