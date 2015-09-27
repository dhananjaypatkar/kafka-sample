package com.dhananjay.disb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

public class KafkaProducer {

	private static final Log LOG = LogFactory.getLog(KafkaProducer.class);
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("producer-spring-config.xml");
		applicationContext.start();
		System.out.println("Loaded");
		for (int i = 0; i < 500; i++) {
			final MessageChannel channel = applicationContext.getBean("inputToKafka", MessageChannel.class);
			channel.send(
					MessageBuilder.withPayload("Dhananjay")
							.setHeader("messageKey", String.valueOf(i))
							.setHeader("topic", "test1").build());

			LOG.info("message sent " + i);
		}
	}
	
}
