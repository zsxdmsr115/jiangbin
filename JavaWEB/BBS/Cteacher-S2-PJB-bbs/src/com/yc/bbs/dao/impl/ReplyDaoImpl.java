package com.yc.bbs.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bbs.bean.PageBean;
import com.yc.bbs.bean.Reply;
import com.yc.bbs.dao.ReplyDao;
import com.yc.util.DBHelper;
 
public class ReplyDaoImpl  extends DBHelper implements ReplyDao {

	

	@Override
	public int addReply(Reply rp) {
		dbIndex=0;
		String sql = "insert into tbl_reply  values(null,?,?,now(),now(),?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(rp.getTitle());
		list.add(rp.getContent());
		
		list.add(rp.getUid());
		list.add(rp.getTopicid());
		return	doUpdate(sql, list);
	}

	@Override
	public List<Reply> findReply(int topicid) {
		dbIndex=0;
		String sql = "SELECT  tr.*, tu.uname,tu.head,tu.regtime FROM   tbl_reply tr LEFT JOIN tbl_user tu ON   tr.uid=tu.uid   WHERE  topicid=?";
		try {
		List<Reply> list = 	find(sql, Reply.class,topicid);
		return list;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public List<Reply> selectLimit(PageBean<Reply> page, String... params) {
		List<Reply> rlist = null;
		try {
			String sql = "SELECT tr.replyid,tr.title,tr.content,tr.publishtime, tr.modifytime, tr.uid, tr.topicid ,tu.uname,tu.head,tu.regtime "
					+ "FROM tbl_reply tr,tbl_user tu WHERE tr.uid=tu.uid AND  1=1";
			if (params != null && params.length!=0 ) {
				sql += " and  tr.topicid =?  ";
			} 
			if (page.getFlag()) {
              
				int start = (page.getPageNo() - 1) * page.getPagesize();
				sql += " ORDER BY  tr.modifytime desc  limit " + start + "," + page.getPagesize();
			}
			
		    
		   
			rlist = find(sql, Reply.class,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;

	}

	@Override
	public int getTotalCount(String sql) {
		return  (int) getCount(sql, null);
	}

	@Override
	public int deleteReply(Integer replyid) {
		String sql ="delete from tbl_reply where replyid=?";
		List params = new ArrayList();
		params.add(replyid);
		
		return doUpdate(sql,params );
	}

  
}
