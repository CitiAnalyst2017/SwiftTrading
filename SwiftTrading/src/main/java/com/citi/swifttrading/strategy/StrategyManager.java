package com.citi.swifttrading.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.VO.StrategyVO;
import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.dao.StrategyDao;
import com.citi.swifttrading.domain.BollBand;
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
	@Autowired
	private StrategyDao strategyDao;
	
	private List<Strategy> strategys= new ArrayList<>();
	
	

	public StrategyManager() {
		loadStrategys();
	}

	private void loadStrategys() {
		// TODO Auto-generated method stub
		
	}

	public StrategyVO createMovingAverage(StrategyVO VO) {
		MovingAverage movingAverage=new MovingAverage();
		movingAverage.setExit(VO.getExit());
		movingAverage.setLongPeriod(VO.getLongperiod());
		movingAverage.setShortPeriod(VO.getShortperiod());
		movingAverage.setSecurity(securityDao.queryById(VO.getCode()));
		
		MovingAverageRunner target = new MovingAverageRunner(tradeManager,movingAverage);
		movingAverage.setStatus("ACTIVE");
		movingAverage.setRunner(target);
		target.start();
		strategyDao.save(movingAverage);
		
		log.info("start :"+movingAverage.toString());
		return VO;
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

	public StrategyVO createBollBand(StrategyVO VO) {
		BollBand bollBand=new BollBand();
		bollBand.setExit(VO.getExit());
		bollBand.setPeriod(VO.getPeriod());
		bollBand.setStd(VO.getStd());
		bollBand.setSecurity(securityDao.queryById(VO.getCode()));
		
		BollBandRunner target = new BollBandRunner(tradeManager,bollBand);
		bollBand.setStatus("ACTIVE");
		bollBand.setRunner(target);
		target.start();
		strategyDao.save(bollBand);
		
		log.info("start :"+bollBand.toString());
		return VO;
	}
}
