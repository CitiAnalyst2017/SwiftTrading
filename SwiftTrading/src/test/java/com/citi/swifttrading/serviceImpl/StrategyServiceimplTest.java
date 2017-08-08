package com.citi.swifttrading.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.daoImpl.SecurityDaoImpl;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Strategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class StrategyServiceimplTest {

	@Autowired
	StrategyServiceImpl strategyServiceImpl;

	@Autowired
	SecurityDaoImpl securityDaoImpl;

	Strategy strategy;
	
	Security security;

	List<Strategy> strategies;
	
	@Before
	public void setUp() {
		security = securityDaoImpl.queryById("A");
	}

	@Test
	public void testSave() {
		strategy = new Strategy("strategy1", "strategy 1 desc", security, 0.2, 1);
		strategyServiceImpl.save(strategy);
		strategy = new Strategy("strategy1", "strategy 1 desc", security, 0.2, 1);
		strategyServiceImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyServiceImpl.queryById(1);
		System.out.println(strategy.toString());
		assertEquals("Name1", strategy.getStrategyName());
		assertEquals("desc", strategy.getDescription());
		assertEquals(1, strategy.getTradeId());
		assertEquals(0.3, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurityName());
	}

	@Test
	public void testUpdate() {
		strategy = strategyServiceImpl.queryById(1);
		strategy.setStrategyName("Name2");
		strategyServiceImpl.update(strategy);
		assertEquals("Name2", strategy.getStrategyName());
		assertEquals("desc", strategy.getDescription());
		assertEquals(1, strategy.getTradeId());
		assertEquals(0.3, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurityName());
	}

	@Test
	public void testgetAll() {
		strategies = strategyServiceImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(1, strategies.size());
	}

	@Test
	public void testDeletel() {
		strategyServiceImpl.delete(1);
	}

}
