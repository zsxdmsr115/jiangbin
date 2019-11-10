package com.yc.bbs.biz;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.Board;
import com.yc.bbs.bean.Topic;

public interface BoardBiz {
	/**
	 * �������а��
	 * @return
	 */
 public Map<Integer,List<Board>> findAllBoard();
 /**
  * 
  * @param boardid  �����
  * @param pageNo  ҳ��
  * @param pagesize ÿҳ��¼����
  * @return
  */
 public List<Topic> findAllTopiByBoardIdLimit(int boardid,int pageNo,int pagesize);
 
 
 public Board findBoardById(int bid);
 public double topicCountByBoardId(int boardId);
 public List<Topic>  findAllTopiByBoardIdLimitByTitle(int boardid, int pagNo, int pagesize,String title);
}
