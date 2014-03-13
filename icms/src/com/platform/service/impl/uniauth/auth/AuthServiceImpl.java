package com.platform.service.impl.uniauth.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.role.Role;
import com.platform.service.base.BaseService;
import com.platform.service.iface.uniauth.auth.IAuthService;
import com.platform.util.Page;
import com.platform.vo.uniauth.auth.AuthVo;

@SuppressWarnings({ "unchecked" })
@Component
public class AuthServiceImpl extends BaseService implements IAuthService
{

	@Override
	public List<AuthVo> queryAuthList(Page p)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
		detachedCriteria.add(Restrictions.isNotNull("parent"));
		List<Auth> auths = this.getDao("baseDao").queryListByPage(detachedCriteria, p);
		List<AuthVo> rows = new ArrayList<AuthVo>();
		if (auths != null && auths.size() > 0)
		{
			AuthVo auth = null;
			for (Auth a : auths)
			{
				String updateTime = a.getUpdateTime() == null ? "" : a.getUpdateTime() + "";
				String userName = a.getInsertUser() == null ? "" : a.getInsertUser().getName();
				auth = new AuthVo();
				auth.setId(a.getId());
				auth.setAuthCode(a.getAuthCode());
				auth.setAuthName(a.getAuthName());
				auth.setEffective(a.getEffective().getValue());
				auth.setUpdateTime(updateTime);
				auth.setUpdateUser(userName);
				auth.setPlatformId(a.getParent().getId());
				auth.setPlatformName(a.getParent().getAuthName());
				auth.setUrl(a.getUrl());
				auth.setIcoUrl(a.getIcoUrl());
				rows.add(auth);
			}
		}
		return rows;
	}

	@Override
	public boolean checkAuth(Auth auth)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
		detachedCriteria.add(Restrictions.eq("authCode", auth.getAuthCode()));
		List<Auth> platforms = this.getDao("baseDao").getList(detachedCriteria);
		if (platforms != null && platforms.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void deleteAuth(Auth auth)
	{
		Set<Auth> authSet = new HashSet<Auth>();
		auth = (Auth) this.get(auth.getId(), Auth.class);
		auth.setParent(null);
		getAuthSet(authSet, auth);
		List<Role> roles = auth.getRoles();
		for (Auth a : authSet)
		{
			for (Role r : roles)
			{
				r.getAuths().remove(a);
			}
		}
		this.delete(auth);

	}

	private void getAuthSet(Set<Auth> authSet, Auth platform)
	{
		authSet.add(platform);
		for (Auth a : platform.getChildren())
		{
			getAuthSet(authSet, a);
		}

	}
}
