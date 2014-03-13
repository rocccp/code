package com.platform.web.uniauth.auth;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.uniauth.auth.IAuthService;
import com.platform.util.Page;
import com.platform.vo.uniauth.auth.AuthVo;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class AuthAction extends BaseAction
{
	private static final long serialVersionUID = -6882414905896454247L;

	private Auth auth;

	private List<AuthVo> rows;

	public String queryList()
	{
		Page p = this.initPage();
		this.rows = ((IAuthService) this.getService("authServiceImpl")).queryAuthList(p);
		this.responsePage(p);
		return Action.SUCCESS;
	}

	public void saveOrUpdate()
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		Auth parent = (Auth) this.getService().get(auth.getParent().getId(), Auth.class);
		if (auth.getId() < 1)
		{
			auth.setInsertTime(date);
			auth.setInsertUser(u);
			auth.setParent(parent);

		} else
		{
			Auth p = null;
			p = (Auth) this.getService().get(auth.getId(), Auth.class);
			p.setAuthName(auth.getAuthName());
			p.setEffective(auth.getEffective());
			p.setIcoUrl(auth.getIcoUrl());
			p.setUrl(auth.getUrl());
			auth = p;
		}
		if (StringUtils.isEmpty(auth.getIcoUrl()))
		{
			auth.setIcoUrl("pages/icon/applications.png");
		}
		auth.setUpdateTime(date);
		auth.setUpdateUser(u);
		this.getService().saveOrUpdate(auth);
	}

	public void delete()
	{
		// String id = this.getRequest().getParameter("ids");
		// auth = new Auth();
		// auth.setId(Integer.parseInt(id));
		((IAuthService) this.getService("authServiceImpl")).deleteAuth(auth);
	}

	public void checkAuth()
	{
		// if (auth == null)
		// {
		// String authCode = this.getRequest().getParameter("authCode");
		// auth = new Auth();
		// auth.setAuthCode(authCode);
		// }
		boolean b = ((IAuthService) this.getService("authServiceImpl")).checkAuth(auth);
		String msg;
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

	public Auth getAuth()
	{
		return auth;
	}

	public void setAuth(Auth auth)
	{
		this.auth = auth;
	}

	public List<AuthVo> getRows()
	{
		return rows;
	}

	public void setRows(List<AuthVo> rows)
	{
		this.rows = rows;
	}
}
