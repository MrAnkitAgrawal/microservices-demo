package com.spe.demo.customerservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spe.demo.customerservice.domain.model.Customer;
import com.spe.demo.customerservice.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping
	Iterable<Customer> getAllCustomers() {
		return customerService.getAllCustomer();
	}

	@GetMapping(path = "/{customerId}")
	Customer getCustomerByID(@PathVariable final int customerId) {
		return customerService.getCustomerById(customerId);

	}

	@PostMapping
	ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.saveCustomer(customer);

		if (createdCustomer == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{customerId}")
	ResponseEntity<?> updateCustomer(@PathVariable("customerId") final int customerId, @RequestBody Customer customer) {
		Customer existingCustomer = customerService.getCustomerById(customerId);
		if (existingCustomer == null) {
			return ResponseEntity.notFound().build();
		}
		customerService.saveCustomer(customer);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{customerId}")
	ResponseEntity<?> updateCustomerDetails(@PathVariable("customerId") final int customerId,
			@RequestBody Customer customer) {
		Customer existingCustomer = customerService.getCustomerById(customerId);
		if (existingCustomer == null) {
			ResponseEntity.notFound().build();
		}
		customerService.saveCustomer(customer);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{customerId}")
	void deletCustomer(@PathVariable("customerId") final int customerId) {
		customerService.deleteCustomerById(customerId);
	}
}
