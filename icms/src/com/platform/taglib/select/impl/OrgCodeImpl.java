package com.platform.taglib.select.impl;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import com.platform.dao.base.BaseDao;
import com.platform.entity.base.BaseCodeEntity;
import com.platform.entity.uniauth.org.Org;
import com.platform.entity.uniauth.user.User;
import com.platform.util.plantform.OrgUtils;

@Component
public class OrgCodeImpl
{

	public Object orgCode(String value, RequestContext requestContext, String type, String codeName,
			BaseCodeEntity temp, String inIds, String nullFilter, String orgType, String orgLevel)
	{
		BaseDao dao = (BaseDao) requestContext.getWebApplicationContext().getBean("baseDao");
		HibernateTemplate ht = dao.getHibernateTemplate();
		int select = -1;
		if (temp != null)
		{
			select = temp.getId();
		}
		Set<Org> orgs = new TreeSet<Org>();

		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("PLATFORM_USER");
		Org userOrg = null;
		if (user.getOrg() != null)
		{
			userOrg = (Org) dao.get(user.getOrg().getId(), Org.class);

			int level = 0;

			if (StringUtils.isNotEmpty(orgLevel))
			{
				level = level + Integer.parseInt(orgLevel);
			} else
			{
				level = -1;// 全部
			}

			orgs = OrgUtils.getOrgs(userOrg, ht, level, orgType, type);

		}

		StringBuffer sbf = new StringBuffer();
		sbf.append("<option value=''>  -----请选择-----  </option>");
		for (Org q : orgs)
		{
			if (select == q.getId() || (StringUtils.isNotEmpty(value) && Integer.parseInt(value) == q.getId()))
			{
				sbf.append("<option value = ").append(q.getId()).append(" selected='selected'>").append(q.getId())
						.append("</option>");

			} else
			{
				sbf.append("<option value = ").append(q.getId()).append(">").append(q.getName()).append("</option>");
			}
		}
		return sbf.toString();
	}
}
