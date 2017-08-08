package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;


import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

public class TradeTest {
	
	Trade trade;
	
	LocalDateTime time = LocalDateTime.now();
	
	@Before
	public void setUp() {
		trade = new Trade(TradeType.MARKET,new Security("This is Security Name", "Short Name"), 100, new Date(),15, 0.05, 0.05, Position.LONG, 10);
	}

//	TODO
	
//	@Test
//	public void testUserGet() {
//		assertEquals("Short Name", trade.getCode());
//		assertEquals(100, trade.getQuantity());
//	}
//	
//	@Test
//	public void testUserSet() {
//		trade.setCode("ISIN654321");
//		trade.setQuantity(2000);
//		trade.setStatus(TradeStatus.OPEN);
//		trade.setPrice(15.7);
//		trade.setLoss_price(10.7);
//		trade.setProfit_price(15.7);
//		trade.setType(TradeType.MARKET);
//		
//		assertEquals("ISIN654321", trade.getCode());
//		assertEquals(2000, trade.getQuantity());
//		assertEquals(time.plusDays(5), trade.getExpiration());
//		assertEquals(time.plusDays(1), trade.getStart_time());
//		assertEquals(time.plusDays(2), trade.getEnd_time());
//		assertEquals(1, trade.getStatus());
//		assertEquals(0, 15.7, trade.getPrice());
//		assertEquals(0, 10.7, trade.getLoss_price());
//		assertEquals(0, 15.7, trade.getProfit_price());
//		assertEquals("A", trade.getType());
//	}

}
