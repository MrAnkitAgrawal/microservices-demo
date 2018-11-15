package com.spe.demo.customerservice.controller;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;

import org.springframework.stereotype.Component;

import com.spe.demo.customerservice.domain.model.Customer;
import com.spe.demo.customerservice.domain.repository.CustomerRepository;

@Component
public class CustomerAssembler extends ResourceAssemblerSupport<Customer, CustomerDTO> {
	private RepositoryEntityLinks entityLinks;

	public CustomerAssembler(RepositoryEntityLinks entityLinks) {
		super(CustomerController.class, CustomerDTO.class);
		this.entityLinks = entityLinks;
	}

	@Override
	public CustomerDTO toResource(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO(customer.getName(), customer.getCredit(), customer.getPhone());
		
		ControllerLinkBuilder getCustomerByIdLink = linkTo(methodOn(CustomerController.class).getCustomerByID(customer.getCustomerId()));
		customerDto.add(getCustomerByIdLink.withSelfRel());
		
		/*ControllerLinkBuilder getAllCustomerLink = linkTo(methodOn(CustomerController.class).getAllCustomers());
		customerDto.add(getAllCustomerLink.withRel("All"));*/
		
		Link orderlink = entityLinks.linkToSingleResource(CustomerRepository.class, customer.getCustomerId());
		customerDto.add(orderlink.withRel("customer"));
		
		return customerDto;
	}
}
