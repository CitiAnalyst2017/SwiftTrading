package com.citi.swifttrading.domamin;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Strategy;


public class StrategyTest {
	
	Strategy strategy;
	
	@Before
	public void setUp() {
		strategy = new Strategy("Strategy Name", "Strategy Desc");
	}

	@Test
	public void testUserGet() {
		assertEquals("Strategy Name", strategy.getStrategyName());
		assertEquals("Strategy Desc", strategy.getDescription());
	}
	
	@Test
	public void testUserSet() {
		strategy.setStrategyName("Strategy Name Again");
		strategy.setDescription("Strategy Desc Again");
		
		assertEquals("Strategy Name Again", strategy.getStrategyName());
		assertEquals("Strategy Desc Again", strategy.getDescription());
	}

}
