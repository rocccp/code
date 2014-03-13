package com.platform.taglib.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Administrator
 * 
 */
public class BaseTag extends TagSupport
{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String href;

	private String target;

	/**
	 * @return the href
	 */
	public String getHref()
	{
		return href;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href)
	{
		this.href = href;
	}

	/**
	 * @return the target
	 */
	public String getTarget()
	{
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target)
	{
		this.target = target;
	}

	@Override
	public int doEndTag() throws JspException
	{
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();

		String cPath = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + cPath + "/";

		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<base ").append("href = '").append(href).append("'");
		if (this.target != null)
		{
			sb.append(" target = '").append(this.target).append("'");
		}
		sb.append("/>\n");

		sb.append("<link rel=\"shortcut icon\" href=\"").append(basePath).append("platform.ico\">\n");

		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath).append("pages/js/json.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.messager.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/flexigrid.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery-ui-1.9.2.custom.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.bgiframe-2.1.2.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.core.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.widget.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.mouse.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.draggable.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.position.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.resizable.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.dialog.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.effect.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.effect-blind.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.ui.effect-explode.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
				.append("pages/js/jquery.alerts.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
		.append("pages/js/My97DatePicker/WdatePicker.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"").append(basePath)
		.append("pages/js/common/platform.js\"></script>\n");

		sb.append("<link rel=\"stylesheet\" href=\"").append(basePath)
				.append("pages/css/platform.css\" type=\"text/css\"></link>\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(basePath)
		.append("pages/css/flexigrid/flexigrid.css\" type=\"text/css\"></link>\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(basePath)
				.append("pages/css/base/jquery.ui.all.css\" type=\"text/css\"></link>\n");
		sb.append("<link rel=\"stylesheet\" href=\"").append(basePath)
				.append("pages/css/base/jquery.alerts.css\" type=\"text/css\"></link>\n");

		sb.append("<style type=\"text/css\">");
		sb.append(".flexigrid div.fbutton .add {background: url(").append(basePath)
				.append("pages/css/images/add.png) no-repeat center left; }");
		sb.append(".flexigrid div.fbutton .query {background: url(").append(basePath)
		.append("pages/css/images/query.png) no-repeat center left; }");
		sb.append(".flexigrid div.fbutton .delete { background: url(").append(basePath)
				.append("pages/css/images/close.png) no-repeat center left;}");
		sb.append(" .flexigrid div.fbutton .modify {background: url(").append(basePath)
				.append("pages/css/images/modify.png) no-repeat center left;}");
		sb.append(" .flexigrid div.fbutton .save {background: url(").append(basePath)
		.append("pages/css/images/save.png) no-repeat center left;}");
		sb.append("</style>");

		try
		{
			out.write(sb.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
