package com.yc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
 static final long serialVersionUID = 1L;
	protected String op;//�ַ��Ĺؼ�����
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		op=request.getParameter("op");
		System.out.println(op);
		//super.service(request, response);//HttpServlet�еĸ���ķ��������ж�����ʽ
		//�����ʽΪ get,�����  doGet
		//�����ʽΪ post,�����  doPost
		//doPost(request, response);
//		if(request.getMethod().equals("GET")){
//			doPost(request, response);
//		}
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			super.doGet(req, resp);
		}
}
