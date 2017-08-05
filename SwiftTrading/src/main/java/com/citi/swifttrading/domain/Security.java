package com.citi.swifttrading.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
public class Security implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String ISIN;
	protected LocalDateTime time;
	
	public Security() {}
	
	public Security(String iSIN, LocalDateTime time) {
		super();
		ISIN = iSIN;
		this.time = time;
	}

	


	

}
