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
		strategy = new Strategy("testTradeIdNull1", "desc1", security, 0.2);
		strategyDaoImpl.save(strategy);
		strategy = new Strategy("testTradeIdNull2", "desc2", security, 0.2);
		strategyDaoImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyDaoImpl.queryById(14);
		assertEquals("testTradeIdNull1", strategy.getStrategyName());
		assertEquals("desc1", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		strategy = strategyDaoImpl.queryById(15);
		assertEquals("testTradeIdNull2", strategy.getStrategyName());
		assertEquals("desc2", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
		
		strategy.setDescription("testUpdate");
		strategyDaoImpl.update(strategy);
		strategy = strategyDaoImpl.queryById(15);
		
		assertEquals("testTradeIdNull2", strategy.getStrategyName());
		assertEquals("testUpdate", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testgetAll() {
		strategies = strategyDaoImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(15, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyDaoImpl.delete(5);
	}

}
