package com.cfang.WeChat.dao;


import java.util.List;
import java.util.Set;

import com.cfang.WeChat.dto.UserDto;
import com.cfang.WeChat.model.OperatorResource;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.utils.Page;

public interface UserDao {

	User getUser(int id);
	
	Page getUser(Page page, UserDto dto);
	
	User getUser(String name);
	
	void saveUser(User user);
}
