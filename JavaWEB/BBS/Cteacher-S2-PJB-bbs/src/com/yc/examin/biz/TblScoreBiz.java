package com.yc.examin.biz;

import java.util.List;

import com.yc.examin.bean.TblScore;



/**
 * ˢ���¼
 * @author zsxdmsr115
 *
 */
public interface TblScoreBiz {
 
   /**
    * ��ѯˢ���¼
    */
   public List<TblScore>  findbsRecord(int uid);
   void addRecord(List<TblScore> tblist);
}
