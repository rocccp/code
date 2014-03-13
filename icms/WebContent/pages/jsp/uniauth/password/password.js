$(function() {
	
	
	$( "#saveBtn" )
	.button()
	.click(function( event ) {
		var flag = false;
		var password = $("#oldPassword").val();
		$.ajax({
			type : "post",
			url : "platform/passwordAction_checkPW",
			data : "password="+password,
			async:false,
			success : function(text) {
				if ("1" == text) {
					flag = true;
				}else
				{
					flag = false;	
				}
			},
			error : function() {
				flag = false;
			}
		});
		if(flag)
		{
			var newPassword = $("#newPassword").val();
			var conformPassword = $("#conformPassword").val();
			
			if(newPassword != conformPassword)
			{
				jAlert('两次密码不一致', '系统提示');
				return ;
			}
			
			$.ajax({
				type : "post",
				url : "platform/passwordAction_save",
				data : "password="+newPassword,
				success : function(text) {
						jAlert('修改成功', '系统提示');
				},
				error : function() {
					jAlert('修改失败', '系统提示');
				}
			});
		}else
		{
			var oldPassword = $("#oldPassword");
			if(oldPassword == "")
			{
				jAlert('原密码不能为空', '系统提示');
			}else
			{
				jAlert('原密码错误', '系统提示');
			}
			
		}
		
		
	});
	 var oTips = document.getElementById("tips");
	 var oInput = document.getElementById("newPassword");
	 var aSpan = oTips.getElementsByTagName("span");
	 var aStr = ["弱", "中", "强", "非常好"];
	 var i = 0; 
	 
	 oInput.onkeyup = oInput.onfocus = oInput.onblur = function ()
	 {
	  var index = checkStrong(this.value);
	  this.className = index ? "correct" : "error";
	  oTips.className = "s" + index;
	  for (i = 0; i < aSpan.length; i++)
	  {
		  aSpan[i].className = aSpan[i].innerHTML = "";
	  }
	  index && (aSpan[index - 1].className = "active", aSpan[index - 1].innerHTML = aStr[index - 1]);
	 };
	
	/**
	 * 强度规则
	 * +-------------------------------------------------------------------+ 1)
	 * 任何少于6个字符的组合，弱；任何字符数的同类字符组合，弱； 2) 任何字符数的两类字符组合，中； 3) 12位字符数以下的三类或四类字符组合，强；
	 * 4) 12位字符数以上的三类或四类字符组合，非常好。
	 * +-------------------------------------------------------------------+
	 */
	// 检测密码强度
	function checkStrong(sValue) {
		var modes = 0;
		if (sValue.length < 1)
			return modes;
		if (/\d/.test(sValue))
			modes++; // 数字
		if (/[a-z]/.test(sValue))
			modes++; // 小写
		if (/[A-Z]/.test(sValue))
			modes++; // 大写
		if (/\W/.test(sValue))
			modes++; // 特殊字符
		switch (modes) {
		case 1:
			return 1;
			break;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return sValue.length < 8 ? 3 : 4;
			break;
		}
	}
	
	function checkPW()
	{
		
	}

});