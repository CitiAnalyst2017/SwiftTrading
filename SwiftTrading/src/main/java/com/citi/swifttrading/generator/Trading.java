package com.citi.swifttrading.generator;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trading implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Symbol;
    private String Bide;
    private double Price;
    private int Qty;
	public Trading() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Trading [Symbol=" + Symbol + ", Bide=" + Bide + ", Price=" + Price + ", Qty=" + Qty + "]";
	}
	public Trading(String symbol, String bide, double price, int qty) {
		super();
		Symbol = symbol;
		Bide = bide;
		Price = price;
		Qty = qty;
	}
	

}
