package com.javaguide.stockservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.javaguide.basedomain.dto.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderConsumer {

	@KafkaListener(topics="${spring.kafka.topic.name}",
			groupId= "${spring.kafka.consumer.group-id}")
	public void consumer(OrderEvent event) {
		log.info(String.format("Order event recived in stock services => %s",event.toString()));
		
		//save the order data in database
		
	}
}
