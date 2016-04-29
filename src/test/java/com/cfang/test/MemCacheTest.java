package com.cfang.test;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.whalin.MemCached.MemCachedClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-memcache.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class MemCacheTest {
	
	private static Logger logger = Logger.getLogger(MemCacheTest.class);  
	
	@Resource(name="memcachedClient")
	private MemCachedClient memcachedClient;
	
	@Test
	public void testMem(){
//		memcachedClient.set("name", "cfang");
		memcachedClient.delete("name");
		logger.info("提取的姓名："+memcachedClient.get("name"));
	}
}
