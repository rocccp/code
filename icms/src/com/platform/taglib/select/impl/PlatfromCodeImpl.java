package com.platform.taglib.select.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import com.platform.cache.EhCacheManager;
import com.platform.dao.base.BaseDao;
import com.platform.entity.base.BaseCodeEntity;
import com.platform.entity.uniauth.auth.Auth;
import com.platform.taglib.select.iface.ICode;
import com.platform.util.Constant;

@Component
public class PlatfromCodeImpl implements ICode
{

	@SuppressWarnings("unchecked")
	@Override
	public String getHtml(String value, RequestContext requestContext, String type, String codeName,
			BaseCodeEntity temp, String inIds, String nullFilter)
	{
		BaseDao dao = (BaseDao) requestContext.getWebApplicationContext().getBean("baseDao");
		int select = -1;
		if (temp != null)
		{
			select = temp.getId();
		}

		Set<Auth> set = (Set<Auth>) EhCacheManager.getCacheValue("CACHE_CODE_" + codeName);
		if (set == null || set.size() < 1)
		{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
			detachedCriteria.add(Restrictions.isNull("parent"));
			List<Auth> tempList = (List<Auth>) dao.getList(detachedCriteria);
			set = new TreeSet<Auth>(tempList);
			EhCacheManager.addCache("CACHE_CODE_" + codeName, set);
		}

		if (StringUtils.isNotEmpty(inIds))
		{
			List<Auth> tempList = new ArrayList<Auth>();
			String[] inIdsString = inIds.split(",");
			List<String> inIdsList = Arrays.asList(inIdsString);
			for (Auth c : set)
			{
				if (inIdsList.contains(c.getId() + ""))
				{
					tempList.add(c);
				}
			}

			set = new TreeSet<Auth>(tempList);
		}

		List<Auth> tempList = new ArrayList<Auth>();

		if ("effective".equals(type))
		{
			for (Auth auth : set)
			{
				if (auth.getEffective().getId() == Constant.EFFECTIVE_EFFECT)
				{
					tempList.add(auth);
				}
			}

		} else if ("invalid".equals(type))
		{
			for (Auth auth : set)
			{
				if (auth.getEffective().getId() == Constant.EFFECTIVE_UNEFFECT)
				{
					tempList.add(auth);
				}
			}
		} else
		{
			tempList.addAll(set);
		}
		StringBuffer sbf = new StringBuffer();
		sbf.append("<option value=''>  -----请选择-----  </option>");
		for (Auth q : tempList)
		{
			if (select == q.getId() || (StringUtils.isNotEmpty(value) && Integer.parseInt(value) == q.getId()))
			{
				sbf.append("<option value = ").append(q.getId()).append(" selected='selected'>").append(q.getId())
						.append("</option>");

			} else
			{
				sbf.append("<option value = ").append(q.getId()).append(">").append(q.getAuthName()).append("</option>");
			}
		}
		return sbf.toString();
	}
}
