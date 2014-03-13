package com.platform.service.iface.uniauth.user;

import java.util.List;
import java.util.Map;

import com.platform.entity.uniauth.user.User;
import com.platform.util.Page;
import com.platform.vo.uniauth.role.RoleVO;
import com.platform.vo.uniauth.user.UserVO;

public interface IUserService
{

	List<UserVO> queryList(Page p, List<Map<String, String>> list);

	boolean checkUser(User user);

	void deleteUser(User user);

	List<RoleVO> queryRolesList(Page p, User user);

	void saveRoles(String msg, User user);

}
