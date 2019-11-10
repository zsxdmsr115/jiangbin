package com.yc.bbs.biz.impl;

import java.util.List;

import com.yc.bbs.bean.PageBean;
import com.yc.bbs.bean.Reply;
import com.yc.bbs.biz.ReplyBiz;
import com.yc.bbs.dao.ReplyDao;
import com.yc.bbs.dao.impl.ReplyDaoImpl;
import com.yc.util.DBHelper;

public class ReplyBizImpl extends DBHelper implements ReplyBiz {
      ReplyDao rd = new ReplyDaoImpl();
	@Override
	public int addReply(Reply rp) {
		return rd.addReply(rp);
	}
	@Override
	public List<Reply> findReply(int topicid) {
		return rd. findReply( topicid);
	}
	public List<Reply> selectLimit(PageBean<Reply> page, String... params){
		return rd.selectLimit(page, params);
	}
	@Override
	public int getCount() {
		String sql="select count(*)  from tbl_reply";
		return rd.getTotalCount(sql);
	}
	@Override
	public int deleteReply(int replyid) {
		
		return rd.deleteReply(replyid);
	}
}
