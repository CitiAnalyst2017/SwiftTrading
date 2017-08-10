package com.citi.swifttrading.strategy;

import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Strategy;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class StrategyRunner extends Thread {

	protected boolean suspended=false;
	protected Object lock=new Object();
	protected TradeManager tradeManager;
	protected Security target;
	protected double exit;
	protected Trade request;
	protected int strategyID;
	StrategyRunner(TradeManager tradeManager, Strategy strategy) {
		this.tradeManager = tradeManager;
		this.target = strategy.getSecurity();
		this.exit = strategy.getExit();
		this.strategyID =strategy.getId();
		log.info(tradeManager.toString());
	}

	protected Trade createLongRequest(Security target, double exit) {

		log.info(String.format("Long Secrity %s ,at price %f ,with exit %f", target.getNameAbbreviation(), target.latestPrice(),
				exit));
		return tradeManager.createMarketTrade(Position.LONG, target, exit, 20,strategyID);
	}

	protected Trade createShortRequest(Security target, double exit) {

		log.info(String.format("short Secrity %s ,at price %f ,with exit %f", target.getNameAbbreviation(), target.latestPrice(),
				exit));
		return tradeManager.createMarketTrade(Position.SHORT, target, exit,20,strategyID);

	}

	protected void closeRequest(Trade trade) {
		log.info(String.format("close request %s", trade.toString()));
		tradeManager.closeOrCancleTrade(trade);
	};
	
	protected boolean takeProfit() { 
		return target.latestPrice()>(1+exit)*request.getBuyPrice()||target.latestPrice()<(1-exit)*request.getBuyPrice();
	}

	public void Resume() {
		suspended=false;
		 synchronized (lock) {
		lock.notify();
		 }
	}
	
	public void Suspend() {
		suspended=true;
	}
}
