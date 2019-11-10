package com.yc.bbs.biz;

import java.util.List;

import com.yc.bbs.bean.PageBean;
import com.yc.bbs.bean.Reply;

public interface ReplyBiz {
   public int addReply(Reply ry);
   public List<Reply> findReply(int topicid);
   public List<Reply> selectLimit(PageBean<Reply> page, String... params);
   public int getCount();
   public int deleteReply(int replyid);
}
