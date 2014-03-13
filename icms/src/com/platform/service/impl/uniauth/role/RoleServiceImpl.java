package com.platform.service.impl.uniauth.role;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.platform.entity.uniauth.auth.Auth;
import com.platform.entity.uniauth.role.Role;
import com.platform.service.base.BaseService;
import com.platform.service.iface.uniauth.role.IRoleService;
import com.platform.util.Page;
import com.platform.vo.uniauth.role.RoleVO;

@SuppressWarnings({ "unchecked" })
@Service
public class RoleServiceImpl extends BaseService implements IRoleService
{

	@Override
	public List<RoleVO> queryList(Page p)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		List<Role> list = this.getDao("baseDao").queryListByPage(detachedCriteria, p);
		List<RoleVO> rows = new ArrayList<RoleVO>();
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
				rows.add(role);
			}
		}
		return rows;
	}

	@Override
	public void deleteRole(Role role)
	{

		role = (Role) this.get(role.getId(), Role.class);
		role.setAuths(null);
		this.getDao("baseDao").delete(role);
	}

	@Override
	public String queryAuth(String authId)
	{
		String cPath = this.getRequest().getContextPath();
		String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":"
				+ this.getRequest().getServerPort() + cPath;

		Role role = (Role) this.get(Integer.parseInt(authId), Role.class);
		List<Auth> roleAuths = role.getAuths();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Auth.class);
		List<Auth> authList = this.queryList(detachedCriteria);
		String enter = "\n";
		// //定义xtree
		StringBuffer str = new StringBuffer("<script>").append(enter);
		str.append("webFXTreeConfig.setImagePath('").append(basePath).append("/pages/images/xtree');");
		str.append("var node0 = new WebFXTree('权限');").append(enter);
		for (Auth j : authList)
		{
			str.append("var node").append(j.getId()).append("=new WebFXCheckBoxTreeItem('").append(j.getAuthName())
					.append("',").append(j.getId()).append(",");
			if (j.getParent() == null)
			{
				str.append("node0");
			} else
			{
				str.append("node").append(j.getParent().getId());
			}
			str.append(",null");
			if (roleAuths.contains(j))
			{
				str.append(",true");
			}
			str.append(");").append(enter);
		}
		str.append("document.write(node0);").append(enter);
		str.append("</script>").append(enter);
		return str.toString();
	}

	@Override
	public void saveAuth(Role role, List<Auth> auths)
	{

		role = (Role) this.get(role.getId(), Role.class);
		List<Auth> authsList = new ArrayList<Auth>();
		Auth auth;
		for (Auth a : auths)
		{
			auth = (Auth) this.get(a.getId(), Auth.class);
			authsList.add(auth);
			auth = null;
		}
		role.setAuths(authsList);
		this.saveOrUpdate(role);
	}

}
