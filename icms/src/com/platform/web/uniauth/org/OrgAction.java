package com.platform.web.uniauth.org;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.org.Org;
import com.platform.service.iface.uniauth.org.IOrgService;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
@SuppressWarnings({})
public class OrgAction extends BaseAction
{
	private static final long serialVersionUID = 1633897676252691772L;

	private Org org;

	private String msg;

	public String index()
	{
		msg = ((IOrgService) this.getService("orgServiceImpl")).queryOrg();
		return Action.SUCCESS;
	}

	public void add()
	{
		org = ((IOrgService) this.getService("orgServiceImpl")).addOrg(org);
		msg = org.getId() + "";
		this.outString(msg);
	}

	public void update()
	{
		org = ((IOrgService) this.getService("orgServiceImpl")).updateOrg(org);
		msg = org.getEffective().getId() + "";
		this.outString(msg);
	}

	public void delete()
	{
		 ((IOrgService) this.getService("orgServiceImpl")).deleteOrg(org);
	}
	
	public void move()
	{
		((IOrgService) this.getService("orgServiceImpl")).saveMove(org);
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

	public Org getOrg()
	{
		return org;
	}

	public void setOrg(Org org)
	{
		this.org = org;
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
