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
import com.citi.swifttrading.domain.BollBand;

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
		MovingAverage strategMMM = new MovingAverage("MovingAverage", "MovingAverage", security, 19, 10, 0.2);
		strategyDaoImpl.save(strategMMM);
		BollBand strateyBBB = new BollBand("BollBand", "BollBand", security, 19, 5.5, 0.2);
		strategyDaoImpl.save(strateyBBB);
	}
	
	@Test
	public void testQueryById() {
		MovingAverage strategy = (MovingAverage) strategyDaoImpl.queryById(68);
		System.out.println(strategy.getSecurity().toString());
		assertEquals("MovingAverage", strategy.getStrategyName());
		assertEquals("MovingAverage", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(),0);
		assertEquals("AA", strategy.getSecurity().getNameAbbreviation());
		assertEquals(19, strategy.getLongPeriod());
		assertEquals(10, strategy.getShortPeriod());
		
		BollBand strategy2 = (BollBand) strategyDaoImpl.queryById(69);
		assertEquals("BollBand", strategy2.getStrategyName());
		assertEquals("BollBand", strategy2.getDescription());
		assertEquals(0.2, strategy2.getExit(),0);
		assertEquals("AA", strategy2.getSecurity().getNameAbbreviation());
		assertEquals(19, strategy2.getPeriod());
		assertEquals(5.5, strategy2.getStd(), 0);
	}

	@Test
	public void testUpdate() {
		MovingAverage strategyMM = (MovingAverage) strategyDaoImpl.queryById(68);
		assertEquals("MovingAverage", strategyMM.getStrategyName());
		assertEquals("MovingAverage", strategyMM.getDescription());
		assertEquals(0.2, strategyMM.getExit(),0);
		assertEquals("AA", strategyMM.getSecurity().getNameAbbreviation());
		assertEquals(19, strategyMM.getLongPeriod());
		assertEquals(10, strategyMM.getShortPeriod());
		
		security = strategyMM.getSecurity();
		security.setNameAbbreviation("MO");
		strategyMM.setSecurity(security);
		strategyMM.setDescription("change desc");
		strategyMM.setExit(0.5);
		strategyMM.setLongPeriod(100);
		strategyMM.setShortPeriod(100);
		strategyDaoImpl.update(strategyMM);
		
		strategyMM = (MovingAverage) strategyDaoImpl.queryById(68);
		assertEquals("MovingAverage", strategyMM.getStrategyName());
		assertEquals("change desc", strategyMM.getDescription());
		assertEquals(0.5, strategyMM.getExit(),0);
		assertEquals("MO", strategyMM.getSecurity().getNameAbbreviation());
		assertEquals(100, strategyMM.getLongPeriod());
		assertEquals(100, strategyMM.getShortPeriod());
		
		BollBand strategyBB = (BollBand) strategyDaoImpl.queryById(69);
		assertEquals("BollBand", strategyBB.getStrategyName());
		assertEquals("BollBand", strategyBB.getDescription());
		assertEquals(0.2, strategyBB.getExit(),0);
		assertEquals("AA", strategyBB.getSecurity().getNameAbbreviation());
		assertEquals(19, strategyBB.getPeriod());
		assertEquals(5.5, strategyBB.getStd(), 0);
		
		security = strategyBB.getSecurity();
		security.setNameAbbreviation("MO");
		strategyBB.setSecurity(security);
		strategyBB.setDescription("change desc");
		strategyBB.setExit(0.5);
		strategyBB.setPeriod(200);
		strategyBB.setStd(200.5);
		strategyDaoImpl.update(strategyBB);
		
		strategyBB = (BollBand) strategyDaoImpl.queryById(69);
		assertEquals("BollBand", strategyBB.getStrategyName());
		assertEquals("change desc", strategyBB.getDescription());
		assertEquals(0.5, strategyBB.getExit(),0);
		assertEquals("MO", strategyBB.getSecurity().getNameAbbreviation());
		assertEquals(200, strategyBB.getPeriod());
		assertEquals(200.5, strategyBB.getStd(), 0);

	}

	@Test
	public void testgetAll() {
		strategies = strategyDaoImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(18, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyDaoImpl.delete(53);
	}

}
