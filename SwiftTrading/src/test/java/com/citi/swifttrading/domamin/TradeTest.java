package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Trade;

public class TradeTest {
	
	Trade trade;
	
	LocalDateTime time = LocalDateTime.now();
	
	@Before
	public void setUp() {
		trade = new Trade("ISIN123456", 1000, time.plusDays(2), time, time.plusDays(1),
				3, 16, 10.5, 15.5, "M");
	}

	@Test
	public void testUserGet() {
		assertEquals("ISIN123456", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(time.plusDays(2), trade.getExpiration());
		assertEquals(time, trade.getStart_time());
		assertEquals(time.plusDays(1), trade.getEnd_time());
		assertEquals(3, trade.getStatus());
		assertEquals(0, 16, trade.getPrice());
		assertEquals(0, 10.5, trade.getLoss_price());
		assertEquals(0, 15.5, trade.getProfit_price());
		assertEquals("M", trade.getType());
	}
	
	@Test
	public void testUserSet() {
		trade.setCode("ISIN654321");
		trade.setQuantity(2000);
		trade.setExpiration(time.plusDays(5));
		trade.setStart_time(time.plusDays(1));
		trade.setEnd_time(time.plusDays(2));
		trade.setStatus(1);
		trade.setPrice(15.7);
		trade.setLoss_price(10.7);
		trade.setProfit_price(15.7);
		trade.setType("A");
		
		assertEquals("ISIN654321", trade.getCode());
		assertEquals(2000, trade.getQuantity());
		assertEquals(time.plusDays(5), trade.getExpiration());
		assertEquals(time.plusDays(1), trade.getStart_time());
		assertEquals(time.plusDays(2), trade.getEnd_time());
		assertEquals(1, trade.getStatus());
		assertEquals(0, 15.7, trade.getPrice());
		assertEquals(0, 10.7, trade.getLoss_price());
		assertEquals(0, 15.7, trade.getProfit_price());
		assertEquals("A", trade.getType());
	}

}
