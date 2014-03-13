package com.platform.web.uniauth.password;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.platform.entity.uniauth.user.User;
import com.platform.util.MD5CodeUtil;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class PasswordAction extends BaseAction
{
	private static final long serialVersionUID = -8595317865439185265L;

	private String password;

	private String msg;

	public String index()
	{
		return "index";
	}

	public void save()
	{
		User u = this.getLogonUser();
		u = (User) this.getService().get(u.getId(), User.class);
		String pw = MD5CodeUtil.encodeByMD5("PLATFORM" + u.getAccount() + password);
		u.setPassword(pw);
		this.setLogonUser(u);
		this.getService().saveOrUpdate(u);

	}

	public void checkPW()
	{
		User u = this.getLogonUser();
		String pw = MD5CodeUtil.encodeByMD5("PLATFORM" + u.getAccount() + password);

		if (pw.equalsIgnoreCase(u.getPassword()))
		{
			msg = "1";

		} else
		{
			msg = "0";
		}
		this.outString(msg);
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
