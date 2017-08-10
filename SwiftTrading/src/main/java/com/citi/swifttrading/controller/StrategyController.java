package com.citi.swifttrading.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.StrategyVO;
import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.service.trade.TradeManager;
import com.citi.swifttrading.strategy.StrategyManager;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
	
	@Autowired
	 private StrategyManager strategyManager;
	
	@Autowired
	 private TradeManager TradeManager;
	
    @RequestMapping(value="/MovingAverage",method=RequestMethod.POST)
    public StrategyVO createMovingAveragr(@RequestBody StrategyVO strategyVO){
    	
        
       
        return strategyManager.createMovingAverage(strategyVO);
  
    }
    
    @RequestMapping(value="/BollBand",method=RequestMethod.POST)
    public StrategyVO createBollBand(@RequestBody StrategyVO strategyVO){
 
    	return strategyManager.createBollBand(strategyVO);
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public List<StrategyVO> getAll(){
    	return strategyManager.getAll();
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public StrategyVO update(@RequestBody(required=true)StrategyVO trade){
			try {
				return strategyManager.update(trade);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
    }
    
    @RequestMapping(value = "{id}/orders", method = RequestMethod.GET)
	public List<TradeVO> hello(@PathVariable("id") int id) {
		return TradeManager.getByStrategyId(id);
	}
}
