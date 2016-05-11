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
<style>
.west {
	width: 200px;
	padding: 10px;
}

.north {
	height: 70px;
}
</style>
</head>
<body class="easyui-layout">
	<%-- <div region="north" class="north" title="">
		<jsp:include page="/WEB-INF/jsp/common/backStageHeader.jsp"
			flush="true" />
	</div> --%>
	<div region="center" title="首页">
		<div class="easyui-tabs" fit="true" id="tabs">
			<div title="首页"></div>
		</div>
	</div>
	<div region="west" class="west" title="菜单">
		<ul id="tree" class="ztree"></ul>
	</div>
	<div region="south" class="north" title="">
		<jsp:include page="/WEB-INF/jsp/common/backStageFooter.jsp"
			flush="true" />
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	<script type="text/javascript">
		var path = "${path}";
	</script>
</body>
</html>