package com.citi.swifttrading.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Strategy;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TradeDaoImplTest {

	@Autowired
	TradeDaoImpl tradeDaoImpl;

	Date start_time = new Date();
	Date expiration;
	Calendar c = Calendar.getInstance();

	Trade trade;

	List<Trade> trades;

	@Autowired
	SecurityDaoImpl securityDaoImpl;

	Security security;

	@Before
	public void setUp() {
		security = securityDaoImpl.queryById("A");
		this.setTime();
	}

	private void setTime() {
		c.setTime(start_time);
		c.add(Calendar.MINUTE, 15);
		expiration = c.getTime();
	}

	@Test
	public void testSave() {
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		tradeDaoImpl.save(trade);
	}

	@Test
	public void testQueryById() {
		trade = tradeDaoImpl.queryById(10);
		System.out.println(trade.toString());
		assertEquals(TradeType.LIMIT, trade.getType());
		assertEquals("A", trade.getSecurity().getNameAbbreviation());
		assertEquals(10000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getPrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
	}

	@Test
	public void testUpdate() {
		trade = tradeDaoImpl.queryById(6);
		trade.setLoss_price(9.8);
		tradeDaoImpl.update(trade);
		trade = tradeDaoImpl.queryById(6);
		assertEquals(TradeType.LIMIT, trade.getType());
		assertEquals("A", trade.getSecurity().getNameAbbreviation());
		assertEquals(10000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getPrice(), 0);
		assertEquals(9.8, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
	}

	@Test
	public void testgetAll() {
		trades = tradeDaoImpl.queryAll();
		assertNotNull(trades);
		assertEquals(1, trades.size());
	}

	@Test
	public void testDelete() {
		tradeDaoImpl.delete(5);
	}

}
