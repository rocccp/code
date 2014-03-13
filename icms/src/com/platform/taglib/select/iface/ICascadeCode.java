package com.platform.taglib.select.iface;

import org.springframework.web.servlet.support.RequestContext;

public interface ICascadeCode
{
    String getHtml(String value, Object obj, RequestContext requestContext, String type, String funName,String pId,String id);

}
