package com.softeco.examples.services.customer.model;

import com.softeco.examples.services.account.model.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Enumeration;

@Getter
@Setter
@ToString
@Document
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	@Transient
	private Enumeration<Account> accounts;

	public Customer() {

	}

	public Customer(Enumeration<Account> accounts) {
		this.accounts = accounts;
	}

	public Customer(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

}
