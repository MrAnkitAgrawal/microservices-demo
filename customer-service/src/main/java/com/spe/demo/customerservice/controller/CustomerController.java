package com.spe.demo.customerservice.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
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
	@Autowired
	CustomerAssembler customerAssembler;

	/*@GetMapping
	Page<CustomerDTO> getAllCustomers(Pageable pageable) {
		Page<Customer> customerListPage = customerService.getAllCustomer(pageable);
		List<CustomerDTO> customerDTOList = customerListPage.getContent().stream().map(customer -> customerAssembler.toResource(customer)).collect(Collectors.toList());
		
		return new PageImpl<CustomerDTO>(customerDTOList, pageable, customerListPage.getTotalPages());
	}*/
	
	@GetMapping
	PagedResources<CustomerDTO> getAllCustomers(Pageable pageable, PagedResourcesAssembler<Customer> pagedAssembler) {
		Page<Customer> customerListPage = customerService.getAllCustomer(pageable);
		return pagedAssembler.toResource(customerListPage, customerAssembler);
	}

	@GetMapping(path = "/{customerId}")
	CustomerDTO getCustomerByID(@PathVariable final int customerId) {
		return customerAssembler.toResource(customerService.getCustomerById(customerId));
	}

	@PostMapping
	ResponseEntity<?> saveCustomer(@RequestBody @Valid Customer customer) {
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
