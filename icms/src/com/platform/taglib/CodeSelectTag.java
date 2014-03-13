package com.platform.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;

import com.platform.entity.base.BaseCodeEntity;
import com.platform.taglib.select.iface.ICode;
import com.platform.taglib.select.impl.OrgCodeImpl;

public class CodeSelectTag extends TagSupport
{
	private static final long serialVersionUID = 1L;

	private String clazz;

	private String id;

	private String name;

	private String type; // 空 或 all （所有） effective（有效的）invalid （无效的）

	private String codeName;

	private String scopeEntityName;

	private String readonly;

	private String onChange;

	private String value;

	private String style;

	private String inIds;

	private String nullFilter;

	private String orgType;

	private String orgLevel;

	private String mustInput;

	private String title;

	@Override
	public int doStartTag() throws JspException
	{
		// 获取requestContext 用户获取Spring容器内的Bean
		RequestContext requestContext = (RequestContext) pageContext
				.getAttribute("org.springframework.web.servlet.tags.REQUEST_CONTEXT");
		if (requestContext == null)
		{
			requestContext = new JspAwareRequestContext(pageContext);
			pageContext.setAttribute("org.springframework.web.servlet.tags.REQUEST_CONTEXT", requestContext);
		}
		// 获取HttpServletRequest并得到user
		// 是TagSupport类中定义的一个属性，它是javax.servlet.jsp.PageContext的对象
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer("<select platformInput = 'true' id = '");
		html.append(this.id).append("' ");
		if (!"".equals(this.name) && this.name != null)
		{
			html.append("name = '").append(this.name).append("' ");
		}
		if (!"".equals(this.clazz) && this.clazz != null)
		{
			html.append("class = '").append(this.clazz).append("' ");
		}
		if (!"".equals(this.style) && this.style != null)
		{
			html.append("style = '").append(this.style).append("' ");
		}
		if (!"".equals(this.readonly) && this.readonly != null)
		{
			if ("true".equals(this.readonly))
			{
				html.append("disabled = 'disabled' ");
			}
		}
		if (!"".equals(this.onChange) && this.onChange != null)
		{
			html.append("onchange = '").append(this.onChange).append("' ");
		}
		if (!"".equals(this.mustInput) && this.mustInput != null)
		{
			html.append("mustInput = '").append(this.mustInput).append("' ");
		}
		if (!"".equals(this.title) && this.title != null)
		{
			html.append("title = '").append(this.title).append("' ");
		}
		html.append(">");

		BaseCodeEntity temp = null;
		if (scopeEntityName != null)
		{
			temp = (BaseCodeEntity) request.getAttribute(scopeEntityName);
		}

		ICode code = null;
		if ("platformCode".equals(codeName))
		{
			code = (ICode) requestContext.getWebApplicationContext().getBean("platfromCodeImpl");
			html.append(code.getHtml(value, requestContext, type, codeName, temp, inIds, nullFilter));

		} else if ("org".equals(codeName))
		{
			OrgCodeImpl orgCode = (OrgCodeImpl) requestContext.getWebApplicationContext().getBean("orgCodeImpl");
			html.append(orgCode.orgCode(value, requestContext, type, codeName, temp, inIds, nullFilter, orgType,
					orgLevel));

		} else
		{
			code = (ICode) requestContext.getWebApplicationContext().getBean("codeImpl");
			html.append(code.getHtml(value, requestContext, type, codeName, temp, inIds, nullFilter));
		}

		html.append("</select>");

		try
		{
			out.println(html.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}

	public String getScopeEntityName()
	{
		return scopeEntityName;
	}

	public void setScopeEntityName(String scopeEntityName)
	{
		this.scopeEntityName = scopeEntityName;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getOnChange()
	{
		return onChange;
	}

	public void setOnChange(String onChange)
	{
		this.onChange = onChange;
	}

	public String getReadonly()
	{
		return readonly;
	}

	public void setReadonly(String readonly)
	{
		this.readonly = readonly;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getInIds()
	{
		return inIds;
	}

	public void setInIds(String inIds)
	{
		this.inIds = inIds;
	}

	public String getNullFilter()
	{
		return nullFilter;
	}

	public void setNullFilter(String nullFilter)
	{
		this.nullFilter = nullFilter;
	}

	public String getOrgType()
	{
		return orgType;
	}

	public void setOrgType(String orgType)
	{
		this.orgType = orgType;
	}

	public String getOrgLevel()
	{
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel)
	{
		this.orgLevel = orgLevel;
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

}
