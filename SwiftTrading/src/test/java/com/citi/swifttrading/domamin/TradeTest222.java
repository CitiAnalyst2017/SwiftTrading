package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Trade222;

public class TradeTest222 {
	
	Trade222 trade;
	
	Date date = new Date();
	
	@Before
	public void setUp() {
		trade = new Trade222("CNG", 1000, 10.5, date, date, date, 3, 9.5, 10.4,"B","USD", "Market", "Long", 11.5);
	}

	@Test
	public void testUserGet() {
		assertEquals("Market", trade.getOderType());
		assertEquals("CNG", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(3, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(),0);
		assertEquals(11.5, trade.getSalePrice(),0);
		assertEquals(9.5, trade.getLoss_price(),0);
		assertEquals(10.4, trade.getProfit_price(),0);
		assertEquals("Long", trade.getPosition());
		assertEquals("B", trade.getOperationType());
		assertEquals("USD", trade.getCcy());
	}
	
	@Test
	public void testUserSet() {
		trade.setOderType("Limit");
		trade.setCode("ABBS");
		trade.setQuantity(2000);
		trade.setStatus(1);
		trade.setBuyPrice(10.2);
		trade.setSalePrice(11.2);
		trade.setLoss_price(9.2);
		trade.setProfit_price(10.1);
		trade.setPosition("Short");
		trade.setOperationType("S");
		trade.setCcy("CNY");
		
		assertEquals("Limit", trade.getOderType());
		assertEquals("ABBS", trade.getCode());
		assertEquals(2000, trade.getQuantity());
		assertEquals(1, trade.getStatus());
		assertEquals(0, 10.2, trade.getBuyPrice());
		assertEquals(0, 11.2, trade.getSalePrice());
		assertEquals(0, 9.2, trade.getLoss_price());
		assertEquals(0, 10.1, trade.getProfit_price());
		assertEquals("Short", trade.getPosition());
		assertEquals("S", trade.getOperationType());
		assertEquals("CNY", trade.getCcy());
	}

}
