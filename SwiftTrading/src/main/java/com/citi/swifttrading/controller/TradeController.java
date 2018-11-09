package com.citi.swifttrading.controller;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> d01e6dc970b1212d627fb6537c4dab4dcabfa486
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.TradeStatus;
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

	    @RequestMapping(value="/pending" ,method=RequestMethod.GET)
	    public List<TradeVO> getPendings(){
	        log.debug("save");
	        return tradeManager.getPendings();
	    }
	    
	    @RequestMapping(method=RequestMethod.PUT)
	    public void update(@RequestBody(required=true)TradeVO trade){
	    	if(trade.getStatus()==TradeStatus.CLOSING)
	    		tradeManager.closeOrCancleTrade(trade);
	    }
	    
	    @RequestMapping(value="/created/user" ,method=RequestMethod.GET)
	    public List<TradeVO> getUserHistory(){
	        log.debug("save");
	        return tradeManager.getUserHistory();
	    }
	    
	    @RequestMapping(value="/created" ,method=RequestMethod.GET)
	    public List<String> getCreater(){
	    	List<Integer> ids=tradeManager.getCreater();
	    	List<String> str=new ArrayList<>();
	    	ids.forEach(id->str.add(String.valueOf(id)));
			return str;
	    }
	  
	    @RequestMapping(value="/created/{id}" ,method=RequestMethod.GET)
	    public List<TradeVO> getCreater(@PathVariable("id") int id){
	    	return tradeManager.getTradeHistory(id);
	    }

}
