package com.platform.taglib.input;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

public class InputTag extends TagSupport
{
	private String type;

	private String name;

	private String id;

	private String clazz;

	private String checkStyle;

	private String value;

	private String mustInput;

	private String title;

	private String onClick;

	private String onblur;

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		try
		{
			html.append("<input platformInput = 'true' ");
			if (StringUtils.isNotEmpty(type))
			{
				html.append(" type=\"").append(type).append("\" ");
			}
			if (StringUtils.isNotEmpty(name))
			{
				html.append(" name=\"").append(name).append("\" ");
			}
			if (StringUtils.isNotEmpty(id))
			{
				html.append(" id=\"").append(id).append("\" ");
			}
			if (StringUtils.isNotEmpty(clazz))
			{
				html.append(" clazz=\"").append(clazz).append("\" ");
			}
			if (StringUtils.isNotEmpty(title))
			{
				html.append(" title=\"").append(title).append("\" ");
			}
			if (StringUtils.isNotEmpty(mustInput))
			{
				html.append(" mustInput=\"").append(mustInput).append("\" ");
			}
			if (StringUtils.isNotEmpty(onClick))
			{
				html.append(" onClick=\"").append(onClick).append("\" ");
			}
			if (StringUtils.isNotEmpty(onblur))
			{
				html.append(" onblur=\"").append(onblur).append("\" ");
			}
			html.append(" />");
			out.println(html.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return 0;

	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getCheckStyle()
	{
		return checkStyle;
	}

	public void setCheckStyle(String checkStyle)
	{
		this.checkStyle = checkStyle;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getMustInput()
	{
		return mustInput;
	}

	public void setMustInput(String mustInput)
	{
		this.mustInput = mustInput;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getOnClick()
	{
		return onClick;
	}

	public void setOnClick(String onClick)
	{
		this.onClick = onClick;
	}

	public String getOnblur()
	{
		return onblur;
	}

	public void setOnblur(String onblur)
	{
		this.onblur = onblur;
	}

}
