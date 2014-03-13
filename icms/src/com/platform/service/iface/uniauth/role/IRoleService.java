package com.platform.service.iface.uniauth.role;

import java.util.List;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.role.Role;
import com.platform.util.Page;
import com.platform.vo.uniauth.role.RoleVO;

public interface IRoleService
{

	List<RoleVO> queryList(Page p);

	void deleteRole(Role role);

	String queryAuth(String authId);

	void saveAuth(Role role, List<Auth> auths);

}
