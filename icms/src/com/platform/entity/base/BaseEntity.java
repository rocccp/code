package com.platform.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.platform.entity.code.Effective;
import com.platform.entity.uniauth.user.User;

@MappedSuperclass
public class BaseEntity implements Serializable 
{
	private static final long serialVersionUID = 7912772752244886527L;

	private Effective effective;

	private Date insertTime;

	private User insertUser;

	private Date updateTime;

	private User updateUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "effective_flag")
	public Effective getEffective()
	{
		return effective;
	}

	public void setEffective(Effective effective)
	{
		this.effective = effective;
	}

	public Date getInsertTime()
	{
		return insertTime;
	}

	public void setInsertTime(Date insertTime)
	{
		this.insertTime = insertTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insertUser")
	public User getInsertUser()
	{
		return insertUser;
	}

	public void setInsertUser(User insertUser)
	{
		this.insertUser = insertUser;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateUser")
	public User getUpdateUser()
	{
		return updateUser;
	}

	public void setUpdateUser(User updateUser)
	{
		this.updateUser = updateUser;
	}

}
