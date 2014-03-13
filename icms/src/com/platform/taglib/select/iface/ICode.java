package com.platform.taglib.select.iface;

import org.springframework.web.servlet.support.RequestContext;

import com.platform.entity.base.BaseCodeEntity;

public interface ICode
{
	String getHtml(String value, RequestContext requestContext, String type, String codeName, BaseCodeEntity temp,
			String inIds, String nullFilter);

}
