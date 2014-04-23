$(function() {
	var flexi2Url = "";
	$("#flex1").flexigrid({
		url : 'platform/authAction_queryList',
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '权限名称',
			name : 'authName',
			width : 130,
			sortable : false,
			align : 'center'
		}, {
			display : '权限代码',
			name : 'authCode',
			width : 140,
			sortable : false,
			align : 'center'
		}, {
			display : '所属平台',
			name : 'platformName',
			width : 140,
			sortable : false,
			align : 'center'
		}, {
			display : '修改人员',
			name : 'updateUser',
			width : 130,
			sortable : false,
			align : 'center'
		}, {
			display : '修改时间',
			name : 'updateTime',
			width : 130,
			sortable : false,
			align : 'center'
		}, {
			display : '有效标记',
			name : 'effective',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '路径',
			name : 'url',
			width : 70,
			sortable : false,
			align : 'center',
			hide : true
		}, {
			display : '图片路径',
			name : 'icoUrl',
			width : 70,
			sortable : false,
			align : 'center',
			hide : true
		}, {
			display : '平台编号',
			name : 'platformId',
			width : 70,
			sortable : false,
			align : 'center',
			hide : true
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
			name : '权限路径',
			bclass : 'add',
			onpress : opt
		}, {
			separator : true
		} ],
		usepager : true,
		title : '权限列表',
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
	});

	var opttype = "";

	function opt(com, grid) {
		switch (com) {
		case '增加':
			$('#authCode').removeAttr("disabled");
			$('#platformId').removeAttr("disabled");

			$("#authId").val(0);
			$('#authName').val("");
			$('#authCode').val("");
			$('#url').val("");
			$('#icoUrl').val("");
			$("#authManage").dialog("open");
			$('#effective').val("");
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
			$('#url').val(data[7]);
			$('#icoUrl').val(data[8]);

			$('#platformId').val(data[9]);

			$('#authCode').attr("disabled", true);
			$('#platformId').attr("disabled", true);

			if ("有效" == data[6]) {
				$('#effective').val(1);
			} else {
				$('#effective').val(2);
			}

			$("#authManage").dialog("open");
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
		case '权限路径':
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
			flexi2Url = 'platform/authPathAction_queryList?id=' + data[0];
			$('#authRootId').val(data[0]);
			option.url = flexi2Url;
			$("#flex2").flexigrid(option);
			$("#flex2").flexOptions(option).flexReload();
			$("#authManagePath").dialog("open");
			
			break;

		}
	}
	;

	$("#authManage").dialog(
			{
				autoOpen : false,
				show : "blind",
				//hide : "explode",
				height : 380,
				width : 400,
				modal : true,
				resizable : false,
				buttons : {
					"保存" : function() {
						var id = $("#authId").val();
						var authName = $('#authName').val();
						var authCode = $('#authCode').val();
						var platformId = $('#platformId').val();
						var authurl = $('#url').val();
						var icoUrl = $('#icoUrl').val();
						var effective = $('#effective').val();
						if (authName == "") {
							jAlert('权限名不能为空', '系统提示');
							return;
						}
						if (authCode == "") {
							jAlert('权限代码不能为空', '系统提示');
							return;
						}
						if (authurl == "") {
							jAlert('权限路径不能为空', '系统提示');
							return;
						}
						if (platformId == "") {
							jAlert('所属平台不能为空', '系统提示');
							return;
						}
						if (effective == "") {
							jAlert('有效标记不能为空', '系统提示');
							return;
						}
						var url = "platform/authAction_checkAuth";
						var params = "auth.authCode=" + authCode;

						if (opttype == "add") {
							$.ajax({
								type : "post",
								url : url,
								data : params,
								success : function(text) {
									if ("1" == text) {
										jAlert('权限代码已经使用', '系统提示');
									} else {
										saveOrUpdate(id, authName, authCode,
												effective, platformId, authurl,
												icoUrl);
									}
								},
								error : function() {
									jAlert("系统异常", '系统提示');
								}
							});
						} else if (opttype == "edit") {
							saveOrUpdate(id, authName, authCode, effective,
									platformId, authurl, icoUrl);
						}

					}
				}
			});
	function saveOrUpdate(id, authName, authCode, effective, platformId,
			authurl, icoUrl) {
		var url = "platform/authAction_saveOrUpdate";
		params = "auth.id=" + id + "&auth.authName=" + authName
				+ "&auth.authCode=" + authCode + "&auth.effective.id="
				+ effective + "&auth.parent.id=" + platformId + "&auth.url="
				+ authurl + "&auth.icoUrl=" + icoUrl;
		$.ajax({
			type : "post",
			url : url,
			data : params,
			success : function(text) {
				jAlert("保存成功", '系统提示');
				$('#flex1').flexReload();
				$('#authManage').dialog("close");
			},
			error : function() {
				jAlert("系统异常", '系统提示');
			}
		});
	}
	function del(id) {
		var url = "platform/authAction_delete";
		var params = "auth.id=" + id;
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

	$("#authManagePath").dialog({
		autoOpen : false,
		show : "blind",
		//hide : "explode",
		height : 500,
		width : 540,
		modal : true,
		resizable : false
	});

	$("#authManageAdd").dialog({
		autoOpen : false,
		show : "blind",
		//hide : "explode",
		height : 300,
		width : 400,
		modal : true,
		resizable : false,
		buttons : {
			"保存" : function() {
				var id = $('#authRootId').val();
				var authPath = $('#authPath').val();
				if (authPath == "") {
					jAlert('权限路径不能为空', '系统提示');
					return;
				}
				var params = "authPath.path=" + authPath + "&id=" + id;
				var authPathurl = "platform/authPathAction_save";

				$.ajax({
					type : "post",
					url : authPathurl,
					data : params,
					async:true,
					success : function(text) {
						jAlert("保存成功", '系统提示');
						$("#flex2").flexOptions(option).flexReload();
						$('#authManageAdd').dialog("close");
					},
					error : function() {
						jAlert("系统异常", '系统提示');
					}
				});

			}
		}
	});

	var option = {
		url : "platform/authPathAction_queryList",
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '路径',
			name : 'path',
			width : 400,
			sortable : false,
			align : 'center'
		} ],
		buttons : [ {
			name : '增加',
			bclass : 'add',
			onpress : optF2
		}, {
			separator : true
		}, {
			name : '删除',
			bclass : 'delete',
			onpress : optF2
		}, {
			separator : true
		} ],
		title : '权限路径列表',
		useRp : false,
		usepager: false,
		rp : 100,
		nomsg : '没有符合条件的记录存在',
		rpOptions : [ 100 ], // 可选择设定的每页结果数
		showTableToggleBtn : false,
		autoload : true, // 自动加载，即第一次发起ajax请求
		resizable : false, // table是否可伸缩
		//procmsg : '加载中, 请稍等 ...',
		hideOnSubmit : true, // 是否在回调时显示遮盖
		blockOpacity : 0.5,// 透明度设置
		rowbinddata : true,// 配合上一个操作，如在双击事件中获取该行的数据
		pagestat : '',// 显示当前页和总页面的样式
		radio : true,// 是否要多选框
		width : 500,
		height : 300,
		singleSelect : true
	};

	function optF2(com, grid) {
		switch (com) {
		case '增加':
			$("#authPathId").val(0);
			$('#authPath').val("");
			$("#authManageAdd").dialog("open");
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
					var id = $('#authRootId').val();
					var url = "platform/authPathAction_delete";
					var params = "id=" + id + "&delId=" + ids;
					$.ajax({
						type : "post",
						url : url,
						data : params,
						success : function(text) {
							jAlert("删除成功", '系统提示');
							$('#flex2').flexReload();
						},
						error : function() {
							jAlert("系统异常", '系统提示');
						}
					});
				} else {
					return;
				}

			});
			break;

		}
	}

});