package com.platform.util;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanUtils
{
	public static Object getBean(String name)
	{
		ServletContext servletContext = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return ctx.getBean(name);
	}
}
