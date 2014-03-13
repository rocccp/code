package com.platform.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class LogoutAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = this.getRequest();
		if (request != null)
		{
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				session.invalidate();
			}
		}
		return super.execute();
	}

}
