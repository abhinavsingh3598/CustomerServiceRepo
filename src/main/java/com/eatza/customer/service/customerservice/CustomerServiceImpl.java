package com.eatza.customer.service.customerservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.customer.dto.CustomerFetchDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;
	
	private static final String CUSTNOTPRESENT="Customer with this ID is not Present";
    
	@Override
	public Customer saveCustomer(CustomerRequestDto customerRequestDto) {
		logger.debug("In saveCustomer, creating object of customer to save");
		Customer customer = new Customer(customerRequestDto.getCustomerName(), customerRequestDto.getAge(),
				"Registered");
		logger.debug("calling repository save customer method");		
		return customerRepository.save(customer);
	}

	@Override
	public CustomerFetchDto updateCustomer(CustomerRequestDto customerRequestDto, long customerId)
			throws CustomerException {
		logger.debug("In updateCustomer, update details  of customer");
		Optional<Customer> previouslyPersistedCustomer = customerRepository.findById(customerId);
		if (!previouslyPersistedCustomer.isPresent()) {
			throw new CustomerException(CUSTNOTPRESENT);
		}
		Customer customer = new Customer(customerId, customerRequestDto.getCustomerName(), customerRequestDto.getAge(),
				"Updated");
		logger.debug("calling repository updating customer method");
		Customer savedCustomer = customerRepository.saveAndFlush(customer);
		logger.debug("returning the updated Customer");
		return new CustomerFetchDto(savedCustomer.getCustomerId(), savedCustomer.getCustomerName(),
				savedCustomer.getAge(), savedCustomer.getStatus());
	}

	@Override
	public boolean deactivateCustomer(long customerId) throws CustomerException {
		logger.debug("In deactivate customer service method, calling repository");
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			logger.debug("Customer was found in db");
			customer.get().setStatus("Deactivated");
			customerRepository.save(customer.get());
			return true;
		} else {
			logger.debug("Customer not found");
			throw new CustomerException(CUSTNOTPRESENT);
		}

	}  

	@Override
	public CustomerFetchDto getCustomer(long customerId) throws CustomerException {
		logger.debug("In get customer service method, calling repository");
		if (!customerRepository.findById(customerId).isPresent()) {
			throw new CustomerException(CUSTNOTPRESENT);
		}
		Customer customer = customerRepository.findById(customerId).get();

		logger.debug("In get customer service method, called repository");
		return new CustomerFetchDto(customer.getCustomerId(), customer.getCustomerName(), customer.getAge(),
				customer.getStatus());

	}

}
