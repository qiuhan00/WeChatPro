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
				<b>用户名：</b> <input type="text" name="username" class="easyui-validatebox" /> 
				<b>创建时间：</b> 
				<input id="startDate" name="startDate" type="text" class="easyui-datebox" style="width: 100px;"
					data-options="formatter:myformatter,parser:myparser" /> 
					至 <input id="endDate" name="endDate" type="text" class="easyui-datebox"
					style="width:100px;" data-options="formatter:myformatter,parser:myparser" />
			</p>
		</form>
	</div>
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'code'">Code</th>
				<th data-options="field:'name'">Name</th>
				<th data-options="field:'price'">Price</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>name1</td>
				<td>2323</td>
			</tr>
			<tr>
				<td>002</td>
				<td>name2</td>
				<td>4612</td>
			</tr>
		</tbody>
	</table>

	<script type="text/javascript">
		var path = "${path}";
		$(function() {

		});
		function myformatter(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}

		function myparser(s) {
			if (!s)
				return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0], 10);
			var m = parseInt(ss[1], 10);
			var d = parseInt(ss[2], 10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
				return new Date(y, m - 1, d);
			} else {
				return new Date();
			}
		}
	</script>
</body>
</html>