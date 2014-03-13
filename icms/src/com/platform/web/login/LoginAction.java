package com.platform.web.login;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.auth.AuthPath;
import com.platform.entity.uniauth.role.Role;
import com.platform.entity.uniauth.user.User;
import com.platform.service.iface.login.ILoginService;
import com.platform.util.IrBarIconDataUtil;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
public class LoginAction extends BaseAction
{
	private static final long serialVersionUID = 2085187337062639965L;

	private User user;

	private String checkCode;

	private String msg;

	@Override
	public String execute() throws Exception
	{
		String rand = (String) this.getSession().getAttribute("rand");

		if (rand == null || !rand.equals(checkCode))
		{
			msg = "验证码错误";
			return "login";
		} else
		{
			User u = ((ILoginService) this.getService("loginServiceImpl")).login(user);
			if (u == null)
			{
				msg = "用户名或密码错误";
				return "login";
			} else
			{
				this.getSession().setAttribute("PLATFORM_USER", u);

				List<Auth> IrBarIconData = IrBarIconDataUtil.IrBarIconDataList;
				Map<String, String> lrBarIconDataMap = getIrBarIconData(IrBarIconData);
				this.getRequest().setAttribute("lrBarIconData", lrBarIconDataMap.get("lrBarIconData"));
				this.getRequest().setAttribute("default_app", lrBarIconDataMap.get("default_app"));

				Map<String, String> deskTop = new HashMap<String, String>();
				Set<String> auths = initDeskTop(u, deskTop);

				this.getRequest().setAttribute("navBar", deskTop.get("navBar"));

				this.getRequest().setAttribute("deskIconData", deskTop.get("deskIconData"));

				this.getRequest().setAttribute("desktopInnerPanel", deskTop.get("desktopInnerPanel"));

				this.getSession().setAttribute("PLATFORM_USER_AUTH", auths);

			}
		}

		return "success";
	}

	private Set<String> initDeskTop(User u, Map<String, String> deskTop)
	{

		Set<Auth> platforms = new TreeSet<Auth>();

		String cPath = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":"
				+ this.getRequest().getServerPort() + cPath;

		StringBuffer navBar = new StringBuffer();
		StringBuffer desktopInnerPanel = new StringBuffer();
		StringBuffer deskIconData = new StringBuffer("{");
		Map<String, Map<String, Auth>> auths = new TreeMap<String, Map<String, Auth>>();
		Set<String> allAuth = new HashSet<String>();
		List<Role> roles = u.getRoles();
		try
		{
			for (Role r : roles)
			{
				for (Auth a : r.getAuths())
				{
					for (AuthPath path : a.getAuthPaths())
					{
						allAuth.add(path.getPath());
					}
					if (a.getParent() == null)
					{
						if (auths.get(a.getAuthCode()) == null)
						{
							auths.put(a.getAuthCode(), new TreeMap<String, Auth>());
							platforms.add(a);
						}
					} else
					{
						Map<String, Auth> map = auths.get(a.getParent().getAuthCode());
						if (map == null)
						{
							map = new TreeMap<String, Auth>();
						}
						map.put(a.getAuthCode(), a);
						auths.put(a.getParent().getAuthCode(), map);
					}
				}
			}

			int i = 0;
			for (Auth p : platforms)
			{
				navBar.append("<a href='#' ");
				desktopInnerPanel.append("<ul class='");
				if (i == 0)
				{
					navBar.append(" class='currTab' ");
					desktopInnerPanel.append("deskIcon currDesktop'>");

				} else
				{
					desktopInnerPanel.append("deskIcon'>");
				}

				navBar.append(" title='").append(p.getAuthName()).append("'></a>");

				Map<String, Auth> authMap = auths.get(p.getAuthCode());

				for (String s : authMap.keySet())
				{
					Auth au = authMap.get(s);
					desktopInnerPanel.append("<li class='desktop_icon' id='").append(au.getId())
							.append("'> <span class='icon'><img src='").append(au.getIcoUrl())
							.append("'/></span> <div class='text'>").append(au.getAuthName())
							.append(" <s></s></div> </li>");

					if (!"{".equals(deskIconData.toString()))
					{
						deskIconData.append(",");
					}
					String url = null;
					if (au.getUrl().startsWith("http://"))
					{
						url = au.getUrl();
					} else
					{
						url = basePath + "/" + au.getUrl();
					}
					deskIconData.append("'").append(au.getId()).append("':{'title':'").append(au.getAuthName())
							.append("','url':'").append(url).append("','winWidth':950,'winHeight':700}");

				}

				i++;
				desktopInnerPanel.append("</ul>");
			}
			deskIconData.append("}");
			deskTop.put("navBar", navBar.toString());
			deskTop.put("desktopInnerPanel", desktopInnerPanel.toString());
			deskTop.put("deskIconData", deskIconData.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return allAuth;
	}

	private Map<String, String> getIrBarIconData(List<Auth> irBarIconData)
	{
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer lrBarIconData = new StringBuffer("{");
		StringBuffer default_app = new StringBuffer("");
		for (Auth a : irBarIconData)
		{
			if (!"{".equals(lrBarIconData.toString()))
			{
				lrBarIconData.append(",");
			}
			if ("修改密码".equals(a.getAuthName()))
			{
				lrBarIconData.append("'app").append(a.getId()).append("':{'title':'").append(a.getAuthName())
						.append("','url':'").append(a.getUrl()).append("','winWidth':400,'winHeight':250}");
			} else
			{
				lrBarIconData.append("'app").append(a.getId()).append("':{'title':'").append(a.getAuthName())
						.append("','url':'").append(a.getUrl()).append("','winWidth':800,'winHeight':650}");
			}

			default_app.append("<li id='app").append(a.getId()).append("'><span><img src='").append(a.getIcoUrl())
					.append("' title='").append(a.getAuthName()).append("'/></span><div class='text'>")
					.append(a.getAuthName()).append("<s></s></div></li>");
		}
		lrBarIconData.append("}");

		map.put("lrBarIconData", lrBarIconData.toString());
		map.put("default_app", default_app.toString());
		return map;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getCheckCode()
	{
		return checkCode;
	}

	public void setCheckCode(String checkCode)
	{
		this.checkCode = checkCode;
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
