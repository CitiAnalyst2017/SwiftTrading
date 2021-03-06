package com.citi.swifttrading.serviceImpl;

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

import com.citi.swifttrading.daoImpl.SecurityDaoImpl;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TradeServiceImplTest {
	@Autowired
	TradeServiceImpl tradeServiceImpl;

	@Autowired
	SecurityDaoImpl securityDaoImpl;

	Date start_time = new Date();
	Date expiration;
	Calendar c = Calendar.getInstance();

	Trade trade;

	List<Trade> trades;

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
		trade = new Trade(TradeType.LIMIT, security, 1200, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CREATED);
		System.out.println(tradeServiceImpl.save(trade));
		trade = new Trade(TradeType.LIMIT, security, 2000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CREATED);
		System.out.println(tradeServiceImpl.save(trade));
	}

	@Test
	public void testQueryByStatus() {
		trades = tradeServiceImpl.queryByStatus(TradeStatus.CANCLED);
		System.out.println(trades.get(0).getStatus());
		assertEquals(4, trades.size());
		trades = tradeServiceImpl.queryByStatus(TradeStatus.CREATED);
		System.out.println(trades.get(0).getStatus());
		assertEquals(5, trades.size());
		trades = tradeServiceImpl.queryByStatus(TradeStatus.OPEN);
		System.out.println(trades.get(0).getStatus());
		assertEquals(5, trades.size());
		trades = tradeServiceImpl.queryByStatus(TradeStatus.CLOSED);
		System.out.println(trades.get(0).getStatus());
		assertEquals(4, trades.size());
	}

	@Test
	public void testQueryById() {
		trade = tradeServiceImpl.queryById(33);
		System.out.println(trade.getSecurity().toString());
		assertEquals(TradeType.LIMIT, trade.getType());
		assertEquals("A", trade.getSecurity().getNameAbbreviation());
		assertEquals(10000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
	}

	@Test
	public void testUpdate() {
		trade = tradeServiceImpl.queryById(34);
		System.out.println(trade.getSecurity().toString());
		assertEquals(TradeType.LIMIT, trade.getType());
		assertEquals("A", trade.getSecurity().getNameAbbreviation());
		assertEquals(10000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());

		security = trade.getSecurity();
		security.setNameAbbreviation("ABT");
		trade.setSecurity(security);
		tradeServiceImpl.update(trade);
		trade = tradeServiceImpl.queryById(34);
		System.out.println(trade.getSecurity().toString());
		assertEquals(TradeType.LIMIT, trade.getType());
		assertEquals("ABT", trade.getSecurity().getNameAbbreviation());
		assertEquals(10000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
	}

	@Test
	public void testgetAll() {
		trades = tradeServiceImpl.queryAll();
		System.out.println(trades.get(0).toString());
		assertNotNull(trades);
		assertEquals(1, trades.size());
	}

	@Test
	public void testDelete() {
		tradeServiceImpl.delete(33);
	}

}
