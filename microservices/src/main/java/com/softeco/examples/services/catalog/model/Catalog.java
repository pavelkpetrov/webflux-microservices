package com.softeco.examples.services.catalog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
public class Catalog {

	@Id
	private String id;
	private String name;
	private double price;
	private int count;

	public Catalog() {
	}
	
	public Catalog(String name) {
		this.name = name;
	}

}
