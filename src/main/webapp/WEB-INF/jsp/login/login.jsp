<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/jsp/globalVariable.jsp" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="/spring.tld" %>
<%@ taglib prefix="form" uri="/spring-form.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>login</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no"/>
    <link href="${HOME }/css/login.css?v=4" rel="stylesheet" type="text/css" />
     <link href="${HOME}/css/base.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="login">
<div class="loginBg">
   <form id="loginForm" action="test" method="post" >
   <div class=" login_form">
   <div class=" loginFormInner">
   <p class=" error red">
   	<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(error != null){
	%>
			登录失败，请重试.
	<%
	}
	%>
   	</p>
   <!-- 	<div class="logo"></div> -->
   <h1>后台管理系统</h1>
   <h2>${errorMsg }</h2>
   <p>
   		<input type="text" required id="uname" name="username" value="" placeholder="用户名"/> 
   </p>
   <p class=" spasial"> 
		<input required type="password" id="passwd" name="password" value="" placeholder="密码"/> 
	</p>
   </div>
	<div class="toolbar">
    <input type="submit" value="登录" class="loginBtn"/>
	</div>
   </div>
   </form>
</div>
<jsp:include page="/WEB-INF/jsp/common/backStageFooter.jsp" flush="true" />
</div>

<script type="text/javascript">
	resizeLogin();
	$(window).resize(function() {
		resizeLogin();
	});
	function resizeLogin(){
		//浏览器宽度
		var clientHeight=$(window).innerHeight();
		var loginHeight=$('#login').outerHeight()+20;
		var clientWidth=$(window).innerWidth();
		var loginWidth=$('#login').outerWidth()+20;
// 		console.log(clientHeight,loginHeight);
		var top=(clientHeight-loginHeight)/4;
		if (loginHeight>=clientHeight){
			top=0;
		}
		var left=(clientWidth-loginWidth)/2;
		if (loginWidth>=clientWidth){
			left=0;
		}
		$('#login').css("top",top);
		$('#login').css("left",left);
	}
	</script>
</body>
</html>