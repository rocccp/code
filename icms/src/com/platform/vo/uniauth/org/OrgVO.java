package com.platform.vo.uniauth.org;

import com.platform.vo.base.BaseVo;

public class OrgVO extends BaseVo
{
	private int id;
	private int pId;
	private int orgType;
	private int eff;
	private String name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getpId()
	{
		return pId;
	}

	public void setpId(int pId)
	{
		this.pId = pId;
	}

	public int getOrgType()
	{
		return orgType;
	}

	public void setOrgType(int orgType)
	{
		this.orgType = orgType;
	}

	public int getEff()
	{
		return eff;
	}

	public void setEff(int eff)
	{
		this.eff = eff;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
