package com.spe.demo.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spe.demo.customerservice.domain.model.Customer;
import com.spe.demo.customerservice.domain.repository.CustomerRepository;

@Service
public class CustomerService {
	CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer getCustomerById(final int customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer == null) {
			return null;
		}
		return customer.get();
	}
	
	public Page<Customer> getCustomerByName(final String customerName, Pageable pageable) {
		return customerRepository.findByName(customerName, pageable);
	}
	
	public Page<Customer> getAllCustomer(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomerById(final int customerId) {
		customerRepository.deleteById(customerId);
	}
}