package com.platform.vo.uniauth.platform;

import com.platform.vo.base.BaseVo;

public class Platform extends BaseVo
{
	public int id;

	private String authName;

	private String authCode;

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

	public String getAuthCode()
	{
		return authCode;
	}

	public void setAuthCode(String authCode)
	{
		this.authCode = authCode;
	}

}
