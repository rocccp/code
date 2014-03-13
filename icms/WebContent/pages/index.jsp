<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.platform.entity.uniauth.user.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    User user = (User)request.getSession().getAttribute("PLATFORM_USER");
    String lrBarIconData = (String)request.getAttribute("lrBarIconData");
    String deskIconData = (String)request.getAttribute("deskIconData");
    String navBar = (String)request.getAttribute("navBar");
    String desktopInnerPanel = (String)request.getAttribute("desktopInnerPanel");
    String default_app = (String)request.getAttribute("default_app");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>desktop</title>
<script type="text/javascript" src="<%=basePath%>pages/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/myLib.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/jquery.winResize.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/jquery-smartMenu/js/mini/jquery-smartMenu-min.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/desktop.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/js/common/lock.js"></script>
<link href="<%=basePath%>pages/themes/default/css/desktop.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>pages/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>pages/js/jquery-smartMenu/css/smartMenu.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
$(function(){
		   myLib.progressBar();
		   });
$(window).load(function(){
		   myLib.stopProgress();
		   
		   //这里本应从数据库读取json数据，这里直接将数据写在页面上
		   var lrBarIconData=<%=lrBarIconData%>;
		//这里本应从数据库读取json数据，这里直接将数据写在页面上			   
		  var deskIconData=<%=deskIconData%>;			   
 		   
		  //存储桌面布局元素的jquery对象
		   myLib.desktop.desktopPanel();
 		   
		   //初始化桌面背景
		   myLib.desktop.wallpaper.init("<%=basePath%>pages/themes/default/images/blue_glow.jpg");
		   
		   //初始化任务栏
		   myLib.desktop.taskBar.init();
		   
		   //初始化桌面图标
		   myLib.desktop.deskIcon.init(deskIconData);
		   
		   //初始化桌面导航栏
		   myLib.desktop.navBar.init();
		   
		   //初始化侧边栏
		   myLib.desktop.lrBar.init(lrBarIconData);
		   
		  });	

//添加应用函数
function addIcon(data){
	 myLib.desktop.deskIcon.addIcon(data);
	}
function logout()
{
	document.getElementById("mainForm").action = "logoutAction";
	document.getElementById("mainForm").submit();
}
function showDeskTop()
{
	var myData=myLib.desktop.getMydata();
	alert(myData);
}
</script>
</head>
<body>
<form action="" id="mainForm" method="post">
<div id="wallpapers"></div>
<div id="navBar"><%=navBar %></div>
<div id="time" align="right"><font size="5" id="datetime" color="white"><span ></span></font></div>
<div id="desktopPanel">
<div id="desktopInnerPanel">
   <%=desktopInnerPanel%>

</div>
</div>

<div id="taskBarWrap">
<div id="taskBar">
  <div id="leftBtn"><a href="#" class="upBtn"></a></div>
  <div id="rightBtn"><a href="#" class="downBtn"></a> </div>
  <div id="task_lb_wrap"><div id="task_lb"></div></div>
</div>
</div>

<div id="lr_bar">
  <ul id="default_app">
     <%=default_app %>
  </ul>
  <div id="default_tools"> <span id="showZm_btn" title="全屏"></span><span id="weather_btn" title="显示桌面"></span></div>
  <div id="start_block">
<a title="开始" id="start_btn"></a>
<div id="start_item">
      <ul class="item admin">
        <li><span class="adminImg"></span> <%=user.getName() %></li>
      </ul>
      <ul class="item">
        <li><span class="about_btn"></span>关于</li>
        <li onclick="logout();"><span class="logout_btn"></span>退出系统</li>
      </ul>
    </div>
</div>
</div>
</form>
</body>
<script type="text/javascript">
setTime();
</script>
</html>
