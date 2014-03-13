<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="p" uri="/WEB-INF/taglib/platform.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String auth = (String) request.getAttribute("msg");
	String authId = (String) request.getAttribute("authId");
%>
<p:base href="<%=basePath%>" target="_self" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>role</title>
<script type="text/javascript"
	src="<%=basePath%>pages/js/authorityTreeJs/xtree.js"></script>
<script type="text/javascript"
	src="<%=basePath%>pages/js/authorityTreeJs/map.js"></script>
<script type="text/javascript"
	src="<%=basePath%>pages/js/authorityTreeJs/checkboxTreeItem.js"></script>
<link rel="stylesheet" href="<%=basePath%>pages/css/xtree.css"
	type="text/css"></link>
<script>
	function save() {
		var roleId = $("#roleId").val();
		var objs = getCheckObjects(false);
		var i = 0;
		var str = "[";
		for (i; i < objs.length; i++) {
			str += "{id: " + objs[i].value + " }";
			if (i < objs.length - 1) {
				str += ",";
			}

		}
		str += "]";
		var flag = 0;
		$.ajax({
			type : "post",
			url : "platform/roleBaseAction_saveAuth",
			async:false,
			data : "id=" + roleId + "&authStr=" + str,
			success : function(text) {
				flag = 1;
				jAlert("保存成功", '系统提示');
			},
			error : function() {
				flag = 2;
				jAlert("系统异常", '系统提示');
			}
		});
		return flag ;
	}
</script>
</head>
<body>
	<form action="" id="roleManagerForm">
		<input type='hidden' name="roleId" id="roleId" value="<%=authId%>" />
	</form>
	<%=auth%>
</body>