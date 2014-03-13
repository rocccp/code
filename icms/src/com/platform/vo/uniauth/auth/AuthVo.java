package com.platform.vo.uniauth.auth;

import java.util.List;

import com.platform.entity.uniauth.auth.AuthPath;
import com.platform.vo.base.BaseVo;

public class AuthVo extends BaseVo
{
	private int id;

	private String authName;

	private String authCode;

	private String url;

	private int platformId;

	private String icoUrl;

	private String platformName;

	private List<AuthPath> authPaths;

	public int getPlatformId()
	{
		return platformId;
	}

	public void setPlatformId(int platformId)
	{
		this.platformId = platformId;
	}

	public String getPlatformName()
	{
		return platformName;
	}

	public void setPlatformName(String platformName)
	{
		this.platformName = platformName;
	}

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

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getIcoUrl()
	{
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl)
	{
		this.icoUrl = icoUrl;
	}

	public List<AuthPath> getAuthPaths()
	{
		return authPaths;
	}

	public void setAuthPaths(List<AuthPath> authPaths)
	{
		this.authPaths = authPaths;
	}
}
