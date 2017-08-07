package com.citi.swifttrading.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.domain.Trade222;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TradeServiceImplTest {
	@Autowired
	TradeServiceImpl tradeServiceImpl;

	Date date = new Date();

	Trade222 trade;

	List<Trade222> trades;

	@Test
	public void testSave() {
		trade = new Trade222("ABT", 1000, 10.5, date, date, date, 3, 9.5, 10.4, "B", "USD", "Market", "Long", 11.5);
		tradeServiceImpl.save(trade);
		trade = new Trade222("ABT", 1000, 10.5, date, date, date, 3, 9.5, 10.4, "B", "USD", "Market", "Long", 11.5);
		tradeServiceImpl.save(trade);
	}

	@Test
	public void testQueryById() {
		trade = tradeServiceImpl.queryById(21);
		System.out.println(trade);
		assertEquals("Market", trade.getOderType());
		assertEquals("ABT", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(3, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(), 0);
		assertEquals(11.5, trade.getSalePrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(10.4, trade.getProfit_price(), 0);
		assertEquals("Long", trade.getPosition());
		assertEquals("B", trade.getOperationType());
		assertEquals("USD", trade.getCcy());
	}

	@Test
	public void testUpdate() {
		trade = tradeServiceImpl.queryById(22);
		System.out.println(trade);
		trade.setCcy("CNY");
		tradeServiceImpl.update(trade);
		trade = tradeServiceImpl.queryById(22);
		assertEquals("Market", trade.getOderType());
		assertEquals("ABT", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(3, trade.getStatus());
		assertEquals(10.5, trade.getBuyPrice(), 0);
		assertEquals(11.5, trade.getSalePrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(10.4, trade.getProfit_price(), 0);
		assertEquals("Long", trade.getPosition());
		assertEquals("B", trade.getOperationType());
		assertEquals("CNY", trade.getCcy());
	}

	@Test
	public void testgetAll() {
		trades = tradeServiceImpl.queryAll();
		System.out.println(trades.get(0).toString());
		assertNotNull(trades);
		assertEquals(7, trades.size());
	}

	@Test
	public void testDeletel() {
		tradeServiceImpl.delete(19);
	}

}
