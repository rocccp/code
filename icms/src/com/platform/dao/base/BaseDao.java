package com.platform.dao.base;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.platform.util.Base;
import com.platform.util.Page;
import com.platform.util.SysDateUtil;

@Component
@SuppressWarnings("rawtypes")
public class BaseDao extends Base
{
	@Resource(name = "hibernateTemplates")
	private HibernateTemplates HibernateTemplates;

	public HibernateTemplate getHibernateTemplate()
	{
		return HibernateTemplates.getTemplates().get("DEFAULT");
	}

	public HibernateTemplate getHibernateTemplate(String name)
	{
		return HibernateTemplates.getTemplates().get(name);
	}

	public List getList(DetachedCriteria criteria)
	{
		return getList(criteria, null);
	}

	public List getList(DetachedCriteria criteria, HibernateTemplate ht)
	{
		HibernateTemplate template = null;
		if (ht == null)
		{
			template = this.getHibernateTemplate();
		} else
		{
			template = ht;
		}
		return template.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	private List getCountByCriteria(final DetachedCriteria detachedCriteria, Page page, HibernateTemplate ht)
	{
		final int firstResult = page.getStartIndex();
		final int pageSize = page.getRp();
		if (StringUtils.isNotEmpty(page.getSortname()) && StringUtils.isNotEmpty(page.getSortorder())
				&& !"undefined".equals(page.getSortname()) && !"undefined".equals(page.getSortorder()))
		{
			if ("ASC".equalsIgnoreCase(page.getSortorder()))
			{
				detachedCriteria.addOrder(Order.asc(page.getSortname()));
			} else
			{
				detachedCriteria.addOrder(Order.desc(page.getSortname()));
			}
		}
		HibernateTemplate template = null;
		if (ht == null)
		{
			template = this.getHibernateTemplate();
		} else
		{
			template = ht;
		}
		Map returnMap = (Map) template.execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException
			{
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				List list = null;
				Map map = new HashMap();
				list = criteria.setFirstResult(firstResult).setMaxResults(pageSize).list();
				map.put("list", list);
				criteria.setFirstResult(0).setMaxResults(214783647);
				Object obj = criteria.setProjection(Projections.rowCount()).uniqueResult();
				if(obj!= null)
				{
				    map.put("totalCount",((Integer)obj ).intValue());
				}else
				{
				    map.put("totalCount",0);
				}
				
				return map;
			}
		});
		int i = (Integer) returnMap.get("totalCount");
		page.setTotolResultNum(i);
		return (List) returnMap.get("list");
	}

	public List queryListByPage(DetachedCriteria detachedCriteria, Page page, HibernateTemplate ht)
	{
		return getCountByCriteria(detachedCriteria, page, ht);
	}

	public List queryListByPage(DetachedCriteria detachedCriteria, Page page)
	{
		return getCountByCriteria(detachedCriteria, page, null);
	}

	public void saveOrUpdate(Object obj, HibernateTemplate ht)
	{
		HibernateTemplate template = null;
		if (ht == null)
		{
			template = this.getHibernateTemplate();
		} else
		{
			template = ht;
		}
		template.saveOrUpdate(obj);
		template.flush();
	}

	public void saveOrUpdate(Object obj)
	{
		saveOrUpdate(obj, this.getHibernateTemplate());
	}

	public void saveOrUpdate(Object obj, String htn)
	{
		saveOrUpdate(obj, this.getHibernateTemplate(htn));
	}

	public void saveOrUpdateAll(Collection obj, HibernateTemplate ht)
	{
		HibernateTemplate template = null;
		if (ht == null)
		{
			template = this.getHibernateTemplate();
		} else
		{
			template = ht;
		}
		template.saveOrUpdateAll(obj);
		template.flush();
	}

	public void saveOrUpdateAll(Collection obj)
	{
		saveOrUpdate(obj, this.getHibernateTemplate());
	}

	public void saveOrUpdateAll(Collection obj, String htn)
	{
		saveOrUpdate(obj, this.getHibernateTemplate(htn));
	}

	public Date getSysDate()
	{
		return SysDateUtil.getSysDate();
	}

	public void delete(Object obj, HibernateTemplate ht)
	{
		try
		{
			ht.delete(obj);
			ht.flush();
		} catch (DataAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Object obj, String htn)
	{
		this.delete(obj, this.getHibernateTemplate(htn));

	}

	public void delete(Object obj)
	{
		this.delete(obj, this.getHibernateTemplate());
	}

	public Object get(int id, Class clazz)
	{
		return this.get(id, clazz, this.getHibernateTemplate());
	}

	public Object get(int id, Class clazz, String htn)
	{
		return this.get(id, clazz, this.getHibernateTemplate(htn));
	}

	@SuppressWarnings("unchecked")
	public Object get(int id, Class clazz, HibernateTemplate ht)
	{
		return ht.get(clazz, id);
	}

	public void deleteSql(String sql, String htn)
	{
		this.deleteSql(sql, this.getHibernateTemplate(htn));

	}

	public void deleteSql(String sql)
	{
		this.deleteSql(sql, this.getHibernateTemplate());
	}

	public void deleteSql(String sql, HibernateTemplate ht)
	{
		Session session = ht.getSessionFactory().getCurrentSession();
		session.createSQLQuery(sql).executeUpdate();
		session.close();
	}

	public List queryList(DetachedCriteria detachedCriteria)
	{
		return this.queryList(detachedCriteria, this.getHibernateTemplate());
	}

	public List queryList(DetachedCriteria detachedCriteria, HibernateTemplate ht)
	{
		HibernateTemplate template = null;
		if (ht == null)
		{
			template = this.getHibernateTemplate();
		} else
		{
			template = ht;
		}
		return template.findByCriteria(detachedCriteria);
	}

	public void updateHQL(String hql, String htn)
	{
		this.updateHQL(hql, this.getHibernateTemplate(htn));

	}

	public void updateHQL(String hql)
	{
		this.updateHQL(hql, this.getHibernateTemplate());
	}

	public void updateHQL(String hql, HibernateTemplate ht)
	{
		try
		{
			Session session = ht.getSessionFactory().getCurrentSession();
			session.createQuery(hql).executeUpdate();
			session.flush();
//			session.close();
		} catch (HibernateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
