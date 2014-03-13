package com.platform.service.iface.login;

import com.platform.entity.uniauth.user.User;

public interface ILoginService
{
	public User login(User user);

	public User ssologin(String name);

}
