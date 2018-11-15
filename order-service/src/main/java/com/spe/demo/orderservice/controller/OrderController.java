package com.spe.demo.orderservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spe.demo.orderservice.domain.model.Order;
import com.spe.demo.orderservice.services.OrderService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController()
public class OrderController {
	@Autowired
	OrderService orderService;

	@GetMapping(path = "/customers/{customerId}/orders")
	public List<Order> getOrdersByCustomerId(@PathVariable int customerId) {
		return orderService.getOrdersByCustomerId(customerId);
	}

	@GetMapping(path = "/customers/{customerId}/orders/{orderId}")
	public Resource<Order> getOrdersById(@PathVariable int customerId, @PathVariable int orderId) {
		List<Order> customerOrderList = orderService.getOrdersByCustomerId(customerId);
		for (Order order : customerOrderList) {
			if (order.getOrderId().equals(orderId)) {
				Resource<Order> orderResource = new Resource<>(order);
				ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getOrdersByCustomerId(customerId));
				orderResource.add(link.withRel("parent"));
				return orderResource;
			}
		}
		return null;
	}

	//@PostMapping(path = "/customers/{customerId}/orders")
	/*public ResponseEntity<?> createOrder(@PathVariable int customerId, @RequestParam(value = "itemCode") final int itemCode,
			@RequestParam(value = "units") final int units) {*/
	@PostMapping(path = "/customers/{customerId}/orders")
	public ResponseEntity<?> createOrder(@PathVariable int customerId, @RequestBody final Map<String, Integer> orderParam) {
		try {
			if (orderParam == null) {
				return ResponseEntity.badRequest().build();
			}
			int itemCode = orderParam.containsKey("itemCode") ? orderParam.get("itemCode") : -1;
			int units = orderParam.containsKey("units") ? orderParam.get("units") : -1;
			
			if(itemCode == -1 || units == -1) {
				return ResponseEntity.badRequest().build();
			}
			
			Order order = orderService.createOrder(customerId, itemCode, units);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(order.getOrderId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			String cause = (e.getCause() != null) ? e.getCause().getMessage() : e.getMessage();
			throw new RuntimeException("Error during order creation! Cause: " + cause);
		} 
	}
}
