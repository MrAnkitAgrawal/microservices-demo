package com.spe.demo.customerservice.controller;

import org.springframework.hateoas.ResourceSupport;

public class CustomerDTO extends ResourceSupport {
	private String name;
	private String credit;
	private String phone;
	
	public CustomerDTO(String name, String credit, String phone) {
		this.name = name;
		this.credit = credit;
		this.phone = phone;
	}

	protected CustomerDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
