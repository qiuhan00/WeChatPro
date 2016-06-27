package com.cfang.test;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cfang.WeChat.activemq.producer.ProducerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-memcache.xml",
		"classpath:applicationContext-jms.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class ActivemqTest {

	@Autowired
	private ProducerService producerService;
	@Resource(name="p2pDestination")
	private Destination destination;
	
	@Test
	public void testSend(){
		for (int i=0; i<2; i++) {  
            producerService.sendMessage(destination, "你好！这是消息：" + (i+1));  
        }  
	}
}
