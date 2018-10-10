package com.spe.demo.orderservice.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spe.demo.orderservice.domain.model.Catalog;
import com.spe.demo.orderservice.domain.model.Customer;
import com.spe.demo.orderservice.domain.model.Order;
import com.spe.demo.orderservice.domain.model.OrderStatus;
import com.spe.demo.orderservice.domain.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	RestTemplate restTemplate;
	ObjectMapper objectMapper = new ObjectMapper();

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getOrdersByCustomerId(final Integer customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	public Order getOrderById(final Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		return order.get();
	}

	public Order createOrder(final int customerId, final int itemCode, final int units)
			throws JsonParseException, JsonMappingException, IOException {
		Customer customer = getCustomerDetails(customerId);
		Catalog catalog = getCatalogDetails(itemCode, units);
		
		double amoutToPay = catalog.getPrice() * units;
		double customerBalance = Double.valueOf(customer.getCredit());
		
		if(amoutToPay > customerBalance) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		// update item units & customer credit
		catalog.setInventory(catalog.getInventory() - units);
		customer.setCredit(Double.toString(customerBalance - amoutToPay));
		
		saveCustomerAndCatalogDetails(customer, catalog);
		
		// place order
		LocalDateTime currentTime = LocalDateTime.now();
		Order order = new Order(customerId, itemCode, units, currentTime, OrderStatus.CONFIRMED);
		
		return orderRepository.save(order);
	}

	private void saveCustomerAndCatalogDetails(Customer customer, Catalog catalog) {
		// Updated Catalog 
		final String updateCatalogItemByIdURI = "http://localhost:8080/catalog/" + catalog.getItemCode();
		HttpEntity<Catalog> catalogEntity = new HttpEntity<Catalog>(catalog);

		ResponseEntity<String> response = restTemplate.exchange(updateCatalogItemByIdURI, HttpMethod.PUT, catalogEntity,
				String.class);
		boolean doesItemUpdated = !(response.getStatusCode() == null
				|| response.getStatusCode() != HttpStatus.OK);
		if (!doesItemUpdated) {
			throw new RuntimeException("Error during catalog update!");
		}

		// Update Customer's Credit
		final String updateCustomerURI = "http://localhost:9090/customers/" + customer.getCustomerId();
		HttpEntity<Customer> customerEntity = new HttpEntity<Customer>(customer);
		
		ResponseEntity<String> customerUpdateResponse = restTemplate.exchange(updateCustomerURI, HttpMethod.PUT, customerEntity,
				String.class);
		boolean doesCustomerUpdated = !(customerUpdateResponse.getStatusCode() == null
				|| customerUpdateResponse.getStatusCode() != HttpStatus.NO_CONTENT);
		if (!doesCustomerUpdated) {
			throw new RuntimeException("Error during customer update!");
		}
	}

	private Catalog getCatalogDetails(final int itemCode, final int units) {
		final String getCatalogItemByIdURI = "http://localhost:8080/catalog/" + itemCode;
		ResponseEntity<String> response = restTemplate.exchange(getCatalogItemByIdURI, HttpMethod.GET, null,
				String.class);
		boolean doesItemExist = !(response.getStatusCode() == null
				|| response.getStatusCode() != HttpStatus.OK);
		if (!doesItemExist) {
			throw new RuntimeException("Item doesn't exist!");
		}

		String responseBody = response.getBody();
		Catalog catalog;
		try {
			JSONObject object = new JSONObject(responseBody);
			catalog = new Catalog(itemCode, object.getString("itemName"), object.getString("description"), 
					(float) object.getDouble("price"), object.getInt("inventory"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Item doesn't exist!");
		}

		int availbaleInventory = catalog.getInventory();
		if((availbaleInventory - units) < 0){
			throw new RuntimeException("Insufficient units!");
		}

		return catalog;
	}

	private Customer getCustomerDetails(final int customerId) {
		final String customerEndpoint = "http://localhost:9090/customers/" + customerId;
		ResponseEntity<String> customerResponse = restTemplate.exchange(customerEndpoint, HttpMethod.GET, null,
				String.class);

		// Check if customer is valid
		boolean isCustomerValid = !(customerResponse.getStatusCode() == null
				|| customerResponse.getStatusCode() != HttpStatus.OK);
		if (!isCustomerValid)
			throw new RuntimeException("Invalid Customer!");

		final String responseBody = customerResponse.getBody();
		Customer customer;
		try {
			customer = objectMapper.readValue(responseBody, Customer.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid Customer!");
		}
		return customer;
	}
}