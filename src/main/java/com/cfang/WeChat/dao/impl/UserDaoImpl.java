package com.cfang.WeChat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cfang.WeChat.dao.UserDao;
import com.cfang.WeChat.model.User;

@Repository(value="userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
	@Override
	public User getUser(int id) {
		return this.find(User.class, id);
	}

	@Override
	public List<User> getUser() {
		return this.queryParams("from User", null, Boolean.FALSE);
	}
	
	

}
