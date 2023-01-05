package com.javaguide.orderservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguide.basedomain.dto.Order;
import com.javaguide.basedomain.dto.OrderEvent;
import com.javaguide.orderservice.kafka.OrderProducer;

@RestController
@RequestMapping("api/v1")
public class OrderController {
@Autowired
	OrderProducer orderProducer;

@PostMapping("/orders")
public String placeOrder(@RequestBody Order order) {
	order.setOrderId(UUID.randomUUID().toString());
	OrderEvent orderEvent=new OrderEvent();
	orderEvent.setStatus("Pending");
	orderEvent.setMessage("order status is in pending state");
	orderEvent.setOrder(order);
	orderProducer.sendMessage(orderEvent);
	return "Order placed successfully";
	
}
}