/**
 *  File: Constant.java
 *  Description:
 *      该文件主要用于定义各种表示层需要的各种常量，
 *  包括session，request中的属性名，各种转发/跳转需要的URL等。
 *
 *  Copyright 2004-2008 Gamax Interactive. All rights reserved.
 *  Date            Author      Changes
 *  2011-3-1       CaoHui      创建
 */
package com.cfang.WeChat.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * <p>
 * 表示层常量
 * </p>
 * 
 * <p>
 * 该类主要用于定义表示层需要的各种常量，包括session，request中的属性名，各种转发/跳转需要的URL等
 * </p>
 * 
 * @author CaoHui
 * 
 */
public class Constant {
	public static final String SUCCESS = "success";
	public static final String DEFAULTCOMMON = "command";

	// 权限管理跳转页面
	public static final String OperatorPAGE = "/security/ControlUsers";
	public static final String RESOURCEPAGE = "/security/ControlResourse";
	public static final String ROLEPAGE = "/security/ControlRole";
	public static final String REDISPAGE = "/security/ResourseDistribute";
	public static final String RODISPAGE = "/security/RoleDistribute";

	// 规则跳转页面
	public static final String CREDITRULEPAGE = "/credit/creditLevel";
	public static final String RULEMANAGE = "/credit/ruleManage";
	public static final String CREDITRULETYPEPAGE = "/credit/creditRuleTypes";
	public static final String RESULTPEPAGE = "/credit/resultTypes";

	public static final String PRESENTGCOINTSPAGE = "/koins/prsentKoins";
	public static final String CURRENCYKOINSPAGE = "/koins/currencyKoins";
	public static final String EXCHANGERECORDPAGE = "/deal/dealExchange";

	// 支付渠道列表页面
	public static final String CHANNEL_LIST_PAGE = "/other/channelList";
	// 模板編輯頁面
	public static final String TEMPLATE_EDIT_PAGE = "/other/template";

	/**
	 * 登录管理相关的常量。
	 * 
	 */
	public static final class LoginManage {
		/** 表示层资源ID：登录页面 */
		public static final String VIEW_LOGIN_PAGE = "/login/login";

		/** 表示层资源ID：主界面 */
		public static final String VIEW_MAIN_PAGE = "/login/backStage";

		/*** 当前操作员权限菜单(Session属性) */
		public static final String LOGIN_OPERATOR_MENU = "LOGIN_OPERATOR_MENU";

		/*** 当前操作员登录日期(Session属性) */
		public static final String LOGIN_DATE = "LOGIN_DATE";

		/*** 当前操作员登录日期(一星期以前)(Session属性) */
		public static final String WEEK_AGO_DATE = "WEEK_AGO_DATE";

		/** 当前请求的一级资源(request属性) */
		public static final String CURRENT_RESOURCE_LEVEL_1 = "CURRENT_RESOURCE_LEVEL_1";

		/** 当前请求的二级资源(request属性) */
		public static final String CURRENT_RESOURCE_LEVEL_2 = "CURRENT_RESOURCE_LEVEL_2";

		/** 当前请求的三级资源(request属性) */
		public static final String CURRENT_RESOURCE_LEVEL_3 = "CURRENT_RESOURCE_LEVEL_3";
		public static final String ROLE_ADMIN = "admin";
	}

	/**
	 * 用户管理相关的常量
	 */

	public static final class UserManage {
		/** 玩家详情页面 */
		public static final String BUYER_DETAIL_PAGE = "/user/userDetail_ajax";

		/** 玩家列表页面 */
		public static final String BUYER_LIST_PAGE = "/user/userList_ajax";

		/** 玩家状态列表页面 */
		public static final String BUYER_STATUS_LIST_PAGE = "/user/userStatusList_ajax";

		/** 玩家查询页面 */
		public static final String BUYER_QUERY_PAGE = "/user/UserSearch";

		/** 玩家启用/禁用页面 */
		public static final String BUYER_ACTIVE_INTERDICT_PAGE = "/user/activeAndInterdict";

		/** 编辑编辑页面 */
		public static final String BUYER_EDIT_PAGE = "/user/userManagePanel_userEdit";

		/*** 国籍列表 */
		public static final String COUNTRY_LIST = "COUNTRY_LIST";

		/** 信用等级列表 */
		public static final String CREDIT_LIST = "CREDIT_LIST";

		/** 玩家列表 */
		public static final String BUYER_LIST = "BUYER_LIST";

		/** 玩家详情 */
		public static final String BUYER_DETAIL = "BUYER_DETAIL";

	}

	/**
	 * 商家管理相关的常量
	 */
	public static final class MerchantManage {
		/** 商家详情页面 */
		public static final String MERCHANT_DETAIL_PAGE = "/merchant/merchantDetail_ajax";

		/** 商家列表页面 */
		public static final String MERCHANT_LIST_PAGE = "/merchant/merchantList_ajax";

		/** 商家状态列表页面 */
		public static final String MERCHANT_STATUS_LIST_PAGE = "/merchant/merchantStatusList_ajax";

		/** 商家忘记密码列表 */
		public static final String MERCHANT_FORGETPASSWD_LIST_PAGE = "/merchant/forgetPasswdList_ajax";

		/** 商家忘记密码处理页面 */
		public static final String MERCHANT_FORGETPASSWD_PAGE = "/merchant/forgetPasswdRequest";

		/** 商家查询页面 */
		public static final String MERCHANT_QUERY_PAGE = "/merchant/merchantSearch";

		/** 商家启动/禁止页面 */
		public static final String MERCHANT_ACTIVE_INTERDICT_PAGE = "/merchant/activeAndInterdict";

		/** 商家编辑页面 */
		public static final String MERCHANT_EDIT_PAGE = "/merchant/merchantEdit_ajax";

		/** 商家账户查询页面 */
		public static final String MERCHANT_ACCOUNT_PAGE = "/merchant/merchantAccountSearch";

		/** 商家账户查询列表页 */
		public static final String MERCHANT_ACCOUNT_LIST_PAGE = "/merchant/merchantAccountSearchList_ajax";

		/*** 国籍列表 */
		public static final String COUNTRY_LIST = "COUNTRY_LIST";

		/** 商家列表 */
		public static final String MERCHANT_LIST = "MERCHANT_LIST";

		/** 商家详情 */
		public static final String MERCHANT_DETAIL = "MERCHANT_DETAIL";

	}

	/**
	 * 信用等级管理
	 */
	public static final class CreditManage {

		/** 信用等级管理页面 */
		public static final String CREDIT_PARAMS_PAGE = "/credit/creditParams";

		/** 当前显示的信用等级 */
		public static final String CURRENT_CREDIT_PARAM = "CURRENT_CREDIT_PARAM";

		/*** 当前显示的国家 */
		public static final String CURRENT_COUNTRY = "CURRENTY_COUNTRY";

	}

	/**
	 * 风险控制管理
	 */
	public static final class RiskManage {
		/** 风险控制管理页面 **/
		public static final String RISK_MANAGE_PAGE = "/risk/riskManage";
	}

	/**
	 * 订单管理
	 */
	public static final class DealManage {
		/** 订单查询页面 */
		public static final String DEAL_SEARCH_PAGE = "/deal/dealSearch";

		/** 订单查询ajax页面 */
		public static final String DEAL_SEARCH_LIST = "/deal/dealSearch_ajax";

		/*** 收入查询页面 */
		public static final String DEAL_INCOME_PAGE = "/deal/dealIncome";

		/** 收入查询ajax页面 */
		public static final String DEAL_INCOME_LIST = "/deal/dealIncome_ajax";

		/*** 支出查询页面 */
		public static final String DEAL_OUTCOME_PAGE = "/deal/dealOutcome";

		/** 支出查询ajax页面 */
		public static final String DEAL_OUTCOME_LIST = "/deal/dealOutcome_ajax";

		/** 查看订单详情页面 */
		public static final String DEAL_DETAIL_PAGE = "/deal/dealDetail";

		/** 修改订单详情页面 */
		public static final String DEAL_EDIT_PAGE = "/deal/dealEditDetail";
	}

	/**
	 * 充值/还款策略管理
	 */
	public static final class GamaxCoinsManage {
		/** 初始化页面 */
		public static final String INDEX_PAGE = "/deal/gamaxCoins";
	}

	/**
	 * 报表管理
	 */
	public static final class ChartManage {
		/*** 收入统计报表 */
		public static final String INCOME__CHART_PAGE = "/chart/incomeChart";

		/*** 支出统计报表 */
		public static final String OUTCOME__CHART_PAGE = "/chart/outcomeChart";
	}

	/**
	 * 公共常量
	 */

	public static final class Common {
		/** 操作结果 */
		public static final String RESULT = "RESULT";
		public static final String DEALSTATUS = "DealStatus";
		public static final String DEALTYPES = "DealTypes";

		public static final String LIST_1 = "LIST_1";

		public static final String LIST_2 = "LIST_2";

		public static final String LIST_3 = "LIST_3";

		/** 分页查询，默认页面容量 */
		public static final int DEFAULT_PAGE_SIZE = 10;

		/** 分页信息页面 */
		public static final String PAGE_INFO_PAGE = "/user/pageInfo";

	}

	public static final class MarketManager {
		public static final String PAGE_INDEX = "/market/marketManager";

		/**
		 * 国家
		 */
		public static final String COUNTRY_LIST = "countryList";

		public static final String CREDIT_LIST = "creditList";

		public static final String BUYER_LIST = "buyerList";

		public static final String MARKETFORM = "marketForm";

		public static final String MARKET_MANAGER_AJAX = "/market/marketManager_ajax";

		public static final String MARKET_MANAGER__REPAYMENT_AJAX = "/market/marketManager_repayment_ajax";

		public static final String MARKET_MANAGER__EMAIL_AJAX = "/market/marketManager_email_ajax";

		public static final String MARKET_MANAGER__SMS_AJAX = "/market/marketManager_sms_ajax";

		public static final String LIST_COUNT = "buyerCount";
	}
	
	/**
	 * 用户交易类型
	 * @author huangyong
	 *
	 */
	public static final class UserDealType {
		
		/**
		 * 数字字典常量DEAL_TYPE
		 */
		public static final String DEAL_TYPE = "DEAL_TYPE";
	}

	public static final class I18nMessage {
		public static final String LANGUAGE_LIST = "LANGUAGE_LIST";
		public static final String CODE_LIST = "CODE_LIST";
	}

	/**
    * 
    */
	public static Properties tempPro = new Properties();
	static {
		try {
			InputStream in = Constant.class.getResourceAsStream("tempConfig.properties");
			tempPro.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final String BROWSER_TYPE = "_browserType";// 浏览类型：mobile,pc
	public static final String BROWSER_TYPE_PC = "pc";// 浏览类型：mobile,pc
	public static final String BROWSER_TYPE_MOBILE = "mobile";// 浏览类型：mobile,pc
}
