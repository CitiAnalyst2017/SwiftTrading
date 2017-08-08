package com.citi.swifttrading.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Strategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class StartegyDaoImplTest {

	@Autowired
	StrategyDaoImpl strategyDaoImpl;
	
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
		strategy.setSecurityName();
		strategyDaoImpl.save(strategy);
		strategy = new Strategy("strategy1", "strategy 1 desc", security, 0.2, 1);
		strategy.setSecurityName();
		strategyDaoImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyDaoImpl.queryById(2);
		assertEquals("strategy1", strategy.getStrategyName());
		assertEquals("strategy 1 desc", strategy.getDescription());
		assertEquals(1, strategy.getTradeId());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurityName());
	}

	@Test
	public void testUpdate() {
		strategy = strategyDaoImpl.queryById(2);
		strategy.setStrategyName("Name2");
		strategyDaoImpl.update(strategy);
		assertEquals("Name2", strategy.getStrategyName());
		assertEquals("strategy 1 desc", strategy.getDescription());
		assertEquals(1, strategy.getTradeId());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurityName());
	}

	@Test
	public void testgetAll() {
		strategies = strategyDaoImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(1, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyDaoImpl.delete(3);
	}

}
