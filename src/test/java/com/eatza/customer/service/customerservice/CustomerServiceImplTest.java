package com.eatza.customer.service.customerservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testSaveCustomer() {

		CustomerRequestDto customerRequestDto = new CustomerRequestDto("abhinav", 1);

		when(customerRepository.save(any(Customer.class))).thenReturn(new Customer(1, "abhinav", 10, "Registered"));

		assertNotNull(customerServiceImpl.saveCustomer(customerRequestDto));

	}

	@Test()
	public void testUpdateCustomer() throws CustomerException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto("abhinav", 1);

		Customer customer = new Customer(1, "abhinav", 10, "Registered");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(customer);

		assertNotNull(customerServiceImpl.updateCustomer(customerRequestDto, 1L));

	}

	@Test(expected = CustomerException.class)
	public void testUpdateCustomerForException() throws CustomerException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto("abhinav", 1);
		Customer customer = new Customer(1, "abhinav", 10, "Registered");
		customerServiceImpl.updateCustomer(customerRequestDto, 1L);
	}

	@Test
	public void testDeactivateCustomer() throws CustomerException {

		Customer customer = new Customer(1, "abhinav", 10, "Registered");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		assertTrue(customerServiceImpl.deactivateCustomer(1));

	}

	@Test(expected = CustomerException.class)
	public void testDeactivateCustomerException() throws CustomerException {

		Customer customer = new Customer(1, "abhinav", 10, "Registered");

		// when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		customerServiceImpl.deactivateCustomer(1L);

	}

	@Test
	public void testGetCustomer() throws CustomerException {

		Customer customer = new Customer(1, "abhinav", 10, "Registered");

		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

		assertNotNull(customerServiceImpl.getCustomer(1));

	}

	@Test(expected = CustomerException.class)
	public void testGetCustomerException() throws CustomerException {

		Customer customer = new Customer(1, "abhinav", 10, "Registered");

//        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		customerServiceImpl.getCustomer(1L);

	}

}