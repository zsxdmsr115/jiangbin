package com.yc.bbs.dao;

import java.util.List;

import com.yc.bbs.bean.PageBean;
import com.yc.bbs.bean.Reply;

public interface ReplyDao {


	int addReply(Reply rp);

	List<Reply> findReply(int topicid);
	public List<Reply> selectLimit(PageBean<Reply> page, String... params);



	int getTotalCount(String sql);

	int deleteReply(Integer replyid);

}
