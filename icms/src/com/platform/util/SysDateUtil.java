package com.platform.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class SysDateUtil
{
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("rawtypes")
	public Date querySysDate(String type)
	{
		Date date = null;
		String sql = null;
		try
		{
			hibernateTemplate = (HibernateTemplate) SpringBeanUtils.getBean("hibernateTemplate");
			if ("mysql".equals(type))
			{
				sql = "select sysdate() as dt";

			} else if ("sybase".equals(type))
			{
				sql = "select getdate() as dt";
			}else if ("oracle".equals(type))
			{
				sql = "select SYSTIMESTAMP as dt from dual ";
			}

			if (StringUtils.isNotEmpty(sql))
			{
				SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				query.addScalar("dt", Hibernate.TIMESTAMP);
				List list = query.list();
				if (list != null && list.size() > 0)
				{
					Map map = (Map) list.get(0);
					date = (Date) map.get("dt");
				} else
				{
					date = new Date();
				}
			}

		} catch (HibernateException e)
		{
			e.printStackTrace();
		}

		return date;

	}

	public static Date getSysDate()
	{
		return getSysDate("mysql");

	}

	public static Date getSysDate(String type)
	{
		SysDateUtil utils = new SysDateUtil();
		return utils.querySysDate(type);
	}

}
