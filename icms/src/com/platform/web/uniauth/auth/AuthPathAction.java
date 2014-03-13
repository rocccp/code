package com.platform.web.uniauth.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.auth.AuthPath;
import com.platform.entity.uniauth.user.User;
import com.platform.vo.uniauth.authpath.AuthPathVo;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class AuthPathAction extends BaseAction
{
	private static final long serialVersionUID = -6882414905896454247L;

	private Auth auth;

	private AuthPath authPath;

	private List<AuthPathVo> rows = new ArrayList<AuthPathVo>();

	public String queryList()
	{
		String authId = this.getRequest().getParameter("id");
		int id = Integer.parseInt(authId);
		Auth auth = (Auth) this.getService().get(id, Auth.class);
		List<AuthPath> list = auth.getAuthPaths();
		if (list == null)
		{
			list = new ArrayList<AuthPath>();
		}

		for (AuthPath a : list)
		{
			AuthPathVo vo = new AuthPathVo();
			vo.setId(a.getId());
			vo.setPath(a.getPath());
			rows.add(vo);

		}
		return Action.SUCCESS;
	}

	public void save()
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		String idStr = this.getRequest().getParameter("id");
		int id = Integer.parseInt(idStr);
		Auth auth = (Auth) this.getService().get(id, Auth.class);
		authPath.setEffective(null);

		List<AuthPath> authPathList = auth.getAuthPaths();
		if (authPathList == null)
		{
			authPathList = new ArrayList<AuthPath>();
		}
		this.getService().saveOrUpdate(authPath);

		authPathList.add(authPath);
		auth.setAuthPaths(authPathList);

		auth.setUpdateTime(date);
		auth.setUpdateUser(u);
		this.getService().saveOrUpdate(auth);
	}

	public void delete()
	{
		String idStr = this.getRequest().getParameter("id");
		String delIdStr = this.getRequest().getParameter("delId");
		int id = Integer.parseInt(idStr);
		int delId = Integer.parseInt(delIdStr);
		Auth auth = (Auth) this.getService().get(id, Auth.class);
		List<AuthPath> authPathList = auth.getAuthPaths();
		AuthPath authPath = null;
		for (AuthPath a : authPathList)
		{
			if (a.getId() == delId)
			{
				authPathList.remove(a);
				authPath = a;
				break;
			}
		}
		auth.setAuthPaths(authPathList);
		this.getService().saveOrUpdate(auth);

		if (authPath != null)
		{
			this.getService().delete(authPath);
		}

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

	public List<AuthPathVo> getRows()
	{
		return rows;
	}

	public void setRows(List<AuthPathVo> rows)
	{
		this.rows = rows;
	}

	public AuthPath getAuthPath()
	{
		return authPath;
	}

	public void setAuthPath(AuthPath authPath)
	{
		this.authPath = authPath;
	}

}
