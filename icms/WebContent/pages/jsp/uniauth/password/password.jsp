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
<title>platform</title>
<script type="text/javascript"
	src="<%=basePath%>pages/jsp/uniauth/password/password.js"></script>
<style type="text/css">

input.correct {
	border: 1px solid green;
}

input.error {
	border: 1px solid red;
}

#tips {
	float: left;
	margin: 2px 0 0 20px;
}

#tips span {
	float: left;
	width: 50px;
	height: 20px;
	color: #fff;
	overflow: hidden;
	background: #ccc;
	margin-right: 2px;
	line-height: 20px;
	text-align: center;
}

#tips.s1 .active {
	background: #f30;
}

#tips.s2 .active {
	background: #fc0;
}

#tips.s3 .active {
	background: #cc0;
}

#tips.s4 .active {
	background: #090;
}
</style>
</head>
<body>
	<fieldset>
		<table>
			<tr>
				<td>原密码 <p:mustInput /></td>
				<td><p:input type="password" name="oldPassword"
						id="oldPassword" clazz="text ui-widget-content ui-corner-all"
						mustInput="true" title="原密码" /></td>
			</tr>
			<tr>
				<td>新密码 <p:mustInput /></td>
				<td><p:input type="password" name="newPassword"
						id="newPassword" clazz="text ui-widget-content ui-corner-all"
						mustInput="true" title="新密码" /></td>
			</tr>
			<tr>
				<td colspan="2"><div id="tips">
						<span></span><span></span><span></span><span></span>
					</div></td>

			</tr>
			<tr>
				<td>确认密码</td>
				<td><p:input type="password" name="conformPassword"
						id="conformPassword" clazz="text ui-widget-content ui-corner-all"
						mustInput="true" title="确认密码" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input id="saveBtn" value="确定" 
					style="width: 70px" /></td>
			</tr>

		</table>
	</fieldset>
</body>