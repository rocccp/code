package com.platform.dao.base;

import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class HibernateTemplates
{

	public Map<String, HibernateTemplate> templates;

	public Map<String, HibernateTemplate> getTemplates()
	{
		return templates;
	}

	public void setTemplates(Map<String, HibernateTemplate> templates)
	{
		this.templates = templates;
	}

	// public void setSessionFactoryBeans(List<SessionFactoryBean>
	// sessionFactoryBeans)
	// {
	// tempplates = new HashMap<String, HibernateTemplate>();
	// HibernateTemplate template;
	// for (SessionFactoryBean sfb : sessionFactoryBeans)
	// {
	// template = new HibernateTemplate();
	// template.setSessionFactory(sfb.getSessionFactory());
	// tempplates.put(sfb.getName(), template);
	// template = null;
	// }
	// }

}
