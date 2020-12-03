package com.eatza.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerUpdateDto {



	private String customerName;
	private int age;
	private String status;

	
	public CustomerUpdateDto(String customerName, int age, String status) {
		super();
		
		this.customerName = customerName;
		this.age = age;
		this.status = status;
	}


	@Override
	public String toString() {
		return "CustomerUpdateDto [customerName=" + customerName + ", age=" + age + ", status=" + status + "]";
	}
	
	
	
}
