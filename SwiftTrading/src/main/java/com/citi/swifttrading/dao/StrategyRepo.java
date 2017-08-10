package com.citi.swifttrading.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.BollBand;
import com.citi.swifttrading.domain.MovingAverage;
import com.citi.swifttrading.domain.Strategy;
import com.citi.swifttrading.service.trade.TradeManager;
import com.citi.swifttrading.strategy.BollBandRunner;
import com.citi.swifttrading.strategy.MovingAverageRunner;
import com.citi.swifttrading.strategy.StrategyRunner;

@Repository
public class StrategyRepo {
	@Autowired
	private StrategyDao strategyDao;
	@Autowired
	private TradeManager tradeManager;
	@Autowired
	private PriceRepo priceRepo;
	
	Map<Integer,Strategy> strategys=new HashMap<>();
	
	StrategyRepo(){
		//loadAll();
	}

	private void loadAll() {
		List<Strategy> allStrategys=strategyDao.queryAll();
		for(Strategy strategy:allStrategys){
			bindRunner(strategy);
		}
	}

	
	public Strategy queryById(int id) {
		if(strategys.containsKey(id)) {
			return strategys.get(id);
		}
		Strategy strategy = strategyDao.queryById(id);
		return bindRunner(strategy);
	};

	private Strategy bindRunner(Strategy strategy) {
	
		if(strategy.getStrategyName().equals("MovingAverage")) {
			MovingAverage movingAverage=(MovingAverage)strategy;
			priceRepo.bind(movingAverage.getSecurity());
			StrategyRunner runner = new MovingAverageRunner(tradeManager,(MovingAverage)strategy);
			movingAverage.setRunner(runner);
			strategys.put(movingAverage.getId(), movingAverage);
			return movingAverage;
		}
			
		else if(strategy.getStrategyName().equals("BollBand")) {
			BollBand bollBand=(BollBand)strategy;
			priceRepo.bind(bollBand.getSecurity());
			StrategyRunner runner = new BollBandRunner(tradeManager,(BollBand)strategy);
			bollBand.setRunner(runner);
			strategys.put(bollBand.getId(), bollBand);
			return bollBand;
		}
		return null;
	}


	public int save(Strategy s) {
		int id=strategyDao.save(s);
		strategys.put(id,s);
		return id;
	};

	public void update(Strategy s) {
		strategys.replace(s.getId(), s);
		strategyDao.update(s);
	}
//
//	public void delete(int id);

	public List<Strategy> queryAll(){
		List<Strategy> result=new ArrayList<>();
		result.addAll(strategys.values());
		List<Strategy> allStrategys=strategyDao.queryAll();
		for(Strategy strategy:allStrategys){
			if (strategys.containsKey(strategy.getId())) {
				continue;
			}
			result.add(bindRunner(strategy));
		}
		return result;
	}
}
