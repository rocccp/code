package com.platform.web.uniauth.platform;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.uniauth.platform.IPlatformService;
import com.platform.util.Page;
import com.platform.vo.uniauth.platform.Platform;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
@SuppressWarnings({})
public class PlatformAction extends BaseAction
{
	private static final long serialVersionUID = 1633897676252691772L;

	private Auth platform;

	private List<Platform> rows;

	private String msg;

	public String queryList()
	{
		Page p = this.initPage();
		this.rows = ((IPlatformService) this.getService("platformServiceImpl")).queryPlatformList(p);
		this.responsePage(p);
		return Action.SUCCESS;
	}


	


	public void saveOrUpdate()
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		String id = this.getRequest().getParameter("id");
		if (StringUtils.isEmpty(id))
		{
			platform.setInsertTime(date);
			platform.setInsertUser(u);
			platform.setUrl("#");

		} else
		{
			Auth p = null;
			p = (Auth) this.getService().get(Integer.parseInt(id), Auth.class);
			p.setAuthName(platform.getAuthName());
			p.setEffective(platform.getEffective());
			platform = p;
		}
		platform.setUpdateTime(date);
		platform.setUpdateUser(u);
		this.getService().saveOrUpdate(platform);
	}

	public void delete()
	{
		String id = this.getRequest().getParameter("ids");
		platform = new Auth();
		platform.setId(Integer.parseInt(id));

		((IPlatformService) this.getService("platformServiceImpl")).deletePlatfrom(platform);
	}

	public void checkPlatform()
	{
		boolean b = ((IPlatformService) this.getService("platformServiceImpl")).checkPlatform(platform);
		if (b)
		{
			msg = "1";
		} else
		{
			msg = "0";
		}
		this.outString(msg);
	}

	public Integer getPage()
	{
		return page;
	}

	public Integer getRp()
	{
		return rp;
	}

	public Integer getTotal()
	{
		return total;
	}

	public List<Platform> getRows()
	{
		return rows;
	}

	public void setRows(List<Platform> rows)
	{
		this.rows = rows;
	}

	public Auth getPlatform()
	{
		return platform;
	}

	public void setPlatform(Auth platform)
	{
		this.platform = platform;
	}

	@JSON
	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
