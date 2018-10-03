package com.spe.demo.orderservice.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spe.demo.orderservice.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	List<Order> findByCustomerId(Integer customerId);
}
