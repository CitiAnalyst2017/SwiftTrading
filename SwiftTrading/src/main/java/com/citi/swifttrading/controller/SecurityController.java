package com.citi.swifttrading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.OrderBookItemVO;
import com.citi.swifttrading.dao.PriceRepo;

@RestController
@RequestMapping("/security")
public class SecurityController {
	@Autowired
	PriceRepo priceRepo;
	@RequestMapping(value="{abbr}/orderbook",method=RequestMethod.GET)
    public List<OrderBookItemVO> hello(@PathVariable("abbr") String abbr){
		
		List<OrderBookItemVO> VOs=new ArrayList<>();
		//TODO priceRepo.getOrderBook(abbr).getItems().foreach();
		VOs.add(new OrderBookItemVO("APPL","Bid",50,15));
        return VOs;
    }
}
