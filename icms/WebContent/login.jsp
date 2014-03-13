<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<link rel="shortcut icon" href="cross.ico">
<head>
<title>login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.txtUserName {
	border: 0px;
	border-bottom: 1px solid #005aa7; /* 下划线 */
	background-color: transparent; /* 背景透明 */
	height: 59;
	padding: 0;
	line-height: 59px;
	vertical-align: middle;
	font-size: 20px;
}

.txtPw {
	border: 0px;
	border-bottom: 1px solid #005aa7; /* 下划线 */
	background-color: transparent; /* 背景透明 */
	height: 50;
	font-size: 20px;
	padding: 0;
	line-height: 50px;
	vertical-align: middle;
}

.txtCode {
	border: 0px;
	border-bottom: 1px solid #005aa7; /* 下划线 */
	background-color: transparent; /* 背景透明 */
	height: 50;
	width: 100;
	padding: 0;
	line-height: 50px;
	font-size: 20px;
}
</style>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="overflow: hidden" onload="init();">
	<form id="loginform" action="loginAction" method="post">
		<table width="1280" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td background="pages/images/login01.jpg" width="213" height="148">&nbsp;</td>
				<td background="pages/images/login02.jpg" width="107" height="148">&nbsp;</td>
				<td background="pages/images/login03.jpg" width="64" height="148">&nbsp;</td>
				<td background="pages/images/login04.jpg" width="256" height="148">&nbsp;</td>
				<td background="pages/images/login05.jpg" width="256" height="148">&nbsp;</td>
				<td background="pages/images/login06.jpg" width="64" height="148">&nbsp;</td>
				<td background="pages/images/login07.jpg" width="107" height="148">&nbsp;</td>
				<td background="pages/images/login08.jpg" width="213" height="148">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login09.jpg" width="213" height="20">&nbsp;</td>
				<td background="pages/images/login10.jpg" width="107" height="20">&nbsp;</td>
				<td background="pages/images/login11.jpg" width="64" height="20">&nbsp;</td>
				<td background="pages/images/login12.jpg" width="256" height="20">&nbsp;</td>
				<td background="pages/images/login13.jpg" width="256" height="20">&nbsp;</td>
				<td background="pages/images/login14.jpg" width="64" height="20">&nbsp;</td>
				<td background="pages/images/login15.jpg" width="107" height="20">&nbsp;</td>
				<td background="pages/images/login16.jpg" width="213" height="20">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login17.jpg" width="213" height="95">&nbsp;</td>
				<td background="pages/images/login18.jpg" width="107" height="95">&nbsp;</td>
				<td background="pages/images/login19.jpg" width="64" height="95">&nbsp;</td>
				<td background="pages/images/login20.jpg" width="256" height="95">&nbsp;</td>
				<td background="pages/images/login21.jpg" width="256" height="95">&nbsp;</td>
				<td background="pages/images/login22.jpg" width="64" height="95">&nbsp;</td>
				<td background="pages/images/login23.jpg" width="107" height="95">&nbsp;</td>
				<td rowspan="2" background="pages/images/login24.jpg" width="213"
					height="158">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login25.jpg" width="213" height="63">&nbsp;</td>
				<td background="pages/images/login26.jpg" width="107" height="63">&nbsp;</td>
				<td background="pages/images/login27.jpg" width="64" height="63">&nbsp;</td>
				<td background="pages/images/login28.jpg" width="256" height="63">&nbsp;</td>
				<td background="pages/images/login29.jpg" width="256" height="63">&nbsp;</td>
				<td background="pages/images/login30.jpg" width="64" height="63">&nbsp;</td>
				<td background="pages/images/login31.jpg" width="107" height="63">&nbsp;</td>
			</tr>
			<tr>
				<td rowspan="2" background="pages/images/login32.jpg" width="213"
					height="119">&nbsp;</td>
				<td rowspan="2" background="pages/images/login33.jpg" width="107"
					height="119">&nbsp;</td>
				<td rowspan="2" background="pages/images/login34.jpg" width="64"
					height="119">&nbsp;</td>
				<td rowspan="2" background="pages/images/login35.jpg" width="256"
					height="119">&nbsp;</td>
				<td background="pages/images/login36.jpg" width="256" height="74">
					<input name="user.account" class="txtUserName" AUTOCOMPLETE="off"
					id="userName" onKeyPress="enterToTab('userName')" />
				</td>
				<td background="pages/images/login37.jpg" width="64" height="74">&nbsp;</td>
				<td background="pages/images/login38.jpg" width="107" height="74">&nbsp;</td>
				<td rowspan="2" background="pages/images/login39.jpg" width="213"
					height="119">&nbsp;</td>
			</tr>
			<tr>
				<td rowspan="2" background="pages/images/login40.jpg" width="256"
					height="69"><input type="password" name="user.password"
					class="txtPw" id="userPassword" onKeyPress="enterToTab('userPassword')" /></td>
				<td background="pages/images/login41.jpg" width="64" height="45">&nbsp;</td>
				<td background="pages/images/login42.jpg" width="107" height="45">&nbsp;</td>
			</tr>
			<tr>
				<td rowspan="5" background="pages/images/login43.jpg" width="213"
					height="277">&nbsp;</td>
				<td rowspan="5" background="pages/images/login44.jpg" width="107"
					height="277">&nbsp;</td>
				<td rowspan="5" background="pages/images/login45.jpg" width="64"
					height="277">&nbsp;</td>
				<td rowspan="2" background="pages/images/login46.jpg" width="256"
					height="83">&nbsp;</td>
				<td background="pages/images/login47.jpg" width="64" height="24">&nbsp;</td>
				<td background="pages/images/login48.jpg" width="107" height="24">&nbsp;</td>
				<td rowspan="5" background="pages/images/login49.jpg" width="213"
					height="277">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login50.jpg" width="256" height="59">
					<input name="checkCode" class="txtCode" id="checkCode"
					AUTOCOMPLETE="off" maxlength="4" onkeypress="checkNumberText();enterToTab('checkCode');"  />
					&nbsp;&nbsp; <img id="code" border=0 width="101" height="40"
					src="pages/jsp/common/image.jsp" onclick="reload();">
				</td>
				<td background="pages/images/login51.jpg" width="64" height="59">&nbsp;</td>
				<td background="pages/images/login52.jpg" width="107" height="59">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login53.jpg" width="256" height="55"
					align="right"><FONT color="red" style="FONT-SIZE: 15pt;"
					id="msg1"></FONT></td>
				<td background="pages/images/login54.jpg" width="256" height="55"
					align="center"><FONT color="red" style="FONT-SIZE: 15pt;"
					id="msg2"></FONT></td>
				<td background="pages/images/login55.jpg" width="64" height="55">&nbsp;</td>
				<td background="pages/images/login56.jpg" width="107" height="55">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><img src="pages/images/login80.jpg" width="512"
					height="87" onMouseOut="this.src = 'pages/images/login80.jpg'"
					onMouseOver="this.src = 'pages/images/login57.jpg'" id="loginBtn"
					onclick="login()"></td>
				<td background="pages/images/login58.jpg" width="64" height="87">&nbsp;</td>
				<td background="pages/images/login59.jpg" width="107" height="87">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login60.jpg" width="256" height="52">&nbsp;</td>
				<td background="pages/images/login61.jpg" width="256" height="52">&nbsp;</td>
				<td background="pages/images/login62.jpg" width="64" height="52">&nbsp;</td>
				<td background="pages/images/login63.jpg" width="107" height="52">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login64.jpg" width="213" height="20">&nbsp;</td>
				<td background="pages/images/login65.jpg" width="107" height="20">&nbsp;</td>
				<td background="pages/images/login66.jpg" width="64" height="20">&nbsp;</td>
				<td background="pages/images/login67.jpg" width="256" height="20">&nbsp;</td>
				<td background="pages/images/login68.jpg" width="256" height="20">&nbsp;</td>
				<td background="pages/images/login69.jpg" width="64" height="20">&nbsp;</td>
				<td background="pages/images/login70.jpg" width="107" height="20">&nbsp;</td>
				<td background="pages/images/login71.jpg" width="213" height="20">&nbsp;</td>
			</tr>
			<tr>
				<td background="pages/images/login72.jpg" width="213" height="148">&nbsp;</td>
				<td background="pages/images/login73.jpg" width="107" height="148">&nbsp;</td>
				<td background="pages/images/login74.jpg" width="64" height="148">&nbsp;</td>
				<td background="pages/images/login75.jpg" width="256" height="148">&nbsp;</td>
				<td background="pages/images/login76.jpg" width="256" height="148">&nbsp;</td>
				<td background="pages/images/login77.jpg" width="64" height="148">&nbsp;</td>
				<td background="pages/images/login78.jpg" width="107" height="148">&nbsp;</td>
				<td background="pages/images/login79.jpg" width="213" height="148">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function reload() {
		document.getElementById("code").setAttribute("src",
				"pages/jsp/common/image.jsp?a=" + new Date().getTime());
	}

	function login() {
		var userName = document.getElementById("userName").value;
		var userPassword = document.getElementById("userPassword").value;
		var checkCode = document.getElementById("checkCode").value;

		if (userName == "") {

			document.getElementById("msg1").innerHTML = "信息 Message:&nbsp;&nbsp;&nbsp; ";
			document.getElementById("msg2").innerHTML = "用户名不能为空 <br> UserName is not allowed null";
			return;
		}
		if (userPassword == "") {
			document.getElementById("msg1").innerHTML = "信息 Message:  ";
			document.getElementById("msg2").innerHTML = "密码不能为空  <br> Password is not allowed null";
			return;
		}
		if (checkCode == "") {
			document.getElementById("msg1").innerHTML = "信息 Message:  ";
			document.getElementById("msg2").innerHTML = "验证码不能为空 <br> checkCode is not allowed null";
			return;
		}
		if (checkCode.length < 4) {
			document.getElementById("msg1").innerHTML = "信息 Message:  ";
			document.getElementById("msg2").innerHTML = "验证码错误 <br> checkCode is wrong";
			return;
		}
		document.getElementById("msg1").innerHTML = " ";
		document.getElementById("msg2").innerHTML = " ";
		document.getElementById("loginBtn").onclick = function() {
		}
		document.getElementById("loginform").submit();

	}
	function checkNumberText() {
		if (!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57)) || (window.event.keyCode == 13))) {
			window.event.keyCode = 0;
		}
	}
	function init()
	{
		var msg = '<%=request.getAttribute("msg")%>';
		if (msg != '' && msg != 'null') {
			document.getElementById("msg1").innerHTML = "信息 Message:  ";
			document.getElementById("msg2").innerHTML = msg;
			return;
		}

	}
	function enterToTab(tp){
		  if(event.srcElement.type != 'button' && event.srcElement.type != 'textarea' && event.keyCode == 13){
		  	if(tp == 'userName'){
		  	  document.getElementById("userPassword").focus();
		  	}else if(tp == 'userPassword'){
		  	  document.getElementById("checkCode").focus();
		  	}else if(tp == 'checkCode'){
		  		login();
			}
		  }
		}

		function enterToSubmit(){
		  if (event.keyCode == 13){
			  login();
		  }
		}
</script>
</html>