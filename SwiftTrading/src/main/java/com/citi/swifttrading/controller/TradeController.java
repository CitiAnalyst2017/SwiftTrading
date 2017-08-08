package com.citi.swifttrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/trade")
public class TradeController {
	@Autowired 
	TradeManager tradeManager;
	
	@RequestMapping(method=RequestMethod.GET)
    public List<TradeVO> getAllTrade(){
        log.debug("hello world");
        return tradeManager.getTradeVOs();
    }
	 	@RequestMapping(value="{id}",method=RequestMethod.GET)
	    public TradeVO getById(@PathVariable("id") int id){
	        log.debug("hello world");
	        return tradeManager.getTradeVOById(id);
	    }
	    
	    @RequestMapping(method=RequestMethod.POST)
	    public TradeVO save(@RequestBody(required=true)TradeVO trade){
	        log.debug("save");
	        return tradeManager.createTrade(trade);
	    }
	    
}
