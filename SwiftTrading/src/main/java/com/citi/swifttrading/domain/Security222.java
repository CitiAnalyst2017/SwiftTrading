package com.citi.swifttrading.domain;

import java.io.Serializable;


import lombok.Data;

@Data
public class Security222 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String securityName;
	private String nameAbbreviation;

	public Security222() {
		super();
	}

	public Security222(String securityName, String nameAbbreviation) {
		super();
		this.securityName = securityName;
		this.nameAbbreviation = nameAbbreviation;
	}
}
