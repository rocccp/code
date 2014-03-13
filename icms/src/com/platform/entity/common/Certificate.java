package com.platform.entity.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.platform.entity.base.BaseEntity;
import com.platform.entity.code.CertificateType;

@Entity
@Table(name = "T_PLATFORM_CERTIFICATE")
public class Certificate extends BaseEntity implements Comparable<Object>
{
	private static final long serialVersionUID = -3262545788312215464L;

	private int id;

	private CertificateType type;

	private String num;

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

	@ManyToOne
	@JoinColumn(name = "type")
	public CertificateType getType()
	{
		return type;
	}

	public void setType(CertificateType type)
	{
		this.type = type;
	}

	public String getNum()
	{
		return num;
	}

	public void setNum(String num)
	{
		this.num = num;
	}

	@Override
	public int hashCode()
	{
		return this.getId();
	}

	@Override
	public boolean equals(Object obj)
	{

		if (((Certificate) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o)
	{
		if (((Certificate) o).getId() == this.getId())
		{
			return 0;
		} else if (((Certificate) o).getId() > this.getId())
		{
			return -1;
		} else
		{
			return 1;
		}
	}
}
