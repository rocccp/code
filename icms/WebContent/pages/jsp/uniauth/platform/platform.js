$(function() {
	$("#flex1").flexigrid({
		url : 'platform/platformAction_queryList',
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '平台名称',
			name : 'authName',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '平台代码',
			name : 'authCode',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '录入人员',
			name : 'updateUser',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '录入时间',
			name : 'updateTime',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '有效标记',
			name : 'effective',
			width : 70,
			sortable : false,
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
		}],
		usepager : true,
		title : '平台列表',
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
		sortname: "id",
		sortorder: "asc",
//		checkbox : true,// 是否要多选框   
		singleSelect : true
	});

	var opttype = "";

	function opt(com, grid) {
		switch (com) {
		case '增加':
			$('#authId').val("");
			$('#authName').val("");
			$('#authCode').val("");
			$('#effective').val("");
			$("#platformManage").dialog("open");
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
			$('#authId').val(data[0]);
			$('#authName').val(data[1]);
			$('#authCode').val(data[2]);
			if ("有效" == data[5]) {
				$('#effective').val(1);
			} else {
				$('#effective').val(2);
			}

			$("#platformManage").dialog("open");
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
		case '权限管理':
			var form = $("#authManager");
			form.action = "platform/authIndexAction";
			form.submit();
			break;

		}
	}
	;

	$("#platformManage").dialog(
			{
				autoOpen : false,
				show : "blind",
				//hide : "explode",
				height : 320,
				width : 350,
				modal : true,
				resizable : false,
				buttons : {
					"保存" : function() {
						var id = $("#authId").val();
						var authName = $('#authName').val();
						var authCode = $('#authCode').val();
						var effective = $('#effective').val();
						if (authName == "") {
							jAlert('权限名不能为空', '系统提示');
							return;
						}
						if (authCode == "") {
							jAlert('权限代码不能为空', '系统提示');
							return;
						}
						if (effective == "") {
							jAlert('有效标记不能为空', '系统提示');
							return;
						}
						var url = "platform/platformBaseAction_checkPlatform";
						var params = "platform.authCode=" + authCode;

						if (opttype == "add") {
							$.ajax({
								type : "post",
								url : url,
								data : params,
								success : function(text) {
									if ("1" == text) {
										jAlert('平台代码已经使用', '系统提示');
									} else {
										saveOrUpdate(id, authName, authCode,
												effective);
									}
								},
								error : function() {
									jAlert("系统异常", '系统提示');
								}
							});
						} else if (opttype == "edit") {
							saveOrUpdate(id, authName, authCode, effective);
						}

					}
				}
			});
	function saveOrUpdate(id, authName, authCode, effective) {
		url = "platform/platformBaseAction_saveOrUpdate";
		params = "id=" + id + "&platform.authName=" + authName
				+ "&platform.authCode=" + authCode + "&platform.effective.id="
				+ effective;
		$.ajax({
			type : "post",
			url : url,
			data : params,
			success : function(text) {
				jAlert("保存成功", '系统提示');
				$('#flex1').flexReload();
				$('#platformManage').dialog("close");
			},
			error : function() {
				jAlert("系统异常", '系统提示');
			}
		});
	}
	function del(ids) {
		url = "platform/platformBaseAction_delete";
		params = "ids=" + ids;
		$.ajax({
			type : "post",
			url : url,
			data : params,
			success : function(text) {
				jAlert("删除成功", '系统提示');
				$('#flex1').flexReload();
			},
			error : function() {
				jAlert("系统异常", '系统提示');
			}
		});
	}
});