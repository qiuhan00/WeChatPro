<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/jsp/globalVariable.jsp"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="/spring.tld"%>
<%@ taglib prefix="form" uri="/spring-form.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>login</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no" />
</head>
<body>
	<div>
		<form id="searchForm">
			<p>
				<b>用户名：</b> <input type="text" name="userName" class="easyui-validatebox" /> 
				<b>创建时间：</b> <input id="startDate" name="startDate" type="text" class="easyui-datebox" style="width: 100px;"
					data-options="formatter:myformatter,parser:myparser" /> 
					至 <input id="endDate" name="endDate" type="text" class="easyui-datebox" style="width: 100px;"
					data-options="formatter:myformatter,parser:myparser" />
				<a href="javascript:void(0)" id="search" class="easyui-linkbutton" 
				data-options="iconCls:'icon-search'">查询</a>
			</p>
		</form>
	</div>
	<div>
		<table id="tt">
		</table>
	</div>
	<script src="${HOME }/js/user/common.js"></script>
	<script type="text/javascript">
		var path = "${path}";
		var isFirst = true;
		var editIndex = undefined; //当前编辑的行
		$(function() {
			init();
			$("#search").click(function() {
				search();
			});
		});
		
		function search(){
			$('#tt').datagrid('load', getJson4Form("searchForm"));  
		}
		
		function init(){
		  $('#tt').datagrid({
				url : path+'/login/find',//"${path }/login/find"
				title : "用户信息列表",
				pagination : true,
				pageSize : 20,
				pageList : [ 50, 40, 30, 20, 10 ],
				//showFooter : false,
				singleSelect:true,
				rownumbers:true,
				fitColumns:true,
				idField:'id',//主键
				columns : [ [ {
					field : 'userName',
					title : '用户名',
					align : 'center',
					width : 50,
					editor : {
						type : 'validatebox',
						options : {
							required : 'true',
							validType : 'length[1,100]'
						}
					}
				},{
					field : 'openId',
					title : 'openId',
					align : 'center',
					width : 50,
					editor : {
						type : 'validatebox',
						options : {
							required : 'true',
							validType : 'length[1,100]'
						}
					}
				},{
					field : 'createTime',
					title : '创建时间',
					align : 'center',
					width : 60
				}, {
					field : 'id',
					title : '操作',
					width : 160,
					align : 'center',
					formatter : function(value, row, index) {
					 	var id = row.id;
						var status = row.status;
						var html = "";
						if (id != undefined && status == 'E') {
							html += "<a href='javascript:void(0)' onclick='del(" + id + ");'>禁用</a>&nbsp;";
						}else if (id != undefined && status == 'U'){
							html += "<a href='javascript:void(0)' onclick='del(" + id + ");'>启用</a>&nbsp;";
						}
						return html;
					}
				}]],toolbar:[{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						if(editIndex != undefined){ //添加时先判断是否有开启编辑的行，如果有则把开启编辑的那行结束编辑
							$('#tt').datagrid('endEdit', editIndex);
						}
						if(editIndex == undefined){
							var field = {
								type : 'T'
							};
							$('#tt').datagrid('appendRow', field);
							editIndex = $('#tt').datagrid('getRows').length - 1;
							$('#tt').datagrid('selectRow', editIndex);
							$('#tt').datagrid('beginEdit', editIndex);
							focusEditor('tt', field, editIndex);
						}
					}
				}, '-', '-', {
					text : '保存',
					iconCls : 'icon-save',
					handler : function() {
						accept();
					}
				}, '-' ]
			});
		};
		
		function del(id) {
			if (id == 0) { //點擊工具按鈕刪除
				var row = $('#tt').datagrid('getSelected');
				if (row) {
					var index = $('#tt').datagrid('getRowIndex', row);
					var nextSelect = index;
					if (row.id == undefined) {
						$('#tt').datagrid('deleteRow', index);
						nextSelect = index > 0 ? index - 1 : 0;
						var cnt = $('#tt').datagrid('getRows').length;
						if (cnt > 0) {
							$('#tt').datagrid('selectRow', nextSelect);
						}
					}

				}
				id = row.id;
			}
			$.messager.confirm('提示', '确认修改?', function(t) {
				if (t) {
					$.post("delUser?id=" + id, function(result) {
						var rObj = eval(result);
						if (rObj.success) {
							$('#tt').datagrid('reload');
						} else {
							$.messager.alert('error', rObj.failure, 'warning');
						}
					});
				}
			});
		}
		
		function accept() {
			if (!endEditing())
				return;
			$.post(path+"/login/saveUser", ToSaveParam("tt"), function(result) {
				var rObj = eval(result);
				if (rObj.success) {
					$('#tt').datagrid('reload');
					$('#tt').datagrid('acceptChanges');
				} else {
					$.messager.alert('error', rObj.failure, 'warning');
				}
			});
		}
		
	</script>
</body>
</html>