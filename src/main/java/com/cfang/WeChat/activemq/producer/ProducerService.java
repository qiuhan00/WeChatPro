package com.cfang.WeChat.activemq.producer;

import javax.jms.Destination;

public interface ProducerService {

	void sendMessage(Destination destination, final String message);
}
