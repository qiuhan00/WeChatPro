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
<link rel="stylesheet" href="${HOME}/js/zTree/css/zTreeStyle/zTreeStyle.css" />
<link href="${HOME}/css/base.css" rel="stylesheet" type="text/css" />
<script src="${HOME}/js/zTree/jquery.ztree.core.js"></script>
<script src="${HOME}/js/user/index.js"></script>
<style>
.west {
	width: 150px;
	padding: 1px;
	background: #f0f6e4;
}

.north {
	height: 70px;
	background: #f0f6e4;
}
/* .ztree li a.level0 {width:200px;height: 20px; text-align: center; display:block; background-color: #66A3D2; border:1px silver solid;}
.ztree li a.level0.cur {background-color: #66A3D2; }
.ztree li a.level0 span {display: block; color: white; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
.ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
.ztree li span.button.switch.level0 {display:none;} */
</style>
</head>
<body class="easyui-layout">
	<%-- <div region="north" class="north" title="">
		<jsp:include page="/WEB-INF/jsp/common/backStageHeader.jsp"
			flush="true" />
	</div> --%>
	<div region="center" title="首页">
		<div class="easyui-tabs" fit="true" id="tabs" >
			<div title="登录信息" style="background:#f0f6e4;">
				<div style="padding:20px;width:auto;">
					<a style="color:#000000;padding:5px ">用户名：${userName }</a></br>
					<a style="color:#000000;padding:5px">上次登录时间：</a></br>
				</div>
				<div style="padding:10px;width:auto;border:1px solid #ccc;">
					<a id="exit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back">退出</a> 
				</div>
			</div>
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
		$("#exit").click(function(){
			path = path + "/login/logout";
			window.location.href=path;
		});
	</script>
</body>
</html>