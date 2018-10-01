package com.spe.demo.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return customer.get();
	}
	
	public List<Customer> getCustomerByName(final String customerName) {
		return customerRepository.findByName(customerName);
	}
	
	public Iterable<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomerById(final int customerId) {
		customerRepository.deleteById(customerId);
	}
}