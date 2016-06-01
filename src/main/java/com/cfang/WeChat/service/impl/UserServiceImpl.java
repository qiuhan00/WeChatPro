package com.cfang.WeChat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.common.MasterDataSource;
import com.cfang.WeChat.common.MemCache;
import com.cfang.WeChat.dao.IBaseDao;
import com.cfang.WeChat.dao.UserDao;
import com.cfang.WeChat.dto.UserDto;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;
import com.cfang.WeChat.utils.Page;

@Service(value="userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name="userDaoImpl")
	private UserDao userDao;
	
	@MemCache(prefix="findUserById", expiration= 1000*60*60*2)
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@MemCache(prefix="findUsers", expiration= 1000*60*60)
	@Transactional(propagation=Propagation.REQUIRED)
	public Page getUser(Page page, UserDto dto) {
		return userDao.getUser(page, dto);
	}

	@Override
	public User getUser(String name) {
		return userDao.getUser(name);
	}

	@MasterDataSource(name="saveUser")
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveUser(User user) {
		this.userDao.saveUser(user);
	}
	
}
