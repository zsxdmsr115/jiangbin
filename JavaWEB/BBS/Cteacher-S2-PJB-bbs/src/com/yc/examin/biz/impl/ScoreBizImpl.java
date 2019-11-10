package com.yc.examin.biz.impl;

import com.yc.examin.biz.ScoreBiz;
import com.yc.examin.dao.ScoreDao;
import com.yc.examin.daoimpl.ScoreDaoImpl;

public class ScoreBizImpl implements ScoreBiz{
	ScoreDao sd = new ScoreDaoImpl();
	@Override
	public void addScore(int uid, int score, int lessonid) {
	   sd.addScore(uid, score, lessonid);
	}

}
