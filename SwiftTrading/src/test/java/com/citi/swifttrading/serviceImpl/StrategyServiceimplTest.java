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
		strategy = new Strategy("testStrategyServices1", "desc11111", security, 0.2);
		strategyServiceImpl.save(strategy);
		strategy = new Strategy("testStrategyServices2", "desc22222", security, 0.2);
		strategyServiceImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyServiceImpl.queryById(20);
		assertEquals("testStrategyServices1", strategy.getStrategyName());
		assertEquals("desc11111", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(), 0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		strategy = strategyServiceImpl.queryById(21);
		assertEquals("testStrategyServices2", strategy.getStrategyName());
		assertEquals("desc22222", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(), 0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());

		strategy.setDescription("testUpdate");
		strategyServiceImpl.update(strategy);
		strategy = strategyServiceImpl.queryById(21);

		assertEquals("testStrategyServices2", strategy.getStrategyName());
		assertEquals("testUpdate", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(), 0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testgetAll() {
		strategies = strategyServiceImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(16, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyServiceImpl.delete(6);
	}
}
