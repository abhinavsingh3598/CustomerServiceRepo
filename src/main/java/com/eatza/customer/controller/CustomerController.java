package com.eatza.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.customer.dto.CustomerFetchDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.service.customerservice.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private static final String RETBACKOBJ="Returning back the object";

	@PostMapping("/customer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
		logger.debug("In add customers method, calling service");
		customerService.saveCustomer(customerRequestDto);
		logger.debug("Restaurant saved, returning back");
		return ResponseEntity.status(HttpStatus.OK).body("Customer Added successfully");

	}

	@PutMapping("/customer/details/{customerId}")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerRequestDto customerRequestDto,
			@PathVariable long customerId) throws CustomerException {

		logger.debug(" In updateCustomer method, calling service");
		customerService.updateCustomer(customerRequestDto, customerId);
		logger.debug(RETBACKOBJ);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Customer updated successfully for customer with id " + customerId);

	}

	@PutMapping("/customer/deactivate/{customerId}")
	public ResponseEntity<String> deactivateCustomer(@PathVariable long customerId) throws CustomerException {
		logger.debug(" In deactivateCustomer method, calling service");
		boolean updatedResponse = customerService.deactivateCustomer(customerId);
		logger.debug("Deactivation status :" + updatedResponse);
		logger.debug(RETBACKOBJ);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Customer with id " + customerId + " is deactivated successfully");

	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerFetchDto> getCustomer(@PathVariable long customerId) throws CustomerException {
		logger.debug(" In getCustomer method, calling service");
		CustomerFetchDto customerFetchDto = customerService.getCustomer(customerId);
		logger.debug(RETBACKOBJ);
		return ResponseEntity.status(HttpStatus.OK).body(customerFetchDto);
	}
}
