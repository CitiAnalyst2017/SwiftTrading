package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.dao.SecurityRepo;
import com.citi.swifttrading.dao.TradeDao;
import com.citi.swifttrading.domain.Security;
import com.citi.swifttrading.domain.Trade;
import com.citi.swifttrading.enumration.Position;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.enumration.TradeType;

@Service("tradeManager")
public class TradeManager {
	@Autowired
	TradeDao tradeDao;
	@Autowired
	SecurityRepo securityDao;
	
	List<Trade> trades = new ArrayList<>();
	Date start_time = new Date();
	Date expiration;
	Calendar c = Calendar.getInstance();

	public Trade createMarketTrade(Position position, Security target, double exit) {
		this.setTime();
		Trade trade = new Trade(TradeType.LIMIT, target, 100, start_time, expiration, 15, exit, position, 0);
		trades.add(trade);
		return trade;
	}

	public void closeTrade(Trade trade) {
		trade.setStatus(TradeStatus.CLOSED);
		trade.setEnd_time(new Date());
		trade.setSalePrice(trade.getSecurity().latestPrice());
		tradeDao.save(trade);
	}

	public Trade getTradeById(int id) {
		return tradeDao.queryById(id);
	}

	public Trade createTrade(TradeVO tradeVO) {
		Trade trade = toTrade(tradeVO);
		tradeDao.save(trade);
		return trade;
	}

	private Trade toTrade(TradeVO tradeVO) {
		Trade trade=new Trade();
		trade.setSecurity(securityDao.get(tradeVO.getSymbol()));
		trade.setStart_time(new Date());
		//TODO trade.setExpiration(new Date().min);
		trade.setQuantity(tradeVO.getQuantity());
		trade.setPosition(tradeVO.getPosition());
		trade.setType(tradeVO.getTradeType());
		trade.setLoss_price(tradeVO.getLossPrice());
		trade.setProfit_price(tradeVO.getProfitPrice());
		trade.setStatus(TradeStatus.CREATED);
		if(tradeVO.getTradeType()==TradeType.LIMIT) {
			trade.setPrice(tradeVO.getPrice());
		}
		else {
			trade.setPrice(securityDao.get(tradeVO.getSymbol()).latestPrice());
		}
		return trade;
	}

	private void setTime() {
		c.setTime(start_time);
		c.add(Calendar.MINUTE, 15);
		expiration = c.getTime();
	}
}
