package com.platform.util.plantform;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.platform.entity.uniauth.org.Org;
import com.platform.util.Constant;

public class OrgUtils
{
	public static Set<Org> getSelfAndDesOrg(Org org, HibernateTemplate ht)
	{
		org = ht.get(Org.class, org.getId());
		Set<Org> orgs = new TreeSet<Org>();
		getOrg(orgs, org);
		return orgs;
	}

	private static void getOrg(Set<Org> orgs, Org org)
	{
		orgs.add(org);
		if (org.getChildren() != null)
		{
			for (Org o : org.getChildren())
			{
				getOrg(orgs, o);
			}
		}
	}

	public static Set<Org> getOrgs(Org org, HibernateTemplate ht, int level, String orgType, String type)
	{
		org = ht.get(Org.class, org.getId());
		Set<Org> orgs = new TreeSet<Org>();
		getOrg(orgs, org, level, orgType, type);
		return orgs;
	}

	private static void getOrg(Set<Org> orgs, Org org, int tempLevel, String orgType, String type)
	{
		boolean orgTypeFlag = false;
		boolean typeFlag = false;
		if (StringUtils.isNotEmpty(orgType))
		{
			if ("D".equalsIgnoreCase(orgType))
			{
				if (Constant.ORGTYPE_DEPARTMENT == org.getType().getId())
				{
					orgTypeFlag = true;
				}

			} else if ("R".equalsIgnoreCase(orgType))
			{
				if (Constant.ORGTYPE_ROOM == org.getType().getId())
				{
					orgTypeFlag = true;
				}

			} else
			{
				orgTypeFlag = true;
			}

		} else
		{
			orgTypeFlag = true;
		}

		if (StringUtils.isNotEmpty(type))
		{
			if ("effective".equalsIgnoreCase(type))
			{
				if (Constant.EFFECTIVE_EFFECT == org.getEffective().getId())
				{
					typeFlag = true;
				}

			} else if ("invalid".equalsIgnoreCase(type))
			{
				if (Constant.EFFECTIVE_UNEFFECT == org.getEffective().getId())
				{
					typeFlag = true;
				}

			} else
			{
				typeFlag = true;
			}

		} else
		{
			typeFlag = true;
		}

		if (orgTypeFlag && typeFlag)
		{
			orgs.add(org);
		}

		if (org.getChildren() != null)
		{
			for (Org o : org.getChildren())
			{
				if (o.getLevel() >= tempLevel)
				{
					getOrg(orgs, o, tempLevel, orgType, type);
				}

			}
		}
	}
}
