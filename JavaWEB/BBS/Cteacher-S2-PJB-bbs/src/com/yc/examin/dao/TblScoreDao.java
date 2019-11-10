package com.yc.examin.dao;

import java.util.List;

import com.yc.examin.bean.TblScore;

public interface TblScoreDao {
	public void addRecord(List<TblScore> tblist);

	public List<TblScore> findbsRecord(int uid);
}
