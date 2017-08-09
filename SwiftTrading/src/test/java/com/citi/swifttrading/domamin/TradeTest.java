package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Calendar;
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

	Date start_time = new Date();
	Date expiration;
	Calendar c = Calendar.getInstance();

	@Before
	public void setUp() {
		this.setTime();
		trade = new Trade(TradeType.LIMIT, new Security("This is Security Name", "Short Name"), 1200, start_time,
				expiration, 9.5, 11.5, Position.LONG, 10.5);
	}

	private void setTime() {
		c.setTime(start_time);
		c.add(Calendar.MINUTE, 15);
		expiration = c.getTime();
	}

	@Test
	public void testTradeGet() {
		assertEquals("Short Name", trade.getSecurity().getNameAbbreviation());
		assertEquals(1200, trade.getQuantity());
	}

	@Test
	public void testTradeSet() {
		trade.getSecurity().setNameAbbreviation("ISIN654321");
		trade.setQuantity(2000);
		trade.setStatus(TradeStatus.OPEN);
		trade.setBuyPrice(15.7);
		trade.setLoss_price(10.7);
		trade.setProfit_price(15.7);
		trade.setType(TradeType.MARKET);

		assertEquals("ISIN654321", trade.getSecurity().getNameAbbreviation());
		assertEquals(2000, trade.getQuantity());
		assertEquals(TradeStatus.OPEN, trade.getStatus());
		assertEquals(0, 15.7, trade.getBuyPrice());
		assertEquals(0, 10.7, trade.getLoss_price());
		assertEquals(0, 15.7, trade.getProfit_price());
		assertEquals(TradeType.MARKET, trade.getType());
	}

}
