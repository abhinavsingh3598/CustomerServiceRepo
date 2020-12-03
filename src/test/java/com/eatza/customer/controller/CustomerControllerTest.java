package com.eatza.customer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.customer.dto.CustomerFetchDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.model.Customer;
import com.eatza.customer.service.customerservice.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CustomerService customerService;

	@Autowired
	private ObjectMapper objectMapper;

	CustomerRequestDto customerRequestDto;
	

	@Before
	public void setUp() throws Exception {
		customerRequestDto = new CustomerRequestDto("abhinav", 23);
	}

	@Test
	public void testAddCustomer() throws Exception {  
		CustomerRequestDto customerRequestDto = new CustomerRequestDto("abhinav", 23);
		when(customerService.saveCustomer(customerRequestDto)).thenReturn(new Customer());
		RequestBuilder request = MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((customerRequestDto)));

		mockMvc.perform(request).andExpect(status().is(200)).andExpect(content().string("Customer Added successfully"))
				.andReturn();

	}

	@Test
	public void testUpdateCustomer() throws Exception {

		when(customerService.updateCustomer(customerRequestDto, 1L))
				.thenReturn(new CustomerFetchDto(1L, "abhinav", 23, "update"));

		RequestBuilder request = MockMvcRequestBuilders.put("/customer/details/1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString((customerRequestDto)));

		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

	@Test
	public void testDeactivateCustomer() throws Exception {

		when(customerService.deactivateCustomer(1L)).thenReturn(true);

		RequestBuilder request = MockMvcRequestBuilders.put("/customer/deactivate/1")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

	@Test
	public void testGetCustomer() throws Exception {

		when(customerService.getCustomer(1L)).thenReturn(new CustomerFetchDto(1L, "abhinav", 23, "update"));

		RequestBuilder request = MockMvcRequestBuilders.get("/customer/1").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

}
