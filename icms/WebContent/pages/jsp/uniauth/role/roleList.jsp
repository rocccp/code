<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="p" uri="/WEB-INF/taglib/platform.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<p:base href="<%=basePath%>" target="_self" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>role</title>
<script type="text/javascript"
	src="<%=basePath%>pages/jsp/uniauth/role/role.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/xtree.css" type="text/css"></link>
</head>
<body>
	<div id="ptable" style="margin: 10px">
		<table id="flex1" style="display: none"></table>
	</div>
	<div id="roleManage" title="角色管理">
		<input type="hidden" id="id" />
		<fieldset>
			<table>
				<tr>
					<td>角色名称</td>
					<td><input type="text" name="name" id="name"
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>有效标记</td>
					<td><p:CodeSelect id="effective" name="effective"
							codeName="effective" style="width:200px"/></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div id="authManage" style="margin: 10px">
		<iframe id='treeIFrame' style="height:80%;width:100%;MARGIN-top:1px;"
			frameborder="0" scrolling="no"></iframe>
	</div>
</body>