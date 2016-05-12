package com.cfang.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cfang.WeChat.memcache.MemCacheService;
import com.cfang.WeChat.model.User;
import com.cfang.WeChat.service.UserService;
import com.whalin.MemCached.MemCachedClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-memcache.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class MemCacheTest {
	
	private static Logger logger = Logger.getLogger(MemCacheTest.class);  
	
	@Resource(name="memcachedClient")
	private MemCachedClient memcachedClient;
	@Resource(name="userServiceImpl")
	private UserService userService; 
	@Resource(name="memCacheServiceImpl")
	private MemCacheService memCacheService;
	
	@Test
	public void testMem(){
		memcachedClient.set("999", "cfang");
//		memcachedClient.delete("findUserById_BDA6203EA0E67EB01603A7E5993ECD95");
//		logger.info("提取的姓名："+memcachedClient.get("999"));
//		User user = this.userService.getUser(2);
//		logger.info("提取的姓名："+user.getUserName());
		
//		List<User> user = this.userService.getUser();
//		logger.info("提取, size:"+user.size()+"|name:" + user.get(0).getUserName());
//		
//		logger.info("memcache提取的姓名："+ this.memCacheService.get("findUsers_BDA6203EA0E67EB01603A7E5993ECD95"));
	}
}
