package com.yc.page.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yc.bean.Page;



public class PageBarTag extends TagSupport {

	private Page page;
	private String href;
	

	@Override
	public int doStartTag() throws JspException {
		//获取输出流
		JspWriter out=super.pageContext.getOut();
		try {
		
			
			out.print(" <input id='op' type='hidden' name='op' />");
			out.print("  <input id='pageNo' type='hidden' name='pageNo' />");
			out.print(" <input type='submit' value='首页' onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='上一页'  onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='下一页'  onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='尾页'  onclick='mysubmit(this)' />");
			
			out.print("<span>每页"+page.getPagesize()+"</span>");
			out.print("<span>总记录"+page.getTotalPage()+"</span>条");
			out.print(" 第<span id='currentpage'>"+page.getPageNo()+"</span>");
			
			out.print(" 共<span id='totalpage'>"+page.getTotalPageNum()+"</span>页");
			out.print("<input id='specifiedPage' style='width:30px' />页 <input type='button' value='跳转' onclick='mysubmit(this)'/>");
			out.print(" <input type='hidden' name='searchValue'   id='searchHidden' value='<%=searchValue %>'/>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//拼接html的分页条
		return super.EVAL_BODY_INCLUDE;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public void setHref(String href) {
		this.href = href;
	}
	
	
	

}
