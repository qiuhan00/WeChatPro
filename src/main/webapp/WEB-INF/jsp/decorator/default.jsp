<%@ page contentType="text/html;charset=UTF-8"%>  
<%@ include file="/WEB-INF/jsp/globalVariable.jsp" %>
<html>  
<head>  
<title>SiteMesh示例-<sitemesh:write property='title'/></title>  
<sitemesh:write property='head'/>
</head>  
<body>  
 <%-- <%@ include file="/WEB-INF/jsp/decorator/header.jsp"%>  --%> 
 <jsp:include page="/WEB-INF/jsp/decorator/header.jsp"></jsp:include>
  <div id="content">  
   <sitemesh:write property='body'/>  
  </div>  
 <%@include file="/WEB-INF/jsp/common/backStageFooter.jsp"%>  
</body>  
</html>  