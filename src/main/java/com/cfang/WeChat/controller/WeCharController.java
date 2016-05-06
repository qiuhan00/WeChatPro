package com.cfang.WeChat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.sword.wechat4j.WechatSupport;

import com.cfang.WeChat.utils.Constant;


@Controller
@RequestMapping(value="/wechatPro")
public class WeCharController{
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView(Constant.LoginManage.VIEW_LOGIN_PAGE);
	}

	@RequestMapping(value="/test")
	public ModelAndView Test(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("/error/404");
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken(request.getParameter("username"), 
					request.getParameter("password")));
		} catch (AuthenticationException e) {
			view = new ModelAndView(Constant.LoginManage.VIEW_LOGIN_PAGE);
			view.addObject("errorMsg", "用户名或者密码错误"); 
		}
		return view;
	}
	
}
