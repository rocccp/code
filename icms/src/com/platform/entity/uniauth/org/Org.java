package com.platform.entity.uniauth.org;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.platform.entity.base.BaseEntity;

@Entity
@Table(name = "T_PLATFORM_ORG")
public class Org extends BaseEntity implements Comparable<Object>
{

	private static final long serialVersionUID = -700401435017880360L;

	private int id;

	private String name;

	private Org parent;

	private List<Org> children;

	private OrgType type;

	private int level;

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

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Org getParent()
	{
		return parent;
	}

	public void setParent(Org parent)
	{
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	public List<Org> getChildren()
	{
		return children;
	}

	public void setChildren(List<Org> children)
	{
		this.children = children;
	}

	@ManyToOne
	@JoinColumn(name = "type")
	public OrgType getType()
	{
		return type;
	}

	public void setType(OrgType type)
	{
		this.type = type;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	@Override
	public int hashCode()
	{
		return this.getId();
	}

	@Override
	public boolean equals(Object obj)
	{

		if (((Org) obj).getId() == this.getId())
		{
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o)
	{

		Org org = (Org) o;
		if (org.getLevel() > this.getLevel())
		{
			return -1;
		} else if (org.getLevel() < this.getLevel())
		{
			return 1;
		} else
		{
			if (org.getId() > this.getId())
			{
				return -1;
			} else if (org.getId() < this.getId())
			{
				return 1;
			}
			return 0;
		}
		// if (org.getParent() == null)
		// {
		// if (this.getParent() == null)
		// {
		// if (org.getId() > this.getId())
		// {
		// return 1;
		// } else if (org.getId() < this.getId())
		// {
		// return -1;
		// } else
		// {
		// return 0;
		// }
		// } else
		// {
		// return -1;
		// }
		// } else
		// {
		// if (this.getParent() == null)
		// {
		// return -1;
		// } else
		// {
		// if(org.getParent())
		//
		// }
		// }
		//
		// if (((Org) o).getId() == this.getId())
		// {
		// return 0;
		// } else if (((Org) o).getId() > this.getId())
		// {
		// return -1;
		// } else
		// {
		// return 1;
		// }
	}
}