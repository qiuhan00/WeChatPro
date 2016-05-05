package com.cfang.WeChat.common;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String userName = (String) authcToken.getPrincipal();
		User user = userService.getUser(userName);
		if(user==null){
		      throw new UnknownAccountException("没有找到该账号");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(), getName());
		return info;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
