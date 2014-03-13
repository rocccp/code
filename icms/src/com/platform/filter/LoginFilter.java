package com.platform.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter
{
	// private String[] suffixs = { ".do", ".jsp", ".htm", ".html" };  

	private String[] exceptions = { "loginAction.do", "login.jsp", "pages/jsp/common/image.jsp" };

	/**
	 * Created on Feb 12, 2010
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @author: 赵继平[rocccp@sohu.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	public void destroy()
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		// 获取uri地址
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		HttpSession s = req.getSession(false);
		String path = url;

		String cPath = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + cPath;

		// 获取当前请求路径是否为 *.do *.jsp *.html *.htm ....需要控制的资源
		// 若为需要控制的资源请求，判断是否登录 checkCondition(suffixs, path) &&
		if (!cPath.equals(path)&&!(cPath+"/").equals(path)&&!checkCondition(exceptions, path) && !checkLogin(s, path))
		{
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open ('" + basePath + "','_top')");
			out.println("</script>");
			out.println("</html>");
			return;
		}

		chain.doFilter(request, response);

	}

	private boolean checkLogin(HttpSession s, String path)
	{
		boolean loginFlag = false;

		if (path.contains("login") || path.contains("login.jsp"))
		{
			loginFlag = true;
		}

		if (!loginFlag)
		{
			if (s != null && s.getAttribute("PLATFORM_USER") != null)
			{
				return true;
			}
		}

		return false;
	}

	private boolean checkCondition(String[] condition, String path)
	{
		for (String s : condition)
		{
			if (path.contains(s))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

}
