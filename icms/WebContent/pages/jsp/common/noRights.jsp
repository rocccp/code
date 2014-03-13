<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table width="100%" height="65%">
	<tr>
		<td valign="middle" align="center"><img
			src="<%=basePath%>pages/icon/error.png" width="256" height="256"></td>
	</tr>
	<tr>
		<td valign="middle" align="center"><FONT face=隶书 color=#ff0000
			size=7><STRONG>对不起，您没有权限访问！</STRONG></td>
	</tr>
</table>
