package com.cfang.WeChat.common;


import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.model.Role;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;

@Service  
@Transactional
public class ShiroDbRealm extends AuthorizingRealm {

	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println(111);
		String userName = (String) principals.fromRealm(getName()).iterator().next();
		User user = this.userService.getUser(userName);
		if(null != user){
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setRoles(user.getRoles());
			List<Role> roles = user.getRoleList();
			for(Role role : roles){
				info.addStringPermissions(role.getPermissions());
			}
			return info;
		}
		return null;
	}

	/**
	 * 身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = this.userService.getUser(token.getUsername());
		SimpleAuthenticationInfo info = null;
		if(null != user){
			info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(), getName());
		}
		return info;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
