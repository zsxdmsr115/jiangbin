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
		//��ȡ�����
		JspWriter out=super.pageContext.getOut();
		try {
		
			
			out.print(" <input id='op' type='hidden' name='op' />");
			out.print("  <input id='pageNo' type='hidden' name='pageNo' />");
			out.print(" <input type='submit' value='��ҳ' onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='��һҳ'  onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='��һҳ'  onclick='mysubmit(this)' />");
			out.print(" <input type='submit'  value='βҳ'  onclick='mysubmit(this)' />");
			
			out.print("<span>ÿҳ"+page.getPagesize()+"</span>");
			out.print("<span>�ܼ�¼"+page.getTotalPage()+"</span>��");
			out.print(" ��<span id='currentpage'>"+page.getPageNo()+"</span>");
			
			out.print(" ��<span id='totalpage'>"+page.getTotalPageNum()+"</span>ҳ");
			out.print("<input id='specifiedPage' style='width:30px' />ҳ <input type='button' value='��ת' onclick='mysubmit(this)'/>");
			out.print(" <input type='hidden' name='searchValue'   id='searchHidden' value='<%=searchValue %>'/>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ƴ��html�ķ�ҳ��
		return super.EVAL_BODY_INCLUDE;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public void setHref(String href) {
		this.href = href;
	}
	
	
	

}
