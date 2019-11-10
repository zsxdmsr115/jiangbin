package com.yc.examin.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.yc.examin.bean.TblScore;
import com.yc.examin.dao.TblScoreDao;
import com.yc.util.DBHelper;

public class TblScoreDaoImpl extends DBHelper  implements TblScoreDao{

	@Override
	public void addRecord(List<TblScore> tblist) {
		dbIndex=1;
		String sql = "insert into tbl_score (uid,selfanwer,qid,nonum,completime,errorcode) values";
		List<Object> params = new ArrayList<Object>();
		for(int i=0;i<tblist.size();i++){
			 TblScore tblScore = tblist.get(i);
			 params.add(tblScore.getUid());
			 params.add(tblScore.getSelfanswer());
			 params.add(tblScore.getQid());
			 params.add(tblScore.getNonum());
			 params.add(tblScore.getErrorcode());
			sql+="(?,?,?,?,now(),?),";
		}
		sql=sql.substring(0,sql.lastIndexOf(","));
		
		doUpdate(sql, params);
	}

	@Override
	public List<TblScore> findbsRecord(int uid) {
		dbIndex=1;
		String sql ="select * from tbl_bsrecord where uid="+uid;
		try {
			return find(sql, TblScore.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
   
}
