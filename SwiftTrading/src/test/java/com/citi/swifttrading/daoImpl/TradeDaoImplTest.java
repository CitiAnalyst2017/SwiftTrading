package com.citi.swifttrading.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TradeDaoImplTest {

	@Autowired
	TradeDaoImpl tradeDaoImpl;

	Date date = new Date();

	Trade trade;

	List<Trade> trades;

	@Test
	public void testSave() {
		trade = new Trade("A", 1000, 15, date, date, TradeStatus.CREATED, 10.5, 9.5, 11.5,
				TradeType.MARKET, new Security("Agilent Technologies", "A"), Position.LONG, "USD", "B", 0);
		tradeDaoImpl.save(trade);
		trade = new Trade("A", 1000, 15, date, date, TradeStatus.CREATED, 10.5, 9.5, 11.5,
				TradeType.MARKET, new Security("Agilent Technologies", "A"), Position.LONG, "USD", "B", 0);
		tradeDaoImpl.save(trade);
	}

	@Test
	public void testQueryById() {
		trade = tradeDaoImpl.queryById(2);
		System.out.println(trade);
		assertEquals(TradeType.MARKET, trade.getType());
		assertEquals("A", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getPrice(), 0);
		assertEquals(0, trade.getSalePrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
		assertEquals("B", trade.getOderType());
		assertEquals("USD", trade.getCcy());
	}

	@Test
	public void testUpdate() {
		trade = tradeDaoImpl.queryById(2);
		System.out.println(trade);
		trade.setCcy("CNY");
		tradeDaoImpl.update(trade);
		assertEquals(TradeType.MARKET, trade.getType());
		assertEquals("A", trade.getCode());
		assertEquals(1000, trade.getQuantity());
		assertEquals(TradeStatus.CREATED, trade.getStatus());
		assertEquals(10.5, trade.getPrice(), 0);
		assertEquals(0, trade.getSalePrice(), 0);
		assertEquals(9.5, trade.getLoss_price(), 0);
		assertEquals(11.5, trade.getProfit_price(), 0);
		assertEquals(Position.LONG, trade.getPosition());
		assertEquals("B", trade.getOderType());
		assertEquals("CNY", trade.getCcy());
	}

	@Test
	public void testgetAll() {
		trades = tradeDaoImpl.queryAll();
		System.out.println(trades.get(0).toString());
		assertNotNull(trades);
		assertEquals(2, trades.size());
	}

	@Test
	public void testDelete() {
		tradeDaoImpl.delete(3);
	}

}
