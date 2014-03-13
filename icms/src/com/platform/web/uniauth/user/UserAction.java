package com.platform.web.uniauth.user;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.platform.entity.common.Certificate;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.uniauth.user.IUserService;
import com.platform.util.MD5CodeUtil;
import com.platform.util.Page;
import com.platform.vo.uniauth.user.UserVO;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class UserAction extends BaseAction
{
	private static final long serialVersionUID = 1633897676252691772L;

	private User user;

	private List<UserVO> rows;

	private String msg;

	public String queryList()
	{
		Page p = this.initPage();
		this.rows = ((IUserService) this.getService("userServiceImpl")).queryList(p, this.getQueryFilter());
		this.responsePage(p);
		return Action.SUCCESS;
	}

	public String checkUser()
	{
		boolean flag = ((IUserService) this.getService("userServiceImpl")).checkUser(user);
		msg = flag + "";
		return "ajax";
	}

	public void save()
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		Certificate certificate = null;
		if (user.getId() == 0)
		{
			user.setInsertTime(date);
			user.setInsertUser(u);
			if (user.getCertificate() != null)
			{
				certificate = user.getCertificate();
				certificate.setInsertUser(u);
				certificate.setInsertTime(date);
				certificate.setUpdateTime(date);
				certificate.setUpdateUser(u);
				this.getService().saveOrUpdate(certificate);
			}
			user.setCertificate(certificate);
			String pw = MD5CodeUtil.encodeByMD5("PLATFORM" + user.getAccount() + "888888");
			user.setPassword(pw);
		} else
		{
			User tempUser = null;
			tempUser = (User) this.getService().get(user.getId(), User.class);
			tempUser.setBirthday(user.getBirthday());
			tempUser.setEffective(user.getEffective());
			tempUser.setEmail(user.getEmail());
			tempUser.setPersonType(user.getPersonType());
			tempUser.setPhone(user.getPhone());
			tempUser.setName(user.getName());
			tempUser.setOrg(user.getOrg());
			tempUser.setSex(user.getSex());
			tempUser.setStatus(user.getStatus());

			if (user.getCertificate() != null)
			{
				certificate = (Certificate) this.getService().get(user.getCertificate().getId(), Certificate.class);
				if (certificate != null)
				{
					certificate.setType(user.getCertificate().getType());
					certificate.setNum(user.getCertificate().getNum());
					certificate.setUpdateTime(date);
					certificate.setUpdateUser(u);
				} else
				{
					certificate = user.getCertificate();
					certificate.setInsertUser(u);
					certificate.setInsertTime(date);
					certificate.setUpdateTime(date);
					certificate.setUpdateUser(u);
					this.getService().saveOrUpdate(certificate);
				}

			}
			tempUser.setCertificate(certificate);
			user = tempUser;
		}
		user.setUpdateTime(date);
		user.setUpdateUser(u);
		
		this.getService().saveOrUpdate(user);
	}

	public void delete()
	{
		((IUserService) this.getService("userServiceImpl")).deleteUser(user);
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

	public List<UserVO> getRows()
	{
		return rows;
	}

	public void setRows(List<UserVO> rows)
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
