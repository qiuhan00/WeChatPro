<%@page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
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
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no" />
<link rel="stylesheet"
	href="${HOME}/js/zTree/css/zTreeStyle/zTreeStyle.css" />
<link href="${HOME}/css/base.css" rel="stylesheet" type="text/css" />
<script src="${HOME}/js/zTree/jquery.ztree.core.js"></script>
<script src="${HOME}/js/user/index.js"></script>
</head>
<body>
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
            <td>001</td><td>name1</td><td>2323</td>  
        </tr>  
        <tr>  
            <td>002</td><td>name2</td><td>4612</td>  
        </tr>  
    </tbody> 
	</table>
</body>
</html>