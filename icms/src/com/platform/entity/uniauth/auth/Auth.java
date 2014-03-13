package com.platform.entity.uniauth.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.platform.entity.base.BaseEntity;
import com.platform.entity.uniauth.role.Role;

@Component
@Entity
@Table(name = "T_PLATFORM_AUTH")
public class Auth extends BaseEntity implements Comparable<Object>
{

	private static final long serialVersionUID = -1809041700740452744L;

	private int id;

	private String authName;

	private String authCode;

	private String url;

	private String icoUrl;

	private Auth parent;

	private List<Auth> children = new ArrayList<Auth>();

	private List<AuthPath> authPaths;

	private List<Role> roles;

	@ManyToMany(mappedBy = "auths")
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	public String getAuthCode()
	{
		return authCode;
	}

	public void setAuthCode(String authCode)
	{
		this.authCode = authCode;
	}

	public String getIcoUrl()
	{
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl)
	{
		this.icoUrl = icoUrl;
	}

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAuthName()
	{
		return authName;
	}

	public void setAuthName(String authName)
	{
		this.authName = authName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	public Auth getParent()
	{
		return parent;
	}

	public void setParent(Auth parent)
	{
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Auth> getChildren()
	{
		return children;
	}

	public void setChildren(List<Auth> children)
	{
		this.children = children;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_id")
	public List<AuthPath> getAuthPaths()
	{
		return authPaths;
	}

	public void setAuthPaths(List<AuthPath> authPaths)
	{
		this.authPaths = authPaths;
	}

	@Override
	public int hashCode()
	{
		return id;
	}

	@Override
	public boolean equals(Object obj)
	{

		if (((Auth) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o)
	{
		if (((Auth) o).getId() == this.getId())
		{
			return 0;
		} else if (((Auth) o).getId() > this.getId())
		{
			return -1;
		} else
		{
			return 1;
		}
	}

	@Override
	public String toString()
	{

		return "{id:" + id + ",authName:" + authName + ",authCode:" + authCode + ",url:" + url + ",icoUrl:" + icoUrl
				+ "}";
	}

}
