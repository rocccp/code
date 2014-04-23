$(function() {
	$("#wsManage").dialog(
			{
				autoOpen : false,
				show : "blind",
				height : 320,
				width : 350,
				modal : true,
				resizable : false,
				buttons : {
					"保存" : function() {
						var id = $("#id").val();
						if (id == "") {
							$("#id").val(0);
							id = $("#id").val();
						}

						var name = $('#name').val();
						var ip = $('#ip').val();
						var content = $('#content').val();
						var note = $('#note').val();
						var effective = $('#effective').val();
						if (name == "") {
							jAlert('名称不能为空', '系统提示');
							return;
						}
						if (ip != "" ) {
							if(!checkIP(ip))
							{
								jAlert('IP格式错误', '系统提示');
								return;
							}
						}
						if (effective == "") {
							jAlert('有效标记不能为空', '系统提示');
							return;
						}
						
						
						var data = "webSet.id=" + id + "&webSet.name=" + name+ "&webSet.ip=" + ip+ "&webSet.content=" + content+ "&webSet.note=" + note
						+ "&webSet.effective.id=" + effective;
						
						$.ajax({
							type : "POST",
							url : "wsm/wsmAction_save",
							data : data,
							success : function(text) {
								jAlert("保存成功", '系统提示');
								$('#flex1').flexReload();
								$('#wsManage').dialog("close");
							},
							error : function() {
								jAlert("系统异常", '系统提示');
							}
						});

					}
				}
			});
	
	
	
	
	$("#flex1").flexigrid({
		url : 'wsm/wsmAction_queryList',
		dataType : 'json',
		colModel : [ {
			display : '编号',
			name : 'id',
			width : 70,
			sortable : false,
			align : 'center'
		}, {
			display : '网站名称',
			name : 'name',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : 'IP地址',
			name : 'ip',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '路径',
			name : 'content',
			width : 160,
			sortable : false,
			align : 'center'
		}, {
			display : '说明',
			name : 'note',
			width : 160,
			sortable : false,
			align : 'center'
		},{
			display : '修改人员',
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
		} ],
		usepager : true,
		title : '网站列表',
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
		sortname : "id",
		sortorder : "asc",
		// checkbox : true,// 是否要多选框
		singleSelect : true
	});

	function opt(com, grid) {
		switch (com) {
		case '增加':
			$('#id').val("");
			$('#name').val("");
			$('#ip').val("");
			$('#content').val("");
			$('#note').val("");
			$('#effective').val("");
			$("#wsManage").dialog("open");
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
			$('#id').val(data[0]);
			$('#name').val(data[1]);
			$('#ip').val(data[2]);
			$('#content').val(data[3]);
			$('#note').val(data[4]);
			if ("有效" == data[7]) {
				$('#effective').val(1);
			} else {
				$('#effective').val(2);
			}
			$("#wsManage").dialog("open");
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
				{
					names += ',';
				}
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
		}
	}
	;

	
	function del(ids) {
		url = "wsm/wsmAction_delete";
		params = "webSet.id=" + ids;
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