package com.platform.service.impl.uniauth.platform;

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
import com.platform.service.iface.uniauth.platform.IPlatformService;
import com.platform.util.Page;
import com.platform.vo.uniauth.platform.Platform;

@SuppressWarnings({ "unchecked" })
@Component("platformServiceImpl")
public class PlatformServiceImpl extends BaseService implements IPlatformService
{

	@Override
	public List<Platform> queryPlatformList(Page p)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
		detachedCriteria.add(Restrictions.isNull("parent"));
		List<Auth> platforms = this.getDao("baseDao").queryListByPage(detachedCriteria, p);
		List<Platform> rows = new ArrayList<Platform>();
		if (platforms != null && platforms.size() > 0)
		{
			Platform platform = null;
			for (Auth a : platforms)
			{
				String updateTime = a.getUpdateTime() == null ? "" : a.getUpdateTime() + "";
				String userName = a.getUpdateUser() == null ? "" : a.getUpdateUser().getName();
				platform = new Platform();
				platform.setId(a.getId());
				platform.setAuthCode(a.getAuthCode());
				platform.setAuthName(a.getAuthName());
				platform.setEffective(a.getEffective().getValue());
				platform.setUpdateTime(updateTime);
				platform.setUpdateUser(userName);
				rows.add(platform);
			}
		}
		return rows;
	}

	@Override
	public boolean checkPlatform(Auth platform)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
		detachedCriteria.add(Restrictions.eq("authCode", platform.getAuthCode()));
		List<Auth> platforms = this.getDao("baseDao").getList(detachedCriteria);
		if (platforms != null && platforms.size() > 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void deletePlatfrom(Auth platform)
	{
		Set<Auth> authSet = new HashSet<Auth>();
		platform = (Auth) this.get(platform.getId(), Auth.class);
		getAuthSet(authSet, platform);
		List<Role> roles = platform.getRoles();
		for (Auth a : authSet)
		{
			for (Role r : roles)
			{
				r.getAuths().remove(a);
			}
		}
		this.delete(platform);

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
