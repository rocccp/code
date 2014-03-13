package com.platform.web.uniauth.role;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.role.Role;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.uniauth.role.IRoleService;
import com.platform.util.JsonUtil;
import com.platform.util.Page;
import com.platform.vo.uniauth.role.RoleVO;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
@SuppressWarnings({ "unchecked" })
public class RoleAction extends BaseAction
{
	private static final long serialVersionUID = 1633897676252691772L;

	private Role role;

	private List<RoleVO> rows;

	private String msg;

	public String queryList()
	{
		Page p = this.initPage();
		this.rows = ((IRoleService) this.getService("roleServiceImpl")).queryList(p);
		this.responsePage(p);
		return Action.SUCCESS;
	}

	public void save()
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		if (role.getId() == 0)
		{
			role.setInsertTime(date);
			role.setInsertUser(u);

		} else
		{
			Role r = null;
			r = (Role) this.getService().get(role.getId(), Role.class);
			r.setName(role.getName());
			r.setEffective(role.getEffective());
			role = r;
		}
		role.setUpdateTime(date);
		role.setUpdateUser(u);
		this.getService().saveOrUpdate(role);
	}

	public void delete()
	{
		((IRoleService) this.getService("roleServiceImpl")).deleteRole(role);
	}

	public String queryAuth()
	{
		String authId = this.getRequest().getParameter("authId");
		msg = ((IRoleService) this.getService("roleServiceImpl")).queryAuth(authId);
		this.getRequest().setAttribute("authId", authId);
		return "auth";
	}

	public void saveAuth()
	{
		String authStr = this.getRequest().getParameter("authStr");
		String id = this.getRequest().getParameter("id");
		role = new Role();
		role.setId(Integer.parseInt(id));
		List<Auth> auths = JsonUtil.getDTOList(authStr, Auth.class);
		((IRoleService) this.getService("roleServiceImpl")).saveAuth(role, auths);
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

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public List<RoleVO> getRows()
	{
		return rows;
	}

	public void setRows(List<RoleVO> rows)
	{
		this.rows = rows;
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
