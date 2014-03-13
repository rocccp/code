package com.platform.service.impl.login;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

import com.platform.entity.code.Effective;
import com.platform.entity.uniauth.user.User;
import com.platform.service.base.BaseService;
import com.platform.service.iface.login.ILoginService;
import com.platform.util.Constant;
import com.platform.util.MD5CodeUtil;

@Component
@SuppressWarnings("unchecked")
public class LoginServiceImpl extends BaseService implements ILoginService
{

	@Override
	public User login(User user)
	{
		String pw = MD5CodeUtil.encodeByMD5("PLATFORM" + user.getAccount() + user.getPassword());
		Effective effective = new Effective();
		effective.setId(Constant.EFFECTIVE_EFFECT);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("account", user.getAccount()));
		detachedCriteria.add(Restrictions.eq("password", pw));
		detachedCriteria.add(Restrictions.eq("effective", effective));
		List<User> users = this.getDao("baseDao").getList(detachedCriteria);
		if (users != null && users.size() > 0)
		{
			user = users.get(0);
		} else
		{
			user = null;
		}
		return user;
	}

	@Override
	public User ssologin(String name)
	{
		User user = null;
		Effective effective = new Effective();
		BASE64Decoder decoder = new BASE64Decoder();
		try
		{
			name = new String(decoder.decodeBuffer(name));
			effective.setId(Constant.EFFECTIVE_EFFECT);
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
			detachedCriteria.add(Restrictions.eq("account", name));
			detachedCriteria.add(Restrictions.eq("effective", effective));
			List<User> users = this.getDao("baseDao").getList(detachedCriteria);
			if (users != null && users.size() > 0)
			{
				user = users.get(0);
			} else
			{
				user = null;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return user;
	}

}
