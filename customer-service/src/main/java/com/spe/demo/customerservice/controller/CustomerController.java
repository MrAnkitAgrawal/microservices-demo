package com.spe.demo.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spe.demo.customerservice.domain.model.Customer;
import com.spe.demo.customerservice.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("{customerId}")
	Customer getCustomerByID(@PathVariable("customerId") final int customerId) {
		return customerService.getCustomerById(customerId);
	}

	@GetMapping
	Iterable<Customer> getAllCustomers() {
		return customerService.getAllCustomer();
	}

	@PostMapping
	Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@PutMapping
	Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@PatchMapping("/{customerId}")
	Customer updateCustomer(@PathVariable("customerId") final int customerId, @RequestBody Customer customer) {
		Customer existingCustomer = customerService.getCustomerById(customerId);
		if (existingCustomer == null) {
			return null;
		}
		return customerService.saveCustomer(customer);
	}

	@DeleteMapping("/{customerId}")
	void deletCustomer(@PathVariable("customerId") final int customerId) {
		customerService.deleteCustomerById(customerId);
	}
}
