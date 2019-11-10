package com.yc.bbs.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.Board;
import com.yc.bbs.bean.Topic;
import com.yc.bbs.dao.BoardDao;
import com.yc.util.DBHelper;

public class BoardDaoImpl  extends DBHelper implements BoardDao{
	
	/**
	 * 查找所有的板块
	 */
	@Override
	public Map<Integer, List<Board>> findAllBoard() {
		dbIndex=0;

		//String sql = "select tbl_board.boardid,boardname,parentid , count( topicid ) as total from tbl_board left join tbl_topic on tbl_board.boardid=tbl_topic.boardid group by tbl_board.boardid,boardname,parentid ";
		String sql="select a.boardid,boardname,parentid,total, topicid,title,modifytime,uname from (select tbl_board.boardid,boardname,parentid , count( topicid ) as total	from tbl_board	left join tbl_topic on tbl_board.boardid=tbl_topic.boardid group by tbl_board.boardid,boardname,parentid) a left join(	select topicid,title,a.modifytime,uname,a.boardid from	(	select topicid, title, modifytime, uname, boardid from tbl_topic left join tbl_user	on tbl_topic.uid=tbl_user.uid  ) a, (select boardid,max(modifytime) as modifytime from tbl_topic group by boardid) b where  a.boardid=b.boardid and a.modifytime=b.modifytime)b on a.boardid=b.boardid";
		
		try {
			List<Board> list = find(sql, Board.class);
			
			Map<Integer,List<Board>> map = new HashMap<Integer, List<Board>>();
			if(list==null || list.size()<=0){
				return map;
			}
			for (Board board : list) {
				 
				 Integer parentid = board.getParentid();
				
				 if(map.containsKey(parentid)){
					 /**
					  * 包含了父板块的parentid，的板块存入到map中
					  */
					 List<Board> sonList = map.get(parentid);
				     sonList.add(board);
				     
				 }else{
					 //剩余其他板块存入到map中
					 List<Board> sonList = new ArrayList<Board>();
					 sonList.add(board);
					 map.put(parentid, sonList);
				 }
			}
			return  map;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public List<Topic> findAllTopiByBoardIdLimit(int boardid, int pagNo, int pagesize,String...title) {

		dbIndex=0;
		int start = (pagNo-1) *pagesize;
	//	StringBuffer sql = new StringBuffer();
		  // sql.append("SELECT tp.topicid,tp.title,tp.content,tp.publishtime,tp.modifytime,tp.uid,tu.uname  FROM  tbl_topic tp ,tbl_user tu where boardid="+boardid+" and tp.uid=tu.uid").append(" LIMIT "+start+","+pagesize); 
		 
		String ss= "SELECT tp.topicid,tp.title,tp.content,tp.publishtime,tp.modifytime,tp.uid,tu.uname,tu.head, COUNT(tr.topicid) replcount FROM tbl_topic tp  LEFT JOIN tbl_reply tr ON  tp.topicid=tr.topicid LEFT JOIN tbl_user tu ON tp.uid=tu.uid  WHERE tp.boardid='"+boardid+"'  ";
				
			if(title.length>0){
		    	ss+=" and tp.title like '%"+title[0]+"%'"; 
		    }
		    ss+=" GROUP BY tp.topicid  order by tp.modifytime desc LIMIT "+start+","+pagesize;
		   
		   try {
			 //  System.out.println("topic==="+find(ss, Topic.class));
		return	find(ss, Topic.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public List<Topic> findAllTopiByBoardIdLimitByTitle(int boardid, int pagNo, int pagesize,String title){
		return findAllTopiByBoardIdLimit(boardid, pagNo, pagesize, title);
	}

	@Override
	public Board findBoardById(int bid) {
		dbIndex=0;
		String sql = "select * from tbl_board where boardid=?";
		try {
			List<Board> bds = find(sql, Board.class,bid);
			return bds.get(0);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public double topicCountByBoardId(Integer boardId) {
		dbIndex=0;
		List<Object> params = new ArrayList<>();
		String sql="SELECT COUNT(boardid) FROM tbl_topic WHERE boardid=?";
		params.add(boardId);
		
		return getCount(sql, params);
	}

}
