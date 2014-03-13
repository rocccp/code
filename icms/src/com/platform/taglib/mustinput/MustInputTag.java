package com.platform.taglib.mustinput;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MustInputTag extends TagSupport
{

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		try
		{
			html.append("<font color = 'red'>*<font>");
			out.println(html.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return 0;

	}

}
