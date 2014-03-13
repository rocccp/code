package com.platform.web.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.entity.uniauth.user.User;
import com.platform.service.base.BaseService;
import com.platform.util.JsonUtil;
import com.platform.util.Page;
import com.platform.util.SysDateUtil;

@SuppressWarnings("unchecked")
@Component
public class BaseAction extends ActionSupport
{
	private static final long serialVersionUID = -5465269533608844395L;

	protected List<Map<String, String>> filters = new ArrayList<Map<String, String>>();

	protected Integer page; // 第几页

	protected Integer total;// 共几页

	protected Integer rp; // 每页显示几条数据

	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession()
	{
		return ServletActionContext.getRequest().getSession();
	}

	public void setLogonUser(User u)
	{
		this.getSession().setAttribute("PLATFORM_USER", u);
	}

	public User getLogonUser()
	{
		return (User) this.getSession().getAttribute("PLATFORM_USER");
	}

	public Object getSpringBean(String beanName)
	{
		ServletContext servletContext = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return ctx.getBean(beanName);
	}

	public HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	public void outJsonString(String str)
	{
		getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str);
	}

	public void outJson(Object obj)
	{
		outJsonString(JSONObject.fromObject(obj).toString());
	}

	public void outJsonArray(Object array)
	{
		outJsonString(JSONArray.fromObject(array).toString());
	}

	public void outString(String str)
	{
		try
		{
			getResponse().setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e)
		{
		}
	}

	public void outXMLString(String xmlStr)
	{
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}

	public String getJsonStringByObject(Object object)
	{
		return JSONObject.fromObject(object).toString();
	}

	public String getJsonStringByObjectArray(Object object)
	{
		return JSONArray.fromObject(object).toString();
	}

	public BaseService getService(String serviceName)
	{
		return (BaseService) this.getSpringBean(serviceName);
	}

	public BaseService getService()
	{
		return getService("baseService");
	}

	public Page initPage()
	{
		Page p = new Page();
		// 得到当前页数
		String page = this.getRequest().getParameter("page");
		// 得到每页显示行数
		String rp = this.getRequest().getParameter("rp");

		String sortname = this.getRequest().getParameter("sortname");
		String sortorder = this.getRequest().getParameter("sortorder");

		if (StringUtils.isEmpty(page))
		{
			p.setPage(1);
		} else
		{
			p.setPage(Integer.parseInt(page));
		}

		if (StringUtils.isEmpty(rp))
		{
			p.setRp(15);
		} else
		{
			p.setRp(Integer.parseInt(rp));
		}
		p.setStartIndex((p.getPage() - 1) * p.getRp());

		if (StringUtils.isNotEmpty(sortname))
		{
			p.setSortname(sortname);
			if (StringUtils.isNotEmpty(sortorder))
			{
				p.setSortorder(sortorder);
			} else
			{
				p.setSortorder("ASC");
			}
		}

		return p;
	}

	public void responsePage(Page p)
	{
		this.setTotal(p.getTotolResultNum());
		this.setPage(p.getPage());
		this.setRp(p.getRp());
	}

	public void printToJson(String jsonStr)
	{
		try
		{
			this.getResponse().setCharacterEncoding("UTF-8");
			this.getResponse().setContentType("text/json");
			this.getResponse().setDateHeader("Expires", 0);
			PrintWriter out = this.getResponse().getWriter();
			out.println(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public void setRp(Integer rp)
	{
		this.rp = rp;
	}

	public Date getSysDate()
	{
		return SysDateUtil.getSysDate();
	}

	public List<Map<String, String>> getQueryFilter()
	{
		String queryStr = this.getRequest().getParameter("queryFilter");
		String[] queryStrs = null;
		if (StringUtils.isNotEmpty(queryStr))
		{
			queryStrs = queryStr.split("@@");
			for (String s : queryStrs)
			{
				s = s + " ";
				if (StringUtils.isNotEmpty(s.trim()))
					;
				{
					this.filters.add(JsonUtil.getMapFromJson(s.trim()));
				}

			}
		}
		return filters;
	}

	public List<Map<String, String>> getFilters()
	{
		return filters;
	}

	public void setFilters(List<Map<String, String>> filters)
	{
		this.filters = filters;
	}

}
