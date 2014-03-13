package com.platform.service.iface.uniauth.auth;

import java.util.List;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.util.Page;
import com.platform.vo.uniauth.auth.AuthVo;

public interface IAuthService
{
	List<AuthVo> queryAuthList(Page p);

	boolean checkAuth(Auth auth);

	void deleteAuth(Auth auth);

}
