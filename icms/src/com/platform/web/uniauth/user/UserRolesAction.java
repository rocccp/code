package com.platform.web.uniauth.user;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.uniauth.user.IUserService;
import com.platform.util.Page;
import com.platform.vo.uniauth.role.RoleVO;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class UserRolesAction extends BaseAction
{
	private static final long serialVersionUID = 1633897676252691772L;

	private List<RoleVO> rows;

	private String msg;

	public String queryList()
	{
		Page p = this.initPage();
	    User u =  new User();
	    String id = this.getRequest().getParameter("id");
	    u.setId(Integer.parseInt(id));
		this.rows = ((IUserService) this.getService("userServiceImpl")).queryRolesList(p,u);
		this.responsePage(p);
		return Action.SUCCESS;
	}
	
	public void save()
	{
		User u =  new User();
	    String id = this.getRequest().getParameter("id");
	    u.setId(Integer.parseInt(id));
	    ((IUserService) this.getService("userServiceImpl")).saveRoles(msg, u);
	    
	    
	    
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
