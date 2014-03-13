
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
<title>user</title>
<script type="text/javascript"
	src="<%=basePath%>pages/jsp/uniauth/user/user.js"></script>
</head>
<body>
	<div id="ptable" style="margin: 10px">
		<table id="flex1" style="display: none"></table>
	</div>
	<div id="authManage" style="margin: 10px">
	    <p:input type="hidden" id="userId" name="user.id"/>
		<table id="flex2" style="display: none"></table>
	</div>
	<div id="queryTable" title="查询">
		<input type="hidden" id="queryFilter" />
		<fieldset>
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="q_name" id="q_name"
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>系统登录名</td>
					<td><input type="text" name="q_account" id="q_account"
						value="" class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>部门</td>
					<td><p:CodeSelect id="q_org" name="q_org" codeName="org"
							style="width:200px" type="effective" /></td>
				</tr>
				<tr>
					<td>人员类型</td>
					<td><p:CodeSelect id="q_personType" name="q_personType"
							style="width:200px" codeName="personType" type="effective" /></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div id="userManage" title="用户管理">
		<p:input type="hidden" id="id" name="user.id"/>
		<p:input type="hidden" id="certificateId" name="user.certificate.id"/>
		<fieldset>
			<table>
				<tr>
					<td>用户名 <p:mustInput /></td>
					<td><p:input type="text" name="user.name" id="name"
							clazz="text ui-widget-content ui-corner-all" mustInput="true" title="用户名"/></td>
					<td>用户代码 <p:mustInput /></td>
					<td><p:input type="text" name="user.account" id="account" value=""
							clazz="text ui-widget-content ui-corner-all" mustInput="true" title="用户代码"/></td>
				</tr>
				<tr>
					<td>电子邮箱 <p:mustInput /></td>
					<td><p:input type="text" name="user.email" id="email"
							clazz="text ui-widget-content ui-corner-all" mustInput="true" title="电子邮箱"/></td>
					<td>出生日期</td>
					<td><p:input type="text" name="user.birthday" id="birthday"
							value="" clazz="text ui-widget-content ui-corner-all"
							onClick="WdatePicker({isShowWeek:true,minDate:'1900-01-01',maxDate:'%y-%M-{%d}'})" title="出生日期"/></td>
				</tr>
				<tr>
					<td>证件类型</td>
					<td><p:CodeSelect id="certificateType" name="user.certificate.type.id"
							codeName="certificateType" style="width:200px" type="effective" title="证件类型"/></td>
					<td>证件号码</td>
					<td><p:input type="text" name="user.certificate.num"
							id="certificateNum" value=""
							clazz="text ui-widget-content ui-corner-all" title="证件号码"/></td>
				</tr>

				<tr>
					<td>性别 <p:mustInput /></td>
					<td><p:CodeSelect id="sex" name="user.sex.id" codeName="sex"
							style="width:200px" type="effective" mustInput="true" title="性别"/></td>
					<td>电话</td>
					<td><p:input type="text" name="user.phone" id="phone"
							clazz="text ui-widget-content ui-corner-all" title="电话"/></td>

				</tr>
				<tr>
					<td>部门 <p:mustInput /></td>
					<td><p:CodeSelect id="org" name="user.org.id" codeName="org"
							style="width:200px" type="effective" mustInput="true" title="部门"/></td>
					<td>人员类型 <p:mustInput /></td>
					<td><p:CodeSelect id="personType" name="user.personType.id"
							style="width:200px" codeName="personType" type="effective"
							mustInput="true" title="人员类型 "/></td>
				</tr>
				<tr>
					<td>状态 <p:mustInput /></td>
					<td><p:CodeSelect id="status" name="user.status.id"
							codeName="personStatus" style="width:200px" type="effective"
							mustInput="true" title="状态"/></td>
					<td>有效标记 <p:mustInput /></td>
					<td><p:CodeSelect id="effective" name="user.effective.id"
							codeName="effective" style="width:200px" type="effective"
							mustInput="true" title="有效标记"/></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<form action="" id="userManagerForm"></form>

</body>