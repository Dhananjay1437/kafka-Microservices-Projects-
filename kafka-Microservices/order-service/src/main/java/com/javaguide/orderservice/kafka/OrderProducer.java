package com.javaguide.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.javaguide.basedomain.dto.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderProducer {

	private NewTopic topic;
	
	private KafkaTemplate<String,OrderEvent> kafkaTemplate;
	
	public OrderProducer(NewTopic topic,KafkaTemplate<String,OrderEvent> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
		this.topic=topic;
	}
	
	public void sendMessage(OrderEvent event) {
		log.info(String.format("Order event=>%s",event.toString()));
		Message<OrderEvent> message=MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC,topic.name()).build();
		kafkaTemplate.send(message);
	}
}
