package com.citi.swifttrading.domain;

import java.io.Serializable;

public class Security implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ISIN;
	private String securityName;
	private String nameAbbreviation;
	private boolean isFavorite;

	public Security() {
		super();
	}

	public Security(String iSIN, String securityName, String nameAbbreviation, boolean isFavorite) {
		super();
		ISIN = iSIN;
		this.securityName = securityName;
		this.nameAbbreviation = nameAbbreviation;
		this.isFavorite = isFavorite;
	}

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getNameAbbreviation() {
		return nameAbbreviation;
	}

	public void setNameAbbreviation(String nameAbbreviation) {
		this.nameAbbreviation = nameAbbreviation;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public String toString() {
		return "Security [ISIN=" + ISIN + ", securityName=" + securityName + ", nameAbbreviation=" + nameAbbreviation
				+ ", isFavorite=" + isFavorite + "]";
	}
}
