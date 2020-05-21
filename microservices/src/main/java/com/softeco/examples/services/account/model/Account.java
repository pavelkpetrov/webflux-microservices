package com.softeco.examples.services.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Document
public class Account {

	@Id
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String customerId;

	public Account() {
	}

}
