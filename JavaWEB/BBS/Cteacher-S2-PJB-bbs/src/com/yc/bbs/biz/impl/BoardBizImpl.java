package com.yc.bbs.biz.impl;


import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.Board;
import com.yc.bbs.bean.Topic;
import com.yc.bbs.biz.BoardBiz;
import com.yc.bbs.dao.BoardDao;
import com.yc.bbs.dao.impl.BoardDaoImpl;
import com.yc.util.DBHelper;

public class BoardBizImpl extends DBHelper implements BoardBiz {
	 private BoardDao bd = new BoardDaoImpl();
    /**
     *  һ��������ж���Ӱ�飬���з��ص�Map����
     */
	@Override
	public Map<Integer, List<Board>> findAllBoard() {
		System.out.println(bd.findAllBoard());
		return bd.findAllBoard();
	}
	@Override
	public List<Topic> findAllTopiByBoardIdLimit(int boardid, int pageNo, int pagesize) {
		
		return bd.findAllTopiByBoardIdLimit(boardid, pageNo, pagesize);
	}
	@Override
	public Board findBoardById(int bid) {
		return bd.findBoardById(bid);
	}
	@Override
	public double topicCountByBoardId(int boardId) {
		return bd.topicCountByBoardId(boardId);
	}
	@Override
	public List<Topic> findAllTopiByBoardIdLimitByTitle(int boardid, int pagNo, int pagesize, String title) {
		return bd.findAllTopiByBoardIdLimitByTitle(boardid, pagNo, pagesize, title);
	}

}
