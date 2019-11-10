package com.yc.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bbs.bean.PageBean;
import com.yc.bbs.bean.Reply;
import com.yc.bbs.biz.ReplyBiz;
import com.yc.bbs.biz.impl.ReplyBizImpl;

/**
 * Servlet implementation class PageServelt
 */
public class PageServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ReplyBiz rb  = new ReplyBizImpl();
	        
	     	PageBean pageBean = new PageBean();
	     	pageBean.setPagesize(3);
	     	pageBean.setFlag(true);
	     	System.out.println(request.getParameter("topicid"));
	     	pageBean.setTotalPage( rb.findReply(   Integer.parseInt(request.getParameter("topicid"))).size());
	    	
	     	if (request.getParameter("pageNo") != null && !"".equals(request.getParameter("pageNo"))) {// 上一页，下一页
				int pageno = Integer.parseInt(request.getParameter("pageNo"));
	     		
	     
			//	page.setPageNo(pageno);
				pageBean.setPageNo(pageno);
			} else {
				pageBean.setPageNo(1);// 显示第一页

			}
	       int  tid =  Integer.parseInt( request.getParameter("topicid"));
	      request.getSession().setAttribute("tid", tid);
	     	List<Reply>rblist =  rb.selectLimit(pageBean,tid+"");
	     	
	     	 request.getSession().setAttribute("page", pageBean);
	     	
	     	request.setAttribute("rblist", rblist);
		 request.getRequestDispatcher("detail.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
