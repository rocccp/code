package com.platform.service.base;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.platform.dao.base.BaseDao;
import com.platform.util.Base;
import com.platform.util.Page;
import com.platform.util.SysDateUtil;

@Component
@SuppressWarnings("rawtypes")
public class BaseService extends Base
{

    public BaseDao getDao(String daoName)
    {
        return (BaseDao) this.getSpringBean(daoName);
    }

    public void saveOrUpdate(Object obj)
    {
        this.getDao("baseDao").saveOrUpdate(obj);
    }

    public void saveOrUpdate(Object obj, String htn)
    {
        this.getDao("baseDao").saveOrUpdate(obj, htn);
    }

    public void saveOrUpdateAll(Collection obj)
    {
        this.getDao("baseDao").saveOrUpdateAll(obj);
    }

    public void saveOrUpdateAll(Collection obj, String htn)
    {
        this.getDao("baseDao").saveOrUpdateAll(obj, htn);
    }

    public Date getSysDate()
    {
        return SysDateUtil.getSysDate();
    }

    public void delete(Object obj)
    {
        this.getDao("baseDao").delete(obj);

    }

    public void delete(Object obj, String htn)
    {
        this.getDao("baseDao").delete(obj, htn);
    }

    public Object get(int id, Class clazz, String htn)
    {
        return this.getDao("baseDao").get(id, clazz, htn);
    }

    public Object get(int id, Class clazz)
    {
        return this.getDao("baseDao").get(id, clazz);
    }

    public void deleteSql(String sql)
    {
        this.getDao("baseDao").deleteSql(sql);

    }

    public void deleteSql(String sql, String htn)
    {
        this.getDao("baseDao").deleteSql(sql, htn);
    }

    public List queryList(DetachedCriteria detachedCriteria)
    {
        return this.getDao("baseDao").queryList(detachedCriteria);
    }

    public List queryListByPage(DetachedCriteria detachedCriteria, Page page)
    {
        return this.getDao("baseDao").queryListByPage(detachedCriteria, page);
    }

    public DetachedCriteria initDetachedCriteria(DetachedCriteria detachedCriteria, List<Map<String, String>> filters)
    {
        for (Map<String, String> map : filters)
        {
            if (StringUtils.isNotEmpty(map.get("value")))
            {
                detachedCriteria.add(getCriterion(map));
            }

        }
        return detachedCriteria;

    }

    private Criterion getCriterion(Map<String, String> filter)
    {
        String vType = filter.get("vType");
        String v = filter.get("value");
        String qType = filter.get("qType");
        String name = filter.get("name");

        Object value = null;
        Criterion c = null;
        if ("int".equals(vType))
        {
            value = Integer.parseInt(v);
        } else if ("double".equals(vType))
        {
            value = Double.parseDouble(v);
        } else if ("boolean".equals(vType))
        {
            value = v;
        } else
        {
            value = v;
        }
        if ("=".equals(qType))
        {
            c = Restrictions.eq(name, value);
        } else if (">=".equals(qType))
        {
            c = Restrictions.ge(name, value);
        } else if (">".equals(qType))
        {
            c = Restrictions.gt(name, value);
        } else if ("<".equals(qType))
        {
            c = Restrictions.lt(name, value);
        } else if ("<=".equals(qType))
        {
            c = Restrictions.le(name, value);
        } else if ("like".equals(qType))
        {
            String matchMode = filter.get("matchMode");
            if (StringUtils.isNotEmpty(matchMode))
            {
                MatchMode mm = null;
                if ("anywhere".equals(matchMode))
                {
                    mm = MatchMode.ANYWHERE;
                } else if ("start".equals(matchMode))
                {
                    mm = MatchMode.START;
                } else if ("end".equals(matchMode))
                {
                    mm = MatchMode.END;
                }
                c = Restrictions.like(name, v, mm);
            } else
            {
                c = Restrictions.like(name, value);
            }

        }
        return c;

    }

}
