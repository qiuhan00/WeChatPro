package com.cfang.WeChat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptors extends HandlerInterceptorAdapter {

/**
 * 拦截器
 */
public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception{
		
		System.out.println("请求uri:"+req.getRequestURI());
		return super.preHandle(req, resp, handler);
	}
}
