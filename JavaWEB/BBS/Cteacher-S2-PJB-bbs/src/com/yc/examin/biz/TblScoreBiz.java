package com.yc.examin.biz;

import java.util.List;

import com.yc.examin.bean.TblScore;



/**
 * 刷题记录
 * @author zsxdmsr115
 *
 */
public interface TblScoreBiz {
 
   /**
    * 查询刷题记录
    */
   public List<TblScore>  findbsRecord(int uid);
   void addRecord(List<TblScore> tblist);
}
