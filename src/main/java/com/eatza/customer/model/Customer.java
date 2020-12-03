package com.eatza.customer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long customerId;
	String customerName;
	int age;
	private String status;


	public Customer(String customerName, int age, String status) {
		super();
		this.customerName = customerName;
		this.age = age;
		this.status = status;
	}

	public Customer(long customerId, String customerName, int age, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.age = age;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age + ", status="
				+ status + "]";
	}



}
