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
		<table class="easyui-datagrid" id="tt" fitColumns=true url="${path }/login/find">
		</table>
	</div>
	<script src="${HOME }/js/user/common.js"></script>
	<script type="text/javascript">
		var path = "${path}";
		var isFirst = true;
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
				pagination : true,
				pageSize : 20,
				pageList : [ 50, 40, 30, 20, 10 ],
				showFooter : true,
				columns : [ [ {
					field : 'userName',
					title : '用户名',
					width : 150,
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
					width : 100
				},{
					field : 'openId',
					title : 'openId',
					align : 'center',
					width : 80
				}]],onBeforeLoad: function (param) {
					if(isFirst){
						isFirst = false;
						search();
					}
				}
			});
		};
		
	</script>
</body>
</html>