package com.eatza.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequestDto {

	
	private String customerName;
	private int age;
	public CustomerRequestDto(String customerName, int age) {
		super();
		this.customerName = customerName;
		this.age = age;
	}
	@Override
	public String toString() {
		return "CustomerRequestDto [customerName=" + customerName + ", age=" + age + "]";
	}

	


}
