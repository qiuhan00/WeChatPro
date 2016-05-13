<%@page import="com.cfang.WeChat.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.cfang.WeChat.utils.DateHelper"%>
<%@page import="com.cfang.WeChat.utils.Constant"%>
<%@ page language="java" pageEncoding="utf-8"%>
<% 
 /**基本上下文路径*/
 String path = request.getContextPath(); 

 String basePath = request.getScheme() + "://"  
              + request.getServerName() + ":" + request.getServerPort() + path ;
 pageContext.setAttribute("HOME",basePath);
//当前登录日期
 request.setAttribute(Constant.LoginManage.LOGIN_DATE, DateHelper.convertToString(new Date(), DateHelper.YEAR_MONTH_DAY));
//昨天
 request.setAttribute("YESTERDAY",DateOperator.formatDate(DateOperator.addDays(new Date(), -1)));
//当前登录日期
 Calendar cal = new GregorianCalendar();
 cal.add(Calendar.DATE, -1);
 request.setAttribute(Constant.LoginManage.WEEK_AGO_DATE, DateHelper.convertToString(cal.getTime(), DateHelper.YEAR_MONTH_DAY));
 request.setAttribute("CURR_MONTH", DateHelper.convertToString(cal.getTime(), "yyyy-MM"));
 //一个月以前
 Calendar cal2 = new GregorianCalendar();
 cal2.add(Calendar.MONTH, -1);
 request.setAttribute("MONTH_AGO_DATE", DateHelper.convertToString(cal2.getTime(), DateHelper.YEAR_MONTH_DAY));
 request.setAttribute("LAST_MONTH", DateHelper.convertToString(cal2.getTime(), "yyyy-MM"));
 //一周以前
 Calendar cal3 = new GregorianCalendar();
 cal3.add(Calendar.WEEK_OF_YEAR, -1);
 request.setAttribute("WEEK_AGO_DATE", DateHelper.convertToString(cal3.getTime(), DateHelper.YEAR_MONTH_DAY));
 //本月月初
  Calendar cal4 = Calendar.getInstance();
  cal4.set(Calendar.DAY_OF_MONTH, 1);
  cal4.set(Calendar.HOUR_OF_DAY, 0);
  cal4.set(Calendar.MINUTE, 0);
  cal4.set(Calendar.SECOND, 0);
  request.setAttribute("MONTH_1TH_DATE", DateHelper.convertToString(cal4.getTime(), DateHelper.YEAR_MONTH_DAY));
 //三个月以前
 Calendar cal5 = new GregorianCalendar();
 cal5.add(Calendar.MONTH,-3);
 request.setAttribute("MONTH_3TH_DATE", DateHelper.convertToString(cal5.getTime(), DateHelper.YEAR_MONTH_DAY));
 
 request.setAttribute("path",path);
 
 //是否是测试环境
 pageContext.setAttribute("DEBUG",true);
 	/**当前平台*/
	String uri = request.getRequestURL().toString();
	if(uri.contains("mo9.com.cn"))
	{
		request.setAttribute("platform", "mo9");
	}
	else if(uri.contains("mo9.com"))
	{
		request.setAttribute("platform", "mo9");
	}
	else if(uri.contains("mokredit.com"))
	{
		request.setAttribute("platform", "mo9");
	}
	else if(uri.contains("localhost"))
	{
		request.setAttribute("platform", "mo9"); 
	}
	else
	{
	    request.setAttribute("platform", "mo9");
	}
	
/* 	response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
 */
 %>
<link rel="shortcut icon" href="<%=basePath%>/images/icon.png"/>
<script src="<%=path%>/js/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
<link href="<%=path%>/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/js/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>