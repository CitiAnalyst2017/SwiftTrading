package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Strategy222;


public class StrategyTest222 {
	
	Strategy222 strategy;
	
	@Before
	public void setUp() {
		strategy = new Strategy222("Strategy Name", "Strategy Desc",1102);
	}

	@Test
	public void testUserGet() {
		assertEquals("Strategy Name", strategy.getStrategyName());
		assertEquals("Strategy Desc", strategy.getDescription());
		assertEquals(1102, strategy.getTradeId());
	}
	
	@Test
	public void testUserSet() {
		strategy.setStrategyName("Strategy Name Again");
		strategy.setDescription("Strategy Desc Again");
		strategy.setTradeId(1103);
		
		assertEquals("Strategy Name Again", strategy.getStrategyName());
		assertEquals("Strategy Desc Again", strategy.getDescription());
		assertEquals(1103, strategy.getTradeId());
	}

}
