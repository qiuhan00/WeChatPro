package com.cfang.WeChat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.common.MemCache;
import com.cfang.WeChat.dao.UserDao;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;

@Service(value="userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name="userDaoImpl")
	private UserDao userDao;
	
	@MemCache(prefix="findUserById", expiration= 1000*60*60*2)
	@Transactional(propagation=Propagation.REQUIRED)
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@MemCache(prefix="findUsers", expiration= 1000*60*60)
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> getUser() {
		return userDao.getUser();
	}

	@Override
	public User getUser(String name) {
		return userDao.getUser(name);
	}
	
	

}
