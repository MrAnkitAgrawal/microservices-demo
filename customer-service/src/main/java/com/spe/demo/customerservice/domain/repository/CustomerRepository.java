package com.spe.demo.customerservice.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spe.demo.customerservice.domain.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	List<Customer> findByName(String customerName);
}
