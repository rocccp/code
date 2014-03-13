package com.platform.service.impl.uniauth.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.SystemException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.platform.entity.code.Effective;
import com.platform.entity.code.PersonType;
import com.platform.entity.common.Certificate;
import com.platform.entity.uniauth.org.Org;
import com.platform.entity.uniauth.role.Role;
import com.platform.entity.uniauth.user.User;
import com.platform.service.base.BaseService;
import com.platform.service.iface.uniauth.user.IUserService;
import com.platform.util.BeansUtils;
import com.platform.util.Constant;
import com.platform.util.DateUtils;
import com.platform.util.Page;
import com.platform.util.PlatformStringUtils;
import com.platform.util.plantform.OrgUtils;
import com.platform.vo.uniauth.role.RoleVO;
import com.platform.vo.uniauth.user.UserVO;

@Component
@SuppressWarnings("unchecked")
public class UserServiceImpl extends BaseService implements IUserService
{

	@Override
	public List<UserVO> queryList(Page p, List<Map<String, String>> filters)
	{
		List<UserVO> rows = new ArrayList<UserVO>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		boolean flag = true;
		for (Map<String, String> map : filters)
		{
			if ("org".equals(map.get("name")))
			{
				Org org = new Org();
				org.setId(Integer.parseInt(map.get("value")));
				detachedCriteria.add(Restrictions.eq("org", org));
				flag = false;
			} else if ("personType".equals(map.get("name")))
			{
				PersonType personType = new PersonType();
				personType.setId(Integer.parseInt(map.get("value")));
				detachedCriteria.add(Restrictions.eq("personType", personType));
			} else
			{
				detachedCriteria.add(Restrictions.like(map.get("name"), map.get("value"), MatchMode.ANYWHERE));
			}

		}
		if (this.getLogonUser().getOrg() != null)
		{
			if (flag)
			{
				Set<Org> orgs = OrgUtils.getSelfAndDesOrg(this.getLogonUser().getOrg(), this.getDao("baseDao")
						.getHibernateTemplate());
				detachedCriteria.add(Restrictions.in("org", orgs));
			}

		} else
		{
			p.setTotolResultNum(0);
			return rows;
		}

		List<User> users = this.getDao("baseDao").queryListByPage(detachedCriteria, p);

		Set<User> userSet = new HashSet<User>(users);

		if (users != null && userSet.size() > 0)
		{
			UserVO u = null;
			for (User a : userSet)
			{
				String updateTime = a.getUpdateTime() == null ? "" : a.getUpdateTime() + "";
				String userName = a.getUpdateUser() == null ? "" : a.getUpdateUser().getName();
				u = new UserVO();
				try
				{
					BeansUtils.copyBeanProperties(u, a);
				} catch (SystemException e)
				{
					e.printStackTrace();
				}
				u.setBirthday(DateUtils.formatDate(a.getBirthday(), "yyyy-MM-dd"));
				u.setPhone(PlatformStringUtils.changeNull(a.getPhone()));

				u.setEffective(a.getEffective().getValue());
				u.setUpdateTime(updateTime);
				u.setUpdateUser(userName);
				u.setOrgId(a.getOrg().getId() + "");
				u.setOrg(a.getOrg().getName());
				if (a.getCertificate() != null)
				{
					u.setCertificateId(a.getCertificate().getId() + "");
					u.setCertificateNum(a.getCertificate().getNum());
					u.setCertificateType(a.getCertificate().getType().getValue());
					u.setCertificateTypeId(a.getCertificate().getType().getId() + "");
				} else
				{
					u.setCertificateId("");
					u.setCertificateNum("");
					u.setCertificateType("");
					u.setCertificateTypeId("");
				}
				if (a.getPersonType() != null)
				{
					u.setPersonType(a.getPersonType().getValue());
					u.setPersonTypeId(a.getPersonType().getId() + "");
				} else
				{
					u.setPersonType("");
					u.setPersonTypeId("");
				}
				if (a.getSex() != null)
				{
					u.setSex(a.getSex().getValue());
					u.setSexId(a.getSex().getId() + "");
				} else
				{
					u.setSex("");
					u.setSexId("");
				}
				if (u.getStatus() != null)
				{
					u.setStatus(a.getStatus().getValue());
					u.setStatusId(a.getStatus().getId() + "");
				} else
				{
					u.setStatus("");
					u.setStatusId("");
				}

				rows.add(u);
			}
		}
		return rows;
	}

	@Override
	public boolean checkUser(User user)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("account", user.getAccount()));
		List<User> users = this.getDao("baseDao").queryList(detachedCriteria);
		if (users == null || users.size() == 0)
		{
			return false;
		}
		return true;

	}

	@Override
	public void deleteUser(User user)
	{
		user = (User) this.getDao("baseDao").get(user.getId(), User.class);
		if (user.getCertificate() != null)
		{
			Certificate certificate = user.getCertificate();
			user.setCertificate(null);
			this.getDao("baseDao").saveOrUpdate(user);
			this.getDao("baseDao").delete(certificate);
		}
		user.setRoles(null);
		this.getDao("baseDao").delete(user);

	}

	@Override
	public List<RoleVO> queryRolesList(Page p, User user)
	{
		user = (User) this.getDao("baseDao").get(user.getId(), User.class);
		List<RoleVO> rows = new ArrayList<RoleVO>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		Effective effective = new Effective();
		effective.setId(Constant.EFFECTIVE_EFFECT);
		detachedCriteria.add(Restrictions.eq("effective", effective));

		List<Role> list = this.getDao("baseDao").queryListByPage(detachedCriteria, p);

		if (list != null && list.size() > 0)
		{
			RoleVO role = null;
			for (Role r : list)
			{
				String updateTime = r.getUpdateTime() == null ? "" : r.getUpdateTime() + "";
				String userName = r.getUpdateUser() == null ? "" : r.getUpdateUser().getName();
				role = new RoleVO();
				role.setId(r.getId());
				role.setName(r.getName());
				role.setEffective(r.getEffective().getValue());
				role.setUpdateTime(updateTime);
				role.setUpdateUser(userName);
				if (user.getRoles().contains(r))
				{
					role.setChecked("true");
				} else
				{
					role.setChecked("false");
				}
				rows.add(role);
			}
		}
		return rows;
	}

	@Override
	public void saveRoles(String msg, User user)
	{
		user = (User) this.getDao("baseDao").get(user.getId(), User.class);
		List<Role> list = new ArrayList<Role>();
		String[] ids = msg.split(",");
		for (String id : ids)
		{
			Role r = new Role();
			r.setId(Integer.parseInt(id));
			list.add(r);
		}
		user.setRoles(list);
		this.getDao("baseDao").saveOrUpdate(user);

	}

}
