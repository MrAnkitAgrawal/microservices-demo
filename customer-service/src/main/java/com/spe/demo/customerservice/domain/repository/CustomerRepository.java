package com.spe.demo.customerservice.domain.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spe.demo.customerservice.domain.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
	Page<Customer> findByName(String customerName, Pageable pageable);
}
