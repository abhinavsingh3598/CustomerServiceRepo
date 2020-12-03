package com.eatza.customer.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CustomerUpdateDtoTest {

	CustomerUpdateDto customerUpdateDto;

	@Before
	public void setUp() throws Exception {
		customerUpdateDto = new CustomerUpdateDto();
	}

	@Test
	public void testGetCustomerName() {
		customerUpdateDto.setCustomerName("abhinav");
		assertNotNull(customerUpdateDto.getCustomerName());
	}

	@Test
	public void testGetAge() {
		customerUpdateDto.setAge(12);
		assertNotNull(customerUpdateDto.getAge());
	}

	@Test
	public void testGetStatus() {
		customerUpdateDto.setStatus("shsh");
		assertNotNull(customerUpdateDto.getStatus());
	}

}
