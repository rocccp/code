<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="p" uri="/WEB-INF/taglib/platform.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String data = (String) request.getAttribute("msg");
%>
<p:base href="<%=basePath%>" target="_self" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>org</title>
<link rel="stylesheet"
	href="<%=basePath%>pages/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=basePath%>pages/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript"
	src="<%=basePath%>pages/js/jquery.ztree.excheck-3.4.js"></script>
<script type="text/javascript"
	src="<%=basePath%>pages/js/jquery.ztree.exedit-3.4.js"></script>
<script type="text/javascript">

    var TreeNodeTemp ;
    
	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			//fontCss: getFont,
			showRemoveBtn: false,
			selectedMulti : false
		},
		edit : {
			enable : true,
			editNameSelectAll : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeDrag : beforeDrag,
			beforeEditName : beforeEditName,
			beforeRemove : beforeRemove,
			//beforeRename : beforeRename,
			beforeDrop: beforeDrop
		}
	};

	var zNodes =<%=data%>	;
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		var pId ;
		   if(moveType == 'inner')
		   {
		      pId =  targetNode.id ;
		   }else
		   {
		      if(targetNode.pId == null)
			  {
			      pId  = 0;
			    
			  }else
			  {
			       pId  = targetNode.pId;
			  }
		   }
		   
		var flag = false;
		if(targetNode ? targetNode.drop !== false : true)
		{
			var url = "platform/orgBaseAction_move";
			var params = "org.id=" + treeNodes[0].id +"&org.parent.id="+pId;
			$.ajax({
				type : "post",
				url : url,
				data : params,
				async : false,
				success : function(text) {
					flag = true;
					jAlert("修改成功", '系统提示');
				},
				error : function() {
					flag = true;
					jAlert("系统异常", '系统提示');
				}
			});
		}
		return flag;
	}
	/*function getFont(treeId, node) {
		return node.font ? node.font : {};
	}*/

	function beforeEditName(treeId, treeNode) {
		$('#id').val(treeNode.id);
		$('#name').val( treeNode.name);
		$('#effective').val( treeNode.eff);
		$('#type').val( treeNode.orgType);
		$('#orgManage').dialog("open");
		TreeNodeTemp = treeNode;
		return false;
	}
	
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "" : "dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		var flag = false;
		
		if(confirm("确认删除 部门 -- " + treeNode.name + " 及其子部门吗？人员部门同时会删除，请删除后调整人员部门。"))
		{
			var url = "platform/orgBaseAction_delete";
			var params = "org.id=" + treeNode.id ;
			$.ajax({
				type : "post",
				url : url,
				data : params,
				async : false,
				success : function(text) {
					flag = true;
					jAlert("删除成功", '系统提示');
				},
				error : function() {
					flag = true;
					jAlert("系统异常", '系统提示');
				}
			});
		}
		return flag;
	}
	function beforeRename(treeId, treeNode, newName) {
		className = (className === "dark" ? "" : "dark");
		if (newName.length == 0) {
			jAlert("部门不能为空.", '系统提示');
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function() {
				zTree.editName(treeNode);
			}, 10);
			return false;
		}
		return true;
	}

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.id).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='增加' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		
		if(treeNode.eff==2)
		{
		   return ;
		}
		var btn = $("#addBtn_" + treeNode.id);

		var nodeId = 0;
		if (btn)
			btn.bind("click", function() {
				$.ajax({
					type : "post",
					url : "platform/orgBaseAction_add",
					data : "org.parent.id=" + treeNode.id,
					async : false,
					success : function(data) {
						nodeId = data;
					},
					error : function() {
						jAlert("系统异常", '系统提示');
						return;
					}
				});
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var addNode = {
						id : nodeId,
						pId : treeNode.id,
						name : "新增部门"
					};
				zTree.addNodes(treeNode, addNode);
				$('#pId').val(treeNode.id);
				$('#id').val(nodeId);
				$('#name').val("");
				$('#effective').val("");
				$('#type').val("");
				TreeNodeTemp = addNode;
				
				$('#orgManage').dialog("open");
			});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.id).unbind().remove();
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
		$("#orgManage").dialog(
				{
					autoOpen : false,
					show : "blind",
					height : 260,
					width : 350,
					modal : true,
					resizable : false,
					buttons : {
						"保存" : function() {
							var id = $('#id').val();
							var name = $('#name').val();
							var effective = $('#effective').val();
							var orgType = $('#type').val();
							
							if (name == "") {
								jAlert('机构名称不能为空', '系统提示');
								return;
							}
							if (effective == "") {
								jAlert('有效标记不能为空', '系统提示');
								return;
							}
							if (orgType == "") {
								jAlert('部门类型不能为空', '系统提示');
								return;
							}
							var url = "platform/orgBaseAction_update";
							var params = "org.id=" + id + "&org.name="
									+ name + "&org.type.id=" + orgType + "&org.effective.id="
									+ effective;
							$.ajax({
								type : "post",
								url : url,
								data : params,
								success : function(text) {
									
									var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
									var node = treeObj.getNodeByParam("id", TreeNodeTemp.id, null);
									node.eff = text;
									node.name = name;
									node.orgType = orgType;
									treeObj.updateNode(node);

									jAlert("保存成功", '系统提示');
									$('#orgManage').dialog("close");
								},
								error : function() {
									jAlert("系统异常", '系统提示');
								}
							});

						}
					}
				});
	});
</script>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>

<body>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>

	<div id="orgManage" title="机构管理">
		<input type="hidden" id="pId" /> 
		<input type="hidden" id="id" />
		<fieldset>
			<table>
				<tr>
					<td>机构名称</td>
					<td><input type="text" name="name" id="name"
						class="text ui-widget-content ui-corner-all" /></td>
				</tr>
				<tr>
					<td>有效标记</td>
					<td><p:CodeSelect id="effective" name="effective"
							codeName="effective" style="width:200px"/></td>
				</tr>
				<tr>
					<td>部门类型</td>
					<td><p:CodeSelect id="type" name="type"
							codeName="orgType" style="width:200px" /></td>
				</tr>
			</table>
		</fieldset>
	</div>
</body>