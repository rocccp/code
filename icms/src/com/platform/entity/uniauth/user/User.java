package com.platform.entity.uniauth.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.platform.entity.base.BaseEntity;
import com.platform.entity.code.PersonStatus;
import com.platform.entity.code.PersonType;
import com.platform.entity.code.Sex;
import com.platform.entity.common.Certificate;
import com.platform.entity.uniauth.org.Org;
import com.platform.entity.uniauth.role.Role;

@Entity
@Table(name = "T_PLATFORM_USER")
public class User extends BaseEntity implements Comparable<Object>
{
	private static final long serialVersionUID = -8565873417451840447L;

	private int id;

	private String account;

	private String password;

	private String name;

	private String email;

	private Certificate certificate;

	private Date birthday;

	private String phone;

	private Sex sex;

	private PersonType personType;

	private Org org;

	private PersonStatus status;

	private List<Role> roles;

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

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@ManyToOne
	@JoinColumn(name = "certificate")
	public Certificate getCertificate()
	{
		return certificate;
	}

	public void setCertificate(Certificate certificate)
	{
		this.certificate = certificate;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@ManyToOne
	@JoinColumn(name = "sex")
	public Sex getSex()
	{
		return sex;
	}

	public void setSex(Sex sex)
	{
		this.sex = sex;
	}

	@ManyToOne
	@JoinColumn(name = "personType" )
	public PersonType getPersonType()
	{
		return personType;
	}

	public void setPersonType(PersonType personType)
	{
		this.personType = personType;
	}

	@ManyToOne
	@JoinColumn(name = "org")
	public Org getOrg()
	{
		return org;
	}

	public void setOrg(Org org)
	{
		this.org = org;
	}

	@ManyToOne
	@JoinColumn(name = "status")
	public PersonStatus getStatus()
	{
		return status;
	}

	public void setStatus(PersonStatus status)
	{
		this.status = status;
	}

	@ManyToMany
	@JoinTable(name = "T_PLATFORM_USER_ROLE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}

	@Override
	public int hashCode()
	{
		return this.getId();
	}

	@Override
	public boolean equals(Object obj)
	{

		if (((User) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o)
	{

		if (((User) o).getId() == this.getId())
		{
			return 0;
		} else if (((User) o).getId() > this.getId())
		{
			return -1;
		} else
		{
			return 1;
		}
	}
}
