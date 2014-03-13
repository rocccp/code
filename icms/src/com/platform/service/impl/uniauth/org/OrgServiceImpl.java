package com.platform.service.impl.uniauth.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.platform.entity.code.Effective;
import com.platform.entity.uniauth.org.Org;
import com.platform.entity.uniauth.org.OrgType;
import com.platform.entity.uniauth.user.User;
import com.platform.service.base.BaseService;
import com.platform.service.iface.uniauth.org.IOrgService;
import com.platform.util.Constant;
import com.platform.util.JsonUtil;
import com.platform.vo.uniauth.org.OrgVO;

@SuppressWarnings({ "unchecked" })
@Service
public class OrgServiceImpl extends BaseService implements IOrgService
{

	@Override
	public String queryOrg()
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Org.class);
		detachedCriteria.add(Restrictions.isNull("parent"));
		List<Org> org = this.getDao("baseDao").queryList(detachedCriteria);
		Set<Org> orgSet = new HashSet<Org>(org);
		List<OrgVO> list = new ArrayList<OrgVO>();

		for (Org o : orgSet)
		{
			getAllOrg(o, list);
		}

		String orgStr = JsonUtil.object2json(list);
		return orgStr;
	}

	private void getAllOrg(Org o, List<OrgVO> list)
	{
		OrgVO orgVO = new OrgVO();
		orgVO.setId(o.getId());
		orgVO.setEff(o.getEffective().getId());
		orgVO.setName(o.getName());
		orgVO.setOrgType(o.getType().getId());
		if (o.getParent() != null)
		{
			orgVO.setpId(o.getParent().getId());
		}
		list.add(orgVO);
		if (o.getChildren() != null)
		{
			for (Org oc : o.getChildren())
			{
				getAllOrg(oc, list);
			}

		}
	}

	@Override
	public void deleteOrg(Org org)
	{
		org = (Org) this.getDao("baseDao").get(org.getId(), Org.class);
		if (org.getChildren() != null && org.getChildren().size() > 0)
		{
			for (Org o : org.getChildren())
			{
				deleteOrg(o);
			}
		}
		org.setChildren(null);
		org.setParent(null);
		String hql = "UPDATE User u SET u.org = null WHERE u.org.id = " + org.getId();
		this.getDao("baseDao").updateHQL(hql);
		this.getDao("baseDao").delete(org);

	}

	@Override
	public Org addOrg(Org org)
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		Org parent = null;
		parent = (Org) this.getDao("baseDao").get(org.getParent().getId(), Org.class);
		org.setType(parent.getType());
		org.setLevel(parent.getLevel() + 1);
		org.setName("新增部门");
		Effective effective = new Effective();
		effective.setId(Constant.EFFECTIVE_EFFECT);
		org.setEffective(effective);
		org.setInsertTime(date);
		org.setInsertUser(u);
		org.setUpdateTime(date);
		org.setUpdateUser(u);
		this.getDao("baseDao").saveOrUpdate(org);
		return org;
	}

	@Override
	public Org updateOrg(Org org)
	{
		Date date = this.getSysDate();
		User u = this.getLogonUser();
		Org o = null;
		o = (Org) this.getDao("baseDao").get(org.getId(), Org.class);
		o.setName(org.getName());
		Effective effective = (Effective) this.getDao("baseDao").get(org.getEffective().getId(), Effective.class);
		o.setEffective(effective);
		OrgType type = (OrgType) this.getDao("baseDao").get(org.getType().getId(), OrgType.class);
		o.setType(type);
		int level = 0;
		if (o.getType().getId() == 1)
		{
			if (o.getParent() != null)
			{
				level = o.getParent().getLevel() + 1;
			} else
			{
				level = level + 1;
			}

		} else
		{
			if (o.getParent() != null)
			{
				level = o.getParent().getLevel();
			}

		}
		o.setLevel(level);
		o.setUpdateTime(date);
		o.setUpdateUser(u);
		this.getDao("baseDao").saveOrUpdate(o);
		return org;
	}

	@Override
	public void saveMove(Org org)
	{
		Org o = null;
		o = (Org) this.getDao("baseDao").get(org.getId(), Org.class);

		if (org.getParent().getId() == 0)
		{
			o.setParent(null);
		} else
		{
			Org parent = null;
			parent = (Org) this.getDao("baseDao").get(org.getParent().getId(), Org.class);
			o.setParent(parent);
		}
		this.getDao("baseDao").saveOrUpdate(o);
	}

}
