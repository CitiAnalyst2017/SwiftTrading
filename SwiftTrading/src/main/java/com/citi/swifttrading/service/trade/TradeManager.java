package com.citi.swifttrading.service.trade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@Service("tradeManager")
public class TradeManager {
	
	List<Trade> trades=new ArrayList<>();
	
	public Trade createMarketTrade(Position position, Security target, double exit) {
		Trade trade=new Trade(TradeType.MARKET,target, 100, LocalDateTime.now(),LocalDateTime.now().plusMonths(1), exit, exit, position, 0);
		trades.add(trade);
		return trade;
	}

	public void closeTrade(Trade trade) {
		trade.setStatus(TradeStatus.CLOSED);
		trade.setEnd_time(LocalDateTime.now());
	}

}
