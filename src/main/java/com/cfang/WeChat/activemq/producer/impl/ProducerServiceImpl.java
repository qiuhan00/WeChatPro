package com.cfang.WeChat.activemq.producer.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.cfang.WeChat.activemq.producer.ProducerService;

//@Service
public class ProducerServiceImpl implements ProducerService{
	
	private JmsTemplate jmsTemplate;

	@Override
	public void sendMessage(Destination destination, final String message) {
		System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	@Resource
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
