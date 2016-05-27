package com.cfang.WeChat.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.cfang.WeChat.model.User;


/**
 * 记录登录用户的相关信息
 * @author Alex.Fang
 * @version 2016-5-27 下午4:02:29
 */
public class LoginUserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void setUserSession(HttpServletRequest request, User user){
		request.getSession().setAttribute("session_user", user);
	}
}
