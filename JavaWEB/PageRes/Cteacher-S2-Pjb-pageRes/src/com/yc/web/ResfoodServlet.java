package com.yc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.yc.bean.JsonModel;
import com.yc.bean.Page;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBizImpl;
import com.yc.util.IpUtils;

@WebServlet("/resfood.action")
public class ResfoodServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");
		System.out.println("op==="+op);
		if ("paging".equals(op)) {
			
			search(req, resp);

		} else if ("praise".equals(op)) {
			praise(req, resp);
		} else if ("findbyId".equals(op)) {

			findById(req, resp);
		} else if ("showHistory".equals(op)) {
			showHistory(req, resp);
		}
	}

	public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResfoodBizImpl rbiz = new ResfoodBizImpl();

		if (req.getParameter("searchValue") != null && !"".equals(req.getParameter("searchValue"))) {

			req.getSession().setAttribute("searchValue", req.getParameter("searchValue"));
			findByLikeName(req, resp);

		} else {
			findAll(req, resp);
		}
	}

	public void findByLikeName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String likeName = req.getParameter("searchValue");
		ResfoodBizImpl rbiz = new ResfoodBizImpl();
		Page page = new Page();
		page.setPagesize(3);
		
		Map <String,String>map = new HashMap<String,String>();
	  System.out.println("liked===="+likeName);
		map.put("fname",likeName );
		int total = rbiz.getcount(map);
		System.out.println("===="+total);
		page.setTotalPage(total);
		page.setFlag(true);
		setPageNo(req, page);
		page = rbiz.find(page, req.getParameter("searchValue"));

		req.getSession().setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(req, resp);
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResfoodBizImpl rbiz = new ResfoodBizImpl();

		int fid = Integer.parseInt(req.getParameter("fid"));
		Resfood resfood = rbiz.details(fid);
		req.setAttribute("rsfood", resfood);
		rbiz.visiteResfood(resfood);// 访问数
		String ipAddress = IpUtils.getIPAddress(req);
		rbiz.recordHistory(ipAddress, fid);// 访问历史纪律
		req.getRequestDispatcher("/WEB-INF/page/detail.jsp").forward(req, resp);

	}

	private void showHistory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ResfoodBizImpl bizImpl = new ResfoodBizImpl();
		String ip = IpUtils.getIPAddress(req);
		// session中的list中的resfood是有图片

		List<Resfood> list = bizImpl.findHistory(ip);
		// 在这个取出HttpSession中的 listFood
		// 循环这个list,取出每个resfodd ,看在listFood中是否有，如有，则取出图片
		System.out.println(list);
		Gson g = new Gson();
		JsonModel jm = new JsonModel();
		jm.setCode("1");

		jm.setObj(list);
		String jsonString = g.toJson(jm);

		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(jsonString);
		out.close();

	}

	private void praise(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ResfoodBizImpl bizImpl = new ResfoodBizImpl();
		int fid = Integer.parseInt(req.getParameter("fid"));
		String ip = IpUtils.getIPAddress(req);
		// 访问业务层，去操作redis
		long praise = bizImpl.praise(fid, ip);// 返回最新点赞数
		Gson g = new Gson();
		JsonModel jm = new JsonModel();
		jm.setCode("1");
		jm.setObj(praise);
		String jsonString = g.toJson(jm);

		resp.setContentType("appllication/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(jsonString);
		out.close();
	}

	// 查所有的菜,分页
	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResfoodBizImpl bizImpl = new ResfoodBizImpl();
		HttpSession session = req.getSession();
		session.removeAttribute("searchValue");
		Page page = new Page();
		page.setPagesize(3);
		page.setFlag(true);
		
		page.setTotalPage(bizImpl.getcount(null));
		int totalpage = page.getTotalPage();
		
		setPageNo(req, page);

		page = bizImpl.find(page);
		//System.out.println(page);
		session.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(req, resp);

	}

	private void setPageNo(HttpServletRequest req, Page page) {
		if (req.getParameter("pageNo") != null && !"".equals(req.getParameter("pageNo"))) {// 上一页，下一页
			int pageno = Integer.parseInt(req.getParameter("pageNo"));
		//	page.setPageNo(pageno);
			page.setPageNo(pageno);
		} else {
			page.setPageNo(1);// 显示第一页

		}
	}
}
