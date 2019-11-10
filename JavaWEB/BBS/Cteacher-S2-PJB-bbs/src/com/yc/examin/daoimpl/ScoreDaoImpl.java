package com.yc.examin.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.yc.examin.dao.ScoreDao;
import com.yc.util.DBHelper;

public class ScoreDaoImpl extends DBHelper implements ScoreDao {

	@Override
	public void addScore(int uid, int score, int lessonid) {
		dbIndex=1;
		String sql ="insert into  score values(?,?,?)";
		List<Object> list = new ArrayList<>();
		list.add(uid);
		list.add(score);
		list.add(lessonid);
		doUpdate(sql,list );
	}

}
