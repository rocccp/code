package com.platform.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.platform.entity.code.Effective;
import com.platform.entity.uniauth.user.User;

@MappedSuperclass
public class BaseCodeEntity implements Serializable, Comparable<Object>
{
	private static final long serialVersionUID = 7912772752244886527L;

	private int id;

	private String value;

	private Effective effective;

	private Date insertTime;

	private User insertUser;

	private Date updateTime;

	private User updateUser;

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

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@ManyToOne
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

	@ManyToOne
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

	@ManyToOne
	@JoinColumn(name = "updateUser")
	public User getUpdateUser()
	{
		return updateUser;
	}

	public void setUpdateUser(User updateUser)
	{
		this.updateUser = updateUser;
	}

	@Override
	public int compareTo(Object o)
	{
		if (((BaseCodeEntity) o).getId() == this.getId())
		{
			return 0;
		} else if (((BaseCodeEntity) o).getId() > this.getId())
		{
			return -1;
		} else
		{
			return 1;
		}
	}
	
	@Override
	public int hashCode()
	{
		return this.getId();
	}

	@Override
	public boolean equals(Object obj)
	{

		if (((BaseCodeEntity) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

}
