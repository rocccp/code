package com.platform.entity.uniauth.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.platform.entity.base.BaseEntity;

@Entity
@Table(name = "T_PLATFORM_AUTHPATH")
public class AuthPath extends BaseEntity
{
	private static final long serialVersionUID = 3461577831282904269L;

	private int id;

	private String path;
	
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

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

}
