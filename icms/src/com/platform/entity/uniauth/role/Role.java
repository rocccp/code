package com.platform.entity.uniauth.role;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.platform.entity.base.BaseEntity;
import com.platform.entity.uniauth.auth.Auth;

@Entity
@Table(name = "T_PLATFORM_ROLE")
public class Role extends BaseEntity implements Comparable<Object>
{
	private static final long serialVersionUID = 467691225701768701L;

	private int id;

	private String name;

	private List<Auth> auths;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "T_PLATFORM_ROLE_AUTH", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "auth_id"))
	public List<Auth> getAuths()
	{
		return auths;
	}

	public void setAuths(List<Auth> auths)
	{
		this.auths = auths;
	}
	
	@Override
	public boolean equals(Object obj)
	{

		if (((Role) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o)
	{
		if (((Role) o).getId() == this.getId())
		{
			return 0;
		} else if (((Role) o).getId() > this.getId())
		{
			return -1;
		} else
		{
			return 1;
		}
	}
}
