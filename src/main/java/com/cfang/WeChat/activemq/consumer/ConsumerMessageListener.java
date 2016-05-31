package com.cfang.WeChat.activemq.consumer;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if(message instanceof MapMessage){
			try {
				MapMessage result = (MapMessage) message;
				System.out.println("用户:" + result.getString("userName"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(message instanceof TextMessage){
			TextMessage result = (TextMessage) message;
			System.out.println("接收到纯文本信息");
			try {
				System.out.println("message:"+result.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
