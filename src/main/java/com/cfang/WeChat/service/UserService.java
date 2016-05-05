package com.cfang.WeChat.service;


import java.util.List;

import com.cfang.WeChat.model.User;

public interface UserService {

	User getUser(int id);
	
	List<User> getUser();
	
	User getUser(String name);
}
