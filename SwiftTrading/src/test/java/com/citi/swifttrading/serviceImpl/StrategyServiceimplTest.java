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
import com.citi.swifttrading.domain.BollBand;
import com.citi.swifttrading.domain.MovingAverage;
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
		MovingAverage strategMMM = new MovingAverage("MovingAverage", "MovingAverage", security, 19, 10, 0.2);
		System.out.println(strategyServiceImpl.save(strategMMM));
		BollBand strateyBBB = new BollBand("BollBand", "BollBand", security, 19, 5.5, 0.2);
		System.out.println(strategyServiceImpl.save(strateyBBB));
	}

	@Test
	public void testQueryById() {
		MovingAverage strategy = (MovingAverage) strategyServiceImpl.queryById(70);
		System.out.println(strategy.getSecurity().toString());
		assertEquals("MovingAverage", strategy.getStrategyName());
		assertEquals("MovingAverage", strategy.getDescription());
		assertEquals(0.2, strategy.getExit(), 0);
		assertEquals("A", strategy.getSecurity().getNameAbbreviation());
		assertEquals(19, strategy.getLongPeriod());
		assertEquals(10, strategy.getShortPeriod());

		BollBand strategy2 = (BollBand) strategyServiceImpl.queryById(71);
		assertEquals("BollBand", strategy2.getStrategyName());
		assertEquals("BollBand", strategy2.getDescription());
		assertEquals(0.2, strategy2.getExit(), 0);
		assertEquals("A", strategy2.getSecurity().getNameAbbreviation());
		assertEquals(19, strategy2.getPeriod());
		assertEquals(5.5, strategy2.getStd(), 0);
	}

	@Test
	public void testUpdate() {
		MovingAverage strategyMM = (MovingAverage) strategyServiceImpl.queryById(70);
		assertEquals("MovingAverage", strategyMM.getStrategyName());
		assertEquals("MovingAverage", strategyMM.getDescription());
		assertEquals(0.2, strategyMM.getExit(), 0);
		assertEquals("A", strategyMM.getSecurity().getNameAbbreviation());
		assertEquals(19, strategyMM.getLongPeriod());
		assertEquals(10, strategyMM.getShortPeriod());

		security = strategyMM.getSecurity();
		security.setNameAbbreviation("MO");
		strategyMM.setSecurity(security);
		strategyMM.setDescription("change desc");
		strategyMM.setExit(0.5);
		strategyMM.setLongPeriod(100);
		strategyMM.setShortPeriod(100);
		strategyServiceImpl.update(strategyMM);

		strategyMM = (MovingAverage) strategyServiceImpl.queryById(70);
		assertEquals("MovingAverage", strategyMM.getStrategyName());
		assertEquals("change desc", strategyMM.getDescription());
		assertEquals(0.5, strategyMM.getExit(), 0);
		assertEquals("MO", strategyMM.getSecurity().getNameAbbreviation());
		assertEquals(100, strategyMM.getLongPeriod());
		assertEquals(100, strategyMM.getShortPeriod());

		BollBand strategyBB = (BollBand) strategyServiceImpl.queryById(71);
		assertEquals("BollBand", strategyBB.getStrategyName());
		assertEquals("BollBand", strategyBB.getDescription());
		assertEquals(0.2, strategyBB.getExit(), 0);
		assertEquals("A", strategyBB.getSecurity().getNameAbbreviation());
		assertEquals(19, strategyBB.getPeriod());
		assertEquals(5.5, strategyBB.getStd(), 0);

		security = strategyBB.getSecurity();
		security.setNameAbbreviation("MO");
		strategyBB.setSecurity(security);
		strategyBB.setDescription("change desc");
		strategyBB.setExit(0.5);
		strategyBB.setPeriod(200);
		strategyBB.setStd(200.5);
		strategyServiceImpl.update(strategyBB);

		strategyBB = (BollBand) strategyServiceImpl.queryById(71);
		assertEquals("BollBand", strategyBB.getStrategyName());
		assertEquals("change desc", strategyBB.getDescription());
		assertEquals(0.5, strategyBB.getExit(), 0);
		assertEquals("MO", strategyBB.getSecurity().getNameAbbreviation());
		assertEquals(200, strategyBB.getPeriod());
		assertEquals(200.5, strategyBB.getStd(), 0);

	}

	@Test
	public void testgetAll() {
		strategies = strategyServiceImpl.queryAll();
		assertNotNull(strategies);
		assertEquals(19, strategies.size());
	}

	@Test
	public void testDelete() {
		strategyServiceImpl.delete(54);
	}
}
