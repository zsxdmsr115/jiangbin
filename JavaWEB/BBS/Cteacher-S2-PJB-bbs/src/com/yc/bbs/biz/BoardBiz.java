package com.yc.bbs.biz;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.Board;
import com.yc.bbs.bean.Topic;

public interface BoardBiz {
	/**
	 * 查找所有板块
	 * @return
	 */
 public Map<Integer,List<Board>> findAllBoard();
 /**
  * 
  * @param boardid  板块编号
  * @param pageNo  页码
  * @param pagesize 每页记录数量
  * @return
  */
 public List<Topic> findAllTopiByBoardIdLimit(int boardid,int pageNo,int pagesize);
 
 
 public Board findBoardById(int bid);
 public double topicCountByBoardId(int boardId);
 public List<Topic>  findAllTopiByBoardIdLimitByTitle(int boardid, int pagNo, int pagesize,String title);
}
