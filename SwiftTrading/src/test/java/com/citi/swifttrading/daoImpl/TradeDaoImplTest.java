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
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CANCLED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CANCLED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CLOSED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CLOSED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CREATED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.CREATED);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.OPEN);
		tradeDaoImpl.save(trade);
		trade = new Trade(TradeType.LIMIT, security, 10000, start_time, expiration, 9.5, 11.5, Position.LONG, 10.5);
		trade.setStatus(TradeStatus.OPEN);
		tradeDaoImpl.save(trade);
	}
	
	@Test
	public void testQueryByStatus() {
		trades = tradeDaoImpl.queryByStarus(TradeStatus.CANCLED);
		assertEquals(2, trades.size());
		trades = tradeDaoImpl.queryByStarus(TradeStatus.CREATED);
		assertEquals(2, trades.size());
		trades = tradeDaoImpl.queryByStarus(TradeStatus.OPEN);
		assertEquals(2, trades.size());
		trades = tradeDaoImpl.queryByStarus(TradeStatus.CLOSED);
		assertEquals(2, trades.size());
	}

	@Test
	public void testQueryById() {
		trade = tradeDaoImpl.queryById(31);
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
		trade = tradeDaoImpl.queryById(32);
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
		tradeDaoImpl.update(trade);
		trade = tradeDaoImpl.queryById(32);
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
		trades = tradeDaoImpl.queryAll();
		System.out.println(trades.get(0).toString());
		assertNotNull(trades);
		assertEquals(5, trades.size());
	}

	@Test
	public void testDelete() {
		tradeDaoImpl.delete(30);
	}

}
