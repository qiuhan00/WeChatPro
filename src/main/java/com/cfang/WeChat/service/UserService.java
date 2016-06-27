package com.cfang.WeChat.service;


import java.util.List;

import com.cfang.WeChat.dto.UserDto;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.utils.Page;

public interface UserService {

	User getUser(int id);
	
	Page getUser(Page page, UserDto dto);
	
	User getUser(String name);
	
	void saveUser(User user);
	
	List<User> getUsers();
}
