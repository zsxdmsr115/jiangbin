package com.yc.examin.biz.impl;

import java.util.List;

import com.yc.examin.bean.TblScore;
import com.yc.examin.biz.TblScoreBiz;
import com.yc.examin.dao.TblScoreDao;
import com.yc.examin.dao.impl.TblScoreDaoImpl;

public class TblScoreBizImpl implements TblScoreBiz{
	TblScoreDao bsdao = new TblScoreDaoImpl();

	@Override
	public void addRecord(List<TblScore> tblist) {
		bsdao.addRecord(tblist);;
	}

	@Override
	public List<TblScore> findbsRecord(int uid) {
		return null;
	}
	

}
