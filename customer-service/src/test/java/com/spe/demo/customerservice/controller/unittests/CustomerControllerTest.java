package com.spe.demo.customerservice.controller.unittests;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spe.demo.customerservice.controller.CustomerController;
import com.spe.demo.customerservice.domain.model.Customer;
import com.spe.demo.customerservice.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void testGetAllCustomers() throws Exception {
		/*Customer customer1 = new Customer(1, "Ankit", "20000", "Lucknow", "ankit29.a@tcs.com", "7800619190");
		Customer customer2 = new Customer(2, "Shraddha", "30000", "Lucknow", "shr@tcs.com", "9452826881");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);

		when(customerService.getAllCustomer()).thenReturn(customerList);

		RequestBuilder requestBuilder = get("/customers").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
		
		String expetced = new ObjectMapper().writeValueAsString(customerList);
		String actual = result.getResponse().getContentAsString();

		JSONAssert.assertEquals(expetced, actual, false);*/
	}
}
