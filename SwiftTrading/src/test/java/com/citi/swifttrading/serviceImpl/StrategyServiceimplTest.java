package com.citi.swifttrading.serviceImpl;

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
public class StrategyServiceimplTest {

	@Autowired
	StrategyServiceImpl strategyServiceImpl;

	Strategy222 strategy;

	List<Strategy222> strategies;

	@Test
	public void testSave() {
		strategy = new Strategy222("strategy4", "strategy 4 desc", 14);
		strategyServiceImpl.save(strategy);
		strategy = new Strategy222("strategy5", "strategy 5 desc", 21);
		strategyServiceImpl.save(strategy);
		strategy = new Strategy222("strategy6", "strategy 6 desc", 22);
		strategyServiceImpl.save(strategy);
	}

	@Test
	public void testQueryById() {
		strategy = strategyServiceImpl.queryById(4);
		System.out.println(strategy.toString());
		assertEquals("strategy4", strategy.getStrategyName());
		assertEquals("strategy 4 desc", strategy.getDescription());
		assertEquals(14, strategy.getTradeId());
	}

	@Test
	public void testUpdate() {
		strategy = strategyServiceImpl.queryById(5);
		strategy.setStrategyName("strategy5 change");
		strategyServiceImpl.update(strategy);
		assertEquals("strategy5 change", strategy.getStrategyName());
		assertEquals("strategy 5 desc", strategy.getDescription());
		assertEquals(21, strategy.getTradeId());
	}

	@Test
	public void testgetAll() {
		strategies = strategyServiceImpl.queryAll();
		System.out.println(strategies.get(1).toString());
		assertNotNull(strategies);
		assertEquals(5, strategies.size());
	}

	@Test
	public void testDeletel() {
		strategyServiceImpl.delete(6);
	}

}
