package com.citi.swifttrading.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.domain.MovingAverage;
import com.citi.swifttrading.domain.Strategy;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StrategyManager {
	
	@Autowired
	private TradeManager tradeManager;
	@Autowired
	private SecurityDao securityDao;

	private List<Strategy> strategys= new ArrayList<>();
	
	

	public StrategyManager() {
		loadStrategys();
	}

	private void loadStrategys() {
		// TODO Auto-generated method stub
		
	}

	public void createMovingAverage() {
		MovingAverage movingAverage=new MovingAverage("move1", "moveagedx", securityDao.queryById("APPL"), 200, 500, 0.05);
		MovingAverageRunner target = new MovingAverageRunner(tradeManager,movingAverage);
		target.start();
		movingAverage.setRunner(target);
		
		log.info("start :"+movingAverage.toString());
	}
	
	@SuppressWarnings("deprecation")
	public void stopStrategy(Strategy strategy) {
		strategy.getRunner().stop();
		log.info("stop :"+strategy.toString());
	}
	
	@SuppressWarnings("deprecation")
	public void stopStrategys() {
		strategys.forEach(strategy->{
			strategy.getRunner().stop();
			log.info("stop :"+strategy.toString());

		}
		);
		
	}
	
	public void startStrategy(Strategy strategy) {
		strategy.getRunner().start();
		log.info("stop :"+strategy.toString());
	}
}
