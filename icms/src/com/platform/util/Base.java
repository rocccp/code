package com.platform.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.entity.uniauth.user.User;

public class Base
{
	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession()
	{
		return ServletActionContext.getRequest().getSession();
	}

	public User getLogonUser()
	{
		return (User) ServletActionContext.getRequest().getSession().getAttribute("PLATFORM_USER");
	}

	public Object getSpringBean(String beanName)
	{
		ServletContext servletContext = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return ctx.getBean(beanName);
	}
}
