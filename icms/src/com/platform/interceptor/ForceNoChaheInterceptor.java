package com.platform.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ForceNoChaheInterceptor implements Interceptor
{

    /**
     * <p>Discription:[字段功能描述]</p>
     */
    private static final long serialVersionUID = 1L;

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
        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", -1);       
        return invocation.invoke();
    }

}
