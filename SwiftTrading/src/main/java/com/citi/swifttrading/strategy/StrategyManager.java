package com.citi.swifttrading.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.VO.StrategyVO;
import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.dao.StrategyRepo;
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
	private StrategyRepo strategyRepo;
	
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
		movingAverage.setStrategyName("MovingAverage");
		movingAverage.setId(strategyRepo.save(movingAverage));
		MovingAverageRunner target = new MovingAverageRunner(tradeManager,movingAverage);
		movingAverage.setRunner(target);
		target.start();
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
		bollBand.setStrategyName("BollBand");
		bollBand.setId(strategyRepo.save(bollBand));
		BollBandRunner target = new BollBandRunner(tradeManager,bollBand);
		bollBand.setRunner(target);
		target.start();
		log.info("start :"+bollBand.toString());
		return VO;
	}

	public List<StrategyVO> getAll() {
		List<StrategyVO> VOs=new ArrayList<>();
		strategys=strategyRepo.queryAll();
		strategys.forEach(strategy->VOs.add(toVO(strategy)));
		return VOs;
	}

	private StrategyVO toVO(Strategy strategy) {
		StrategyVO VO=new StrategyVO();
		VO.setCode(strategy.getSecurity().getNameAbbreviation());
		VO.setExit(strategy.getExit());
		VO.setId(strategy.getId());
//		VO.setLongperiod(strategy.get);
//		VO.setShortperiod(strategy)
//		VO.setPeriod(strategy);
//		VO.setStd(strategy);
		VO.setStatus(strategy.getStatus());
		VO.setName(strategy.getStrategyName());
		return VO;
	}

	@SuppressWarnings("deprecation")
	public StrategyVO update(StrategyVO VO) throws InterruptedException{
		Strategy strategy=strategyRepo.queryById(VO.getId());
		if(!VO.getStatus().equals(strategy.getStatus())) {
			StrategyRunner runner = strategy.getRunner();
			if(VO.getStatus().equals("Running")) {
				if(runner.getState()==Thread.State.WAITING) {
					runner.Resume();
					log.info(runner.getState().toString());
				}else if(runner.getState()==Thread.State.NEW) {
					runner.start();
				}
			}
			else if(VO.getStatus().equals("Stoping")) {
							runner.Suspend();					
					}			
		}
		strategyRepo.update(strategy);
		return null;
	}
}
