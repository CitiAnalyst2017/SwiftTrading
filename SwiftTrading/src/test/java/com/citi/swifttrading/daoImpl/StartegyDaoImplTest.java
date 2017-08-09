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

import com.citi.swifttrading.domain.MovingAverage;
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
		security = securityDaoImpl.queryById("AA");
	}
	
	@Test
	public void testSave() {
		strategy = new Strategy("test1", "test1 desc", security, 0.2);
		strategyDaoImpl.save(strategy);
		strategy = new Strategy("test1", "test1 desc", security, 0.2);
		strategyDaoImpl.save(strategy);
	}
	
	@Test
	public void testQueryById() {
		strategy = strategyDaoImpl.queryById(52);
		System.out.println(strategy.getSecurity().toString());
		assertEquals("test1", strategy.getStrategyName());
		assertEquals("test1 desc", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("AA", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		strategy = strategyDaoImpl.queryById(53);
		System.out.println(strategy.getSecurity().toString());
		assertEquals("test1", strategy.getStrategyName());
		assertEquals("test1 desc", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("AA", strategy.getSecurity().getNameAbbreviation());
		
		security.setNameAbbreviation("MO");
		strategy.setSecurity(security);
		strategyDaoImpl.update(strategy);
		strategy = strategyDaoImpl.queryById(53);
		System.out.println(strategy.getSecurity().toString());
		assertEquals("test1", strategy.getStrategyName());
		assertEquals("test1 desc", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("MO", strategy.getSecurity().getNameAbbreviation());
	}

	@Test
	public void testgetAll() {
		strategies = strategyDaoImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(4, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyDaoImpl.delete(50);
	}

}
