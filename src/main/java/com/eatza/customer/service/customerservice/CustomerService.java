package com.eatza.customer.service.customerservice;

import org.springframework.stereotype.Service;

import com.eatza.customer.dto.CustomerFetchDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;

@Service
public interface CustomerService {

	Customer saveCustomer(CustomerRequestDto customerRequestDto);

	CustomerFetchDto updateCustomer(CustomerRequestDto customerRequestDto, long customerId) throws CustomerException;

	boolean deactivateCustomer(long customerId) throws CustomerException;

	CustomerFetchDto getCustomer(long customerId) throws CustomerException;

}
