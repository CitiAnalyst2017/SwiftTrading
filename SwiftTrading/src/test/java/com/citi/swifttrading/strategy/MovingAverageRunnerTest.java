package com.citi.swifttrading.strategy;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.MovingAverage;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.service.trade.SecurityUpdater;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MovingAverageRunnerTest {

	private MovingAverageRunner target;
	private MovingAverage movingAverage;
	private Security security;
	private SecurityUpdater updater;
	@Before
	public void setUp() throws Exception {
		security=new Security();
		security.setNameAbbreviation("APPL");
		//updater= new SecurityUpdater("APPL");
		target=new  MovingAverageRunner(null, movingAverage);
	}

	@Test
	public void test()  {
		
		updater.start();
		log.info("a");
		try {
			Thread.sleep(1075);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("b");
		target.start();
		
		try {
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
