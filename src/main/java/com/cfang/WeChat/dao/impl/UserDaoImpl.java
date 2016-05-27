package com.cfang.WeChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cfang.WeChat.dao.UserDao;
import com.cfang.WeChat.dto.UserDto;
import com.cfang.WeChat.model.OperatorResource;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.utils.DateOperator;
import com.cfang.WeChat.utils.Page;

@Repository(value="userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
	@Override
	public User getUser(int id) {
		return this.find(User.class, id);
	}

	@Override
	public Page getUser(Page page, UserDto dto) {
		StringBuffer hql = new StringBuffer("from User u where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(dto.getUserName())){
			hql.append(" and u.userName = :userName");
			params.put("userName", dto.getUserName());
		}
		if(StringUtils.isNotBlank(dto.getStartDate())){
			hql.append(" and u.createTime >= :startDate");
			params.put("startDate", DateOperator.parse(dto.getStartDate()));
		}
		if(StringUtils.isNotBlank(dto.getEndDate())){
			hql.append(" and u.createTime < :endDate");
			params.put("endDate", DateOperator.addDays(DateOperator.parse(dto.getEndDate(), DateOperator.FORMAT_STR), 1));
		}
		return this.findPage(page, hql.toString(), params);
	}

	@Override
	public User getUser(String name) {
		return this.queryByParamUnique("from User where userName='"+name+"'", null);
	}
	
	public void saveUser(User user){
		this.save(user);
	}
}
