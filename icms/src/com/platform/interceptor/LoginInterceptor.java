package com.platform.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.platform.entity.uniauth.user.User;

public class LoginInterceptor implements Interceptor
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

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		Map<?, ?> session = invocation.getInvocationContext().getSession();
		if (session == null)
		{
			return "reLogin";
		}
		User user = (User) session.get("PLATFORM_USER");
		if (user == null)
		{
			return "reLogin";
		}
		return invocation.invoke();
	}

}
