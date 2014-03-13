package com.platform.service.iface.uniauth.org;

import com.platform.entity.uniauth.org.Org;


public interface IOrgService
{

	String queryOrg();

	void deleteOrg(Org org);

	Org addOrg(Org org);

	Org updateOrg(Org org);

	void saveMove(Org org);

}
