package com.platform.taglib.select.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import com.platform.cache.EhCacheManager;
import com.platform.dao.base.BaseDao;
import com.platform.entity.base.BaseCodeEntity;
import com.platform.taglib.select.iface.ICode;
import com.platform.util.Constant;

@Component
public class CodeImpl implements ICode
{

	@SuppressWarnings("unchecked")
	@Override
	public String getHtml(String value, RequestContext requestContext, String type, String codeName,
			BaseCodeEntity temp, String inIds, String nullFilter)
	{
		BaseDao dao = (BaseDao) requestContext.getWebApplicationContext().getBean("baseDao");
		BaseCodeEntity codeEntity = (BaseCodeEntity) requestContext.getWebApplicationContext().getBean(codeName);

		int select = -1;
		if (temp != null)
		{
			select = temp.getId();
		}

		Set<BaseCodeEntity> set = (Set<BaseCodeEntity>) EhCacheManager.getCacheValue("CACHE_CODE_" + codeName);

		// Set<BaseCodeEntity> set = new HashSet<BaseCodeEntity>(list);

		if (set == null || set.size() < 1)
		{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(codeEntity.getClass());
			List<BaseCodeEntity> tempList = (List<BaseCodeEntity>) dao.getList(detachedCriteria);
			set = new TreeSet<BaseCodeEntity>(tempList);
			EhCacheManager.addCache("CACHE_CODE_" + codeName, set);
		}

		if (StringUtils.isNotEmpty(inIds))
		{
			List<BaseCodeEntity> tempList = new ArrayList<BaseCodeEntity>();
			String[] inIdsString = inIds.split(",");
			List<String> inIdsList = Arrays.asList(inIdsString);
			for (BaseCodeEntity c : set)
			{
				if (inIdsList.contains(c.getId() + ""))
				{
					tempList.add(c);
				}
			}
			set = new TreeSet<BaseCodeEntity>(tempList);
		}

		List<BaseCodeEntity> tempList = new ArrayList<BaseCodeEntity>();

		if ("effective".equals(type))
		{
			for (BaseCodeEntity c : set)
			{
				if (c.getEffective().getId() == Constant.EFFECTIVE_EFFECT)
				{
					tempList.add(c);
				}
			}

		} else if ("invalid".equals(type))
		{
			for (BaseCodeEntity c : set)
			{
				if (c.getEffective().getId() == Constant.EFFECTIVE_UNEFFECT)
				{
					tempList.add(c);
				}
			}
		} else
		{
			tempList.addAll(set);
		}

		StringBuffer sbf = new StringBuffer();
		sbf.append("<option value=''>  -----请选择-----  </option>");
		for (BaseCodeEntity q : tempList)
		{
			if (select == q.getId() || (StringUtils.isNotEmpty(value) && Integer.parseInt(value) == q.getId()))
			{
				sbf.append("<option value = ").append(q.getId()).append(" selected='selected'>").append(q.getValue())
						.append("</option>");

			} else
			{
				sbf.append("<option value = ").append(q.getId()).append(">").append(q.getValue()).append("</option>");
			}
		}
		return sbf.toString();
	}

}
