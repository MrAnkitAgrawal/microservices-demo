package com.spe.demo.customerservice.controller.integrationtests;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.spe.demo.customerservice.CustomerServiceApplication;
import com.spe.demo.customerservice.domain.model.Customer;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {
	@LocalServerPort
	private int port;
	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	@Test
	public void itGetAllCustomers() throws Exception {
		Customer customer1 = new Customer(1, "Ankit Agrawal", "20000", "Gomti Nager, Lucknow", "ankit29.a@tcs.com", "7800619190");
		Customer customer2 = new Customer(2, "Shr", "5000", "Gomti Nager, Lucknow", "ankit29.a@tcs.com", "7800619190");
		Customer customer3 = new Customer(3, "Eka", "5000", "Gomti Nager, Lucknow", "ankit29.a@tcs.com", "7800619190");
		Customer customer4 = new Customer(4, "Ravi", "2000", "Gomti Nager, Lucknow", "ravi.a@tcs.com", "8800619190");
		Customer customer5 = new Customer(5, "Ravi", "2000", "Gomti Nager, Lucknow", "ravi.a@tcs.com", "9839391636");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		customerList.add(customer4);
		customerList.add(customer5);
		
		String expected = new ObjectMapper().writeValueAsString(customerList);
		
		String uri = "/customers";
		
		ResponseEntity<String> response = testRestTemplate.getForEntity(createURL(port, uri), String.class);
		String actual = response.getBody();
		
		JSONAssert.assertEquals(expected, actual, false);
	}
	
	private String createURL(int port, String uri) {
		return "http://localhost:" + port + uri;
	}
}
