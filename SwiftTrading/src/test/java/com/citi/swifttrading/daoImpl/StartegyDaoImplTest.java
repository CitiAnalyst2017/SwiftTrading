package com.citi.swifttrading.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.domain.Strategy222;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class StartegyDaoImplTest {

	@Autowired
	StrategyDaoImpl strategyDaoImpl;
	
	Strategy222 strategy;
	
	List<Strategy222> strategies;

	@Test
	public void testSave() {
		strategy = new Strategy222("strategy1", "strategy 1 desc", 16);
		strategyDaoImpl.save(strategy);
		strategy = new Strategy222("strategy2", "strategy 2 desc", 17);
		strategyDaoImpl.save(strategy);
		strategy = new Strategy222("strategy3", "strategy 3 desc", 18);
		strategyDaoImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyDaoImpl.queryById(2);
		System.out.println(strategy.toString());
		assertEquals("strategy2", strategy.getStrategyName());
		assertEquals("strategy 2 desc", strategy.getDescription());
		assertEquals(17, strategy.getTradeId());
	}

	@Test
	public void testUpdate() {
		strategy = strategyDaoImpl.queryById(3);
		strategy.setStrategyName("strategy3 change");
		strategyDaoImpl.update(strategy);
		assertEquals("strategy3 change", strategy.getStrategyName());
		assertEquals("strategy 3 desc", strategy.getDescription());
		assertEquals(18, strategy.getTradeId());
	}

	@Test
	public void testgetAll() {
		strategies = strategyDaoImpl.queryAll();
		System.out.println(strategies.get(1).toString());
		assertNotNull(strategies);
		assertEquals(3, strategies.size());
	}

	@Test
	public void testDeletel() {
		strategyDaoImpl.delete(1);
	}

}
