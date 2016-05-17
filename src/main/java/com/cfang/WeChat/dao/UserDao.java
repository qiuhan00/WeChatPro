package com.cfang.WeChat.dao;


import java.util.List;
import java.util.Set;

import com.cfang.WeChat.model.OperatorResource;
import com.cfang.WeChat.model.User;

public interface UserDao {

	User getUser(int id);
	
	List<User> getUser();
	
	User getUser(String name);
	
}
