package com.platform.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthInterceptor implements Interceptor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7573249357130651428L;

	@Override
	public void destroy()
	{

	}

	@Override
	public void init()
	{

	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String url = request.getRequestURL().toString();

		if (url.indexOf("passwordAction") != -1)
		{
			return invocation.invoke();
		}

		Set<String> set = (Set<String>) context.getSession().get("PLATFORM_USER_AUTH");

		if (set == null)
		{
			return "noRight";
		}
		for (String s : set)
		{
			if (url.indexOf(s) != -1)
			{
				return invocation.invoke();
			}
		}
		return "noRight";
	}

}
