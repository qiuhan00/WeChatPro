package com.cfang.WeChat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.dao.UserDao;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;

@Service(value="userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name="userDaoImpl")
	private UserDao userDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User getUser(int id) {
		return userDao.getUser(id);
	}

}
