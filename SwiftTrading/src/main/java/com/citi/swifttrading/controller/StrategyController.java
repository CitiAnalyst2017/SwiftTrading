package com.citi.swifttrading.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.StrategyVO;
import com.citi.swifttrading.dao.SecurityDao;
import com.citi.swifttrading.strategy.StrategyManager;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
	
	@Autowired
	 private StrategyManager strategyManager;
	@Autowired
	 private SecurityDao securityDao;
	
    @RequestMapping(value="/MovingAverage",method=RequestMethod.POST)
    public StrategyVO createMovingAveragr(@RequestBody StrategyVO strategyVO){
    	
        
       
        return strategyManager.createMovingAverage(strategyVO);
  
    }
    
    @RequestMapping(value="/b",method=RequestMethod.GET)
    public String xixi(Model m){
 
        
        
        m.addAttribute("serverTime", "stragystoped");
        strategyManager.stopStrategys();
        return "home";
    }
    
    @RequestMapping(value="/c",method=RequestMethod.GET)
    public String xccc(Model m){
    	 m.addAttribute("serverTime", "updatestoped");
    	// securityRepo.stop();
        return "home";
    }
    
    
}
