package com.cfang.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cfang.WeChat.dto.MenuDto;
import com.cfang.WeChat.model.OperatorResource;
import com.cfang.WeChat.model.Role;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-memcache.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class DbTest {
	
	private static Logger logger = Logger.getLogger(UserTest.class);  

	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Test
	public void test(){
		User user = this.userService.getUser(3);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("id", 2);
		map.put("passWord", "123321");
		map.put("openId", "o_sadfsdfe");
//		List<User> list = this.baseDaoImpl.queryParams("from User where id=:id", map,0,1, false);
//		logger.info("number:"+list.size()+",提取姓名："+list.get(0).getUserName());
		Set<OperatorResource> set = user.getRoleList().get(0).getResources();
		for(Iterator<OperatorResource> iterator = set.iterator();iterator.hasNext();){  
			logger.info("遍历："+iterator.next().getResourceName()+" ");  
        } 
		logger.info("提取："+user.getRoleList().get(0).getResources().size());
		
	}
}
