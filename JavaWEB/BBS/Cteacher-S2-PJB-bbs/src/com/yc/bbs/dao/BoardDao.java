package com.yc.bbs.dao;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.Board;
import com.yc.bbs.bean.Topic;

public interface BoardDao {
	public Map<Integer, List<Board>> findAllBoard();
	/**
	 * 分页查询
	 * @param boardid  版块id
	 * @param pageNo   页码
	 * @param pagesize  每页记录数
	 * @return   版块下 帖子
	 */
	public List<Topic> findAllTopiByBoardIdLimit(int boardid, int pageNo, int pagesize,String...title);
	public Board findBoardById(int bid);

	double topicCountByBoardId(Integer boardId); 
	public List<Topic>  findAllTopiByBoardIdLimitByTitle(int boardid, int pagNo, int pagesize,String title);
	
}
