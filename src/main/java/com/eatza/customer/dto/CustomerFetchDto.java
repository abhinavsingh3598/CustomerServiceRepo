package com.eatza.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerFetchDto {

	private long customerId;
	private String customerName;
	private int age;
	private String status;

	public CustomerFetchDto(long customerId, String customerName, int age) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.age = age;
	
	}

	public CustomerFetchDto(long customerId, String customerName, int age, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.age = age;
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerFetchDto [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age
				+ ", status=" + status + "]";
	}
	
	

}
