package com.platform.service.iface.uniauth.platform;

import java.util.List;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.util.Page;
import com.platform.vo.uniauth.platform.Platform;

public interface IPlatformService
{
	List<Platform> queryPlatformList(Page p);

	boolean checkPlatform(Auth platform);

	void deletePlatfrom(Auth platform);

}
