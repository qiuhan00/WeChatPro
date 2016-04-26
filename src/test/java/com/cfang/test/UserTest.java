package com.cfang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.dao.IBaseDao;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class UserTest {
	
	private static Logger logger = Logger.getLogger(UserTest.class);  
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	@Resource(name="baseDaoImpl")
	private IBaseDao baseDaoImpl;
	@Test
	public void get(){
		User user = this.userService.getUser(1);
		logger.info("这是提取的姓名："+user.getUserName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("userName", "test1");
		List<User> list = this.baseDaoImpl.queryParams(User.class, map, false);
		logger.info("这是提取的姓名："+list.size());
	}
}
