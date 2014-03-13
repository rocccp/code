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
<title>auth</title>
<script type="text/javascript"
	src="<%=basePath%>pages/jsp/uniauth/auth/auth.js"></script>
</head>
<body>
	<div id="ptable" style="margin: 10px">
		<table id="flex1" style="display: none"></table>
	</div>
	<div id="authManage" title="权限管理">
		<input type="hidden" id="authId" />
		<fieldset>
			<table>
				<tr>
					<td>权限名称</td>
					<td><input type="text" name="authName" id="authName"
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>平台代码</td>
					<td><input type="text" name="authCode" id="authCode" value=""
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>所属平台</td>
					<td><p:CodeSelect id="platformId" name="platformId"
							codeName="platformCode" nullFilter="parent" style="width:200px"/></td>
				</tr>
				<tr>
					<td>权限路径</td>
					<td><input type="text" name="url" id="url" value=""
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>图片路径</td>
					<td><input type="text" name="icoUrl" id="icoUrl" value=""
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
	<div id="authManagePath" title="权限路径管理">
	    <table id="flex2" style="display: none"></table>
	</div>
	<div id="authManageAdd" title="权限路径管理">
		<input type="hidden" id="authPathId" />
		<input type="hidden" id="authRootId" />
		<fieldset>
			<table>
				<tr>
					<td>权限路径</td>
				</tr>
				<tr>
					<td><input type="text" name="authPath" id="authPath" value=""
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<form action="" id="authManagerForm"></form>
</body>