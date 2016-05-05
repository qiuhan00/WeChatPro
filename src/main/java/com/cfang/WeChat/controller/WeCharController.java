package com.cfang.WeChat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
		System.out.println("11111111111111111111111");
		return new ModelAndView(Constant.LoginManage.VIEW_LOGIN_PAGE);
	}

	@RequestMapping(value="/test")
	public ModelAndView Test(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("/error/404");
		return view;
	}
	
}
