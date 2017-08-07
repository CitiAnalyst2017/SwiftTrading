package com.citi.swifttrading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.citi.swifttrading.dao.SecurityRepo;
import com.citi.swifttrading.strategy.StrategyManager;

@Controller
public class HelloWorld {

	@Autowired
	 private StrategyManager strategyManager;
	@Autowired
	 private SecurityRepo securityRepo;
	
    @RequestMapping(value="/a",method=RequestMethod.GET)
    public String hello(Model m){
    	
        
       
        strategyManager.createMovingAverage();
        m.addAttribute("serverTime", "created");
        return "home";
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
    	 securityRepo.stop();
        return "home";
    }
    
    
}
