package com.spe.demo.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spe.demo.orderservice.domain.model.Order;
import com.spe.demo.orderservice.services.OrderService;

@RestController()
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping(path="/customers/{customerId}/orders")
	public List<Order> getOrdersByCustomerId(@PathVariable int customerId) {
		return orderService.getOrdersByCustomerId(customerId);
	}
	
	@GetMapping(path="/customers/{customerId}/orders/{orderId}")
	public Order getOrdersById(@PathVariable int customerId, @PathVariable int orderId) {
		List<Order> customerOrderList = orderService.getOrdersByCustomerId(customerId);
		for(Order order : customerOrderList) {
			if(order.getOrderId().equals(orderId)){
				return order;
			}
		}
		return null;
	}
	
	@PostMapping(path="/customers/{customerId}/orders")
	public ResponseEntity<?> createOrder(@PathVariable int customerId, @RequestBody Order order) {
		return null;
	}
}
