package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.swifttrading.VO.TradeVO;
import com.citi.swifttrading.dao.SecurityDao;
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
	SecurityDao securityDao;
	
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
		trade.setSecurity(securityDao.queryById(tradeVO.getCode()));
		trade.setStart_time(new Date());
		trade.setExpiration(new Date());
		trade.setQuantity(tradeVO.getQuantity());
		trade.setPosition(tradeVO.getPosition());
		trade.setType(tradeVO.getType());
		trade.setLoss_price(tradeVO.getLossprice());
		trade.setProfit_price(tradeVO.getProfitprice());
		trade.setStatus(TradeStatus.CREATED);
		if(tradeVO.getType()==TradeType.LIMIT) {
			trade.setBuyPrice(tradeVO.getBuyprice());
		}
		return trade;
	}

	private void setTime() {
		c.setTime(start_time);
		c.add(Calendar.MINUTE, 15);
		expiration = c.getTime();
	}

	public List<TradeVO> getPendings() {
		List<TradeVO> VOs=new ArrayList<TradeVO>();
		tradeDao.queryAll().forEach(trade->{
			VOs.add(toVO(trade));
		});
		return VOs;
	}

	private TradeVO toVO(Trade trade) {
		TradeVO VO=new TradeVO();
		VO.setId(trade.getId());
		
		VO.setTradestatus(trade.getStatus());
		VO.setType(trade.getType());
		VO.setPosition(trade.getPosition());
		VO.setCode(trade.getSecurity().getNameAbbreviation());
		VO.setBuyprice(trade.getBuyPriceReal());
		VO.setQuantity(trade.getQuantity());
		VO.setStarttime(trade.getStart_time());
		return VO;
	}

	public void closeOrCancleTrade(Trade trade) {
		if(trade.getStatus()==TradeStatus.CREATED)
			cancleTrade(trade);
		else if(trade.getStatus()==TradeStatus.OPEN) {
			closeTrade(trade);
		}
	}

	public void cancleTrade(Trade trade) {
		// TODO Auto-generated method stub
		
	}
}
