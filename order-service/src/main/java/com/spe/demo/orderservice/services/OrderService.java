package com.spe.demo.orderservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spe.demo.orderservice.domain.model.Order;
import com.spe.demo.orderservice.domain.repository.OrderRepository;

@Service
public class OrderService {
	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> getOrdersByCustomerId(final Integer customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	public Order getOrderById(final Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		return order.get();
	}
	
	public ResponseEntity<?> createOrder(final int customerId, final int itemCode, final int units) {
		// check if itemCode is valid and units are available
		// if yes, calculate total ammount
		
		// check if customer is valid and having enough credit
		// if yes, 
			// update item units
			// update customer credit
			// place order
		return null;
	}

}
