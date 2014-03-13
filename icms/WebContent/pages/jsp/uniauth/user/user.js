$(function() {

	var flexOptions = {
		url : 'platform/userAction_queryList',
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 50,
			sortable : false,
			align : 'center'
		}, {
			display : '姓名',
			name : 'name',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '系统登录名',
			name : 'account',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '部门',
			name : 'org',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '状态',
			name : 'status',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '人员类别',
			name : 'personType',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '修改人员',
			name : 'updateUser',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '修改时间',
			name : 'updateTime',
			width : 100,
			sortable : false,
			align : 'center'
		}, {
			display : '有效标记',
			name : 'effective',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '部门代码',
			name : 'orgId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '电子邮箱',
			name : 'email',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '证件代码',
			name : 'certificateId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '证件类型代码',
			name : 'certificateTypeId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '证件类型',
			name : 'certificateType',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '证件号码',
			name : 'certificateNum',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '生日',
			name : 'birthday',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '电话',
			name : 'phone',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '性别代码',
			name : 'sexId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '性别',
			name : 'sex',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '人员类别代码',
			name : 'personTypeId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		}, {
			display : '状态代码',
			name : 'statusId',
			width : 70,
			sortable : false,
			hide : true,
			align : 'center'
		} ],
		buttons : [ {
			name : '增加',
			bclass : 'add',
			onpress : opt
		}, {
			separator : true
		}, {
			name : '修改',
			bclass : 'modify',
			onpress : opt
		}, {
			separator : true
		}, {
			name : '删除',
			bclass : 'delete',
			onpress : opt
		}, {
			separator : true
		}, {
			name : '角色管理',
			bclass : 'add',
			onpress : opt
		}, {
			separator : true
		}, {
			name : '查询条件',
			bclass : 'query',
			onpress : opt
		}, {
			separator : true
		} ],
		usepager : true,
		title : '用户列表',
		useRp : true,
		rp : 15,
		nomsg : '没有符合条件的记录存在',
		rpOptions : [ 15, 30, 45, 60 ], // 可选择设定的每页结果数
		showTableToggleBtn : false,
		autoload : true, // 自动加载，即第一次发起ajax请求
		resizable : false, // table是否可伸缩
		procmsg : '加载中, 请稍等 ...',
		hideOnSubmit : true, // 是否在回调时显示遮盖
		blockOpacity : 0.5,// 透明度设置
		rowbinddata : true,// 配合上一个操作，如在双击事件中获取该行的数据
		radio : true,// 是否要多选框
		width : 910,
		height : 540,
		singleSelect : true
	};
	$("#flex1").flexigrid(flexOptions);

	var option = {
		url : "platform/userRolesAction_queryList",
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 20,
			sortable : false,
			align : 'center'
		}, {
			display : '角色名称',
			name : 'name',
			width : 400,
			sortable : false,
			align : 'center'
		}, {
			display : 'checked',
			name : 'checked',
			width : 20,
			hide : true,
			sortable : false,
			align : 'center'
		} ],

		buttons : [ {
			name : '保存',
			bclass : 'save',
			onpress : optF2
		}, {
			separator : true
		} ],

		title : '角色列表',
		useRp : false,
		usepager : false,
		rp : 100,
		nomsg : '没有符合条件的记录存在',
		rpOptions : [ 100 ], // 可选择设定的每页结果数
		showTableToggleBtn : false,
		autoload : true, // 自动加载，即第一次发起ajax请求
		resizable : false, // table是否可伸缩
		hideOnSubmit : true, // 是否在回调时显示遮盖
		blockOpacity : 0.5,// 透明度设置
		rowbinddata : true,// 配合上一个操作，如在双击事件中获取该行的数据
		pagestat : '',// 显示当前页和总页面的样式
		radio : true,// 是否要多选框
		width : 500,
		height : 300,
		checkbox : true,// 是否要多选框
		singleSelect : true
	};

	var opttype = "";

	function opt(com, grid) {
		switch (com) {
		case '增加':
			$('#account').removeAttr("disabled");

			obj = $("#userManage *[platformInput=true]");
			initAdd(obj);
			$("#userManage").dialog("open");
			opttype = "add";
			break;
		case '修改':
			var selected_count = $('.trSelected', grid).length;
			if (selected_count == 0) {
				jAlert("请选择一条记录", '系统提示');
				return;
			}
			if (selected_count > 1) {
				jAlert("抱歉只能同时修改一条记录", '系统提示');
				return;
			}
			data = new Array();
			$('.trSelected td', grid).each(function(i) {
				data[i] = $(this).children('div').text();
			});

			$("#id").val(data[0]);
			$('#name').val(data[1]);
			$('#account').val(data[2]);
			$('#email').val(data[10]);
			$('#birthday').val(data[15]);
			if (trim(data[11]) == "") {

				$("#certificateId").val("");
				$('#certificateType').val("");
				$('#certificateNum').val("");
			} else {
				$("#certificateId").val(data[11]);
				$('#certificateType').val(data[12]);
				$('#certificateNum').val(data[14]);
			}
			$('#sex').val(data[17]);
			$('#phone').val(data[16]);
			$('#org').val(data[9]);
			$('#personType').val(data[19]);
			$('#status').val(data[20]);
			if ("有效" == data[8]) {
				$('#effective').val(1);
			} else {
				$('#effective').val(2);
			}

			$('#account').attr("disabled", true);

			$("#userManage").dialog("open");
			opttype = "edit";
			break;
		case '删除':
			selected_count = $('.trSelected', grid).length;
			if (selected_count == 0) {
				jAlert("请选择一条记录", '系统提示');
				return;
			}
			names = '';
			$('.trSelected td:nth-child(2) div', grid).each(function(i) {
				if (i)
					names += ',';
				names += $(this).text();
			});
			ids = '';
			$('.trSelected td:nth-child(1) div', grid).each(function(i) {
				if (i)
					ids += ',';
				ids += $(this).text();
			});
			jConfirm('确定删除' + names + '吗?', '系统提示', function(r) {
				if (r == true) {
					del(ids);
				} else {
					return;
				}

			});
			break;
		case '角色管理':
			var selected_count = $('.trSelected', grid).length;
			if (selected_count == 0) {
				jAlert("请选择一条记录", '系统提示');
				return;
			}
			if (selected_count > 1) {
				jAlert("抱歉只能同时修改一条记录", '系统提示');
				return;
			}
			data = new Array();
			$('.trSelected td', grid).each(function(i) {
				data[i] = $(this).children('div').text();
			});
			flexi2Url = 'platform/userRolesAction_queryList?id=' + data[0];
			$('#userId').val(data[0]);
			option.url = flexi2Url;
			$("#flex2").flexigrid(option);
			$("#flex2").flexOptions(option).flexReload();
			$("#authManage").dialog("open");
			break;
		case '查询条件':
			$("#queryTable").dialog("open");
			break;

		}
	}

	$("#userManage").dialog({
		autoOpen : false,
		show : "blind",
		height : 350,
		width : 700,
		modal : true,
		resizable : false,
		buttons : {
			"保存" : function() {

				var obj = $("#userManage *[mustInput=true] ");
				var checkObj = checkNull(obj);
				if (checkObj != null) {
					jAlert(checkObj.title + "不能为空", '系统提示');
					return;
				}
				var email = $('#email').val();
				if (!isValidEmailAddress(email)) {
					jAlert("邮件格式错误", '系统提示');
					return;
				}
				var certificateType = $('#certificateType').val();
				var certificateNum = $('#certificateNum').val();

				if (trim(certificateType) != "") {
					if (certificateNum == "") {
						jAlert("证件号码不能为空", '系统提示');
						return;
					}
				}

				obj = $("#userManage *[platformInput=true]");
				var appendURLStr = "";
				appendURLStr = appendUrl(obj, appendURLStr);

				var account = $('#account').val();
				var url = "platform/userAction_checkUser";
				var params = "user.account=" + account;

				if (opttype == "add") {
					$.ajax({
						type : "post",
						url : url,
						data : params,
						success : function(text) {
							if ("true" == text) {
								jAlert('用户代码已经使用', '系统提示');
							} else {
								url = "platform/userAction_save";
								saveOrUpdate(url, appendURLStr);
							}
						},
						error : function() {
							jAlert("系统异常", '系统提示');
						}
					});
				} else if (opttype == "edit") {
					url = "platform/userAction_save";
					saveOrUpdate(url, appendURLStr);
				}
			}
		}
	});

	function saveOrUpdate(url, appendURLStr) {
		$.ajax({
			type : "post",
			url : url,
			data : appendURLStr,
			success : function(text) {
				jAlert("保存成功", '系统提示');
				$('#flex1').flexReload();
				$('#userManage').dialog("close");
			},
			error : function() {
				jAlert("系统异常", '系统提示');
			}
		});
	}

	$("#queryTable").dialog(
			{
				autoOpen : false,
				show : "blind",
				height : 300,
				width : 400,
				modal : true,
				resizable : false,
				buttons : {
					"查询" : function() {
						var parms = "";
						var q_name = $('#q_name').val();
						var q_account = $('#q_account').val();
						var q_org = $('#q_org').val();
						var q_personType = $('#q_personType').val();
						if (q_name != "") {
							parms = parms + "{'name':'name','value':'" + q_name
									+ "',qType:'like','vType':'String'}@@";
						}
						if (q_org != "") {
							parms = parms + "{'name':'org','value':'" + q_org
									+ "','qType':'=','vType':'int'}@@";
						}

						if (q_account != "") {
							parms = parms + "{'name':'account','value':'"
									+ q_account
									+ "',qType:'like',vType:'String'}@@";
						}

						if (q_personType != "") {
							parms = parms + "{'name':'personType','value':'"
									+ q_personType
									+ "','qType':'=','vType':'int'}";
						}
						$("#queryTable").dialog("close");
						$('#queryFilter').val(parms);
						$("#flex1").flexOptions(flexOptions).flexReload();
					}
				}
			});
	function del(id) {
		var url = "platform/userAction_delete";
		var params = "user.id=" + id;
		$.ajax({
			type : "post",
			url : url,
			data : params,
			success : function(text) {
				jAlert("删除成功", '系统提示');
				$("#flex1").flexOptions(flexOptions).flexReload();
			},
			error : function() {
				jAlert("系统异常", '系统提示');
			}
		});
	}

	$("#authManage").dialog({
		autoOpen : false,
		show : "blind",
		height : 500,
		width : 540,
		modal : true,
		resizable : false
	});

	function optF2(com, grid) {
		switch (com) {
		case '保存':
			ids = '';
			$('.trSelected td:nth-child(2) div', grid).each(function(i) {
				if (i)
					ids += ',';
				ids += $(this).text();
			});
			var userId = $('#userId').val();
			$.ajax({
				type : "post",
				url : "platform/userRolesAction_save",
				data : "msg=" + ids + "&id=" + userId,
				success : function(text) {
					jAlert("保存成功", '系统提示');
					$("#authManage").dialog("close");
				},
				error : function() {
					jAlert("系统异常", '系统提示');
				}
			});

			break;

		}
	}
});