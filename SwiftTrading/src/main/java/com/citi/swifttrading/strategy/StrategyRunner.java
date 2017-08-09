package com.citi.swifttrading.strategy;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Strategy;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class StrategyRunner extends Thread {

	protected TradeManager tradeManager;
	protected Security target;
	protected double exit;
	protected Trade request;
	StrategyRunner(TradeManager tradeManager, Strategy strategy) {
		this.tradeManager = tradeManager;
		this.target = strategy.getSecurity();
		this.exit = strategy.getExit();
		log.info(tradeManager.toString());
	}

	protected Trade createLongRequest(Security target, double exit) {

		log.info(String.format("Long Secrity %s ,at price %f ,with exit %f", target.getNameAbbreviation(), target.latestPrice(),
				exit));
		return tradeManager.createMarketTrade(Position.LONG, target, exit, 20);
	}

	protected Trade createShortRequest(Security target, double exit) {

		log.info(String.format("short Secrity %s ,at price %f ,with exit %f", target.getNameAbbreviation(), target.latestPrice(),
				exit));
		return tradeManager.createMarketTrade(Position.SHORT, target, exit,20);

	}

	protected void closeRequest(Trade trade) {
		log.info(String.format("close request %s", trade.toString()));
		tradeManager.closeOrCancleTrade(trade);
	};
	
	protected boolean takeProfit() { 
		return target.latestPrice()>(1+exit)*request.getBuyPrice()||target.latestPrice()<(1-exit)*request.getBuyPrice();
	}

}
