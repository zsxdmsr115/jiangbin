package com.yc.examin.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bbs.bean.JsonModel;
import com.yc.examin.bean.TblScore;
import com.yc.examin.biz.TblScoreBiz;
import com.yc.examin.biz.impl.TblScoreBizImpl;
import com.yc.examin.model.JsonModelIndex;

@WebServlet(urlPatterns = "/Examin/doBsRecord")
public class doBsRecord extends BaseServelet {
//	TblScore rbiz = new TblScoreBizImpl();
//	JsonModelIndex jindex = new JsonModelIndex();
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		if ("stjl".equals(op)) {
//			try {
//				findStRecord(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	private void findStRecord(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		JsonModel jm = new JsonModel();
//	
//		int uid = Integer.parseInt(req.getParameter("uid"));
//		List<TblScore> list = rbiz.findbsRecord(uid);
//	
//		
//		if(list.size()>0&&list!=null){
//			jm.setCode(1);
//			HttpSession session = req.getSession();
//			session.setAttribute("rblist", list);
//			
//			
//			outJson(jm, resp);
//		}
//		
//	}

}
