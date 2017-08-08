package com.citi.swifttrading.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;

	public User() {
		super();
	}

	public User(String name, String email, String phoneNumber, String address) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

}
