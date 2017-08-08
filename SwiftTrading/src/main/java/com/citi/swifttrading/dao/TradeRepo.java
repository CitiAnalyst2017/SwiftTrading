package com.citi.swifttrading.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeType;

@Repository
public class TradeRepo {

	private List<Trade> trades=new ArrayList<>();
	TradeRepo(){
		trades.add(new Trade(TradeType.LIMIT,new Security("Apple","APPL"), 100, LocalDateTime.now(),LocalDateTime.now().plusMonths(1), 0.05, 0.05, Position.LONG, 0));
	}
	public Trade get(int id) {
		return trades.get(id);
	}

	public void add(Trade trade) {
		trades.add(trade);
	}
	
	public List<Trade> getAll(){
		return trades;
	}
}
