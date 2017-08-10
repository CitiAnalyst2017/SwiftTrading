package com.citi.swifttrading.service.trade;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.citi.swifttrading.util.DateUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("tradeManager")
public class TradeManager {
	@Autowired
	TradeDao tradeDao;
	@Autowired
	SecurityDao securityDao;
	
	private static final DecimalFormat numf = new DecimalFormat("#0.00"); 
	private static final SimpleDateFormat datef= new SimpleDateFormat ("HH:mm:ss"); 

	public Trade createMarketTrade(Position position, Security target, double exit,int quantity,int strategyId) {
		Date now=new Date();
		Trade trade = new Trade(TradeType.MARKET, target, quantity, now, DateUtil.addDay(now, 1), exit, exit, position, 0);
		trade.setStrategyId(strategyId);
		trade.setBuyPriceReal(target.latestPrice());
		trade.setId(tradeDao.save(trade));
		return trade;
	}

	public void closeTrade(Trade trade) {
		tradeDao.update(trade);
		trade.setStatus(TradeStatus.CLOSED);
		trade.setEnd_time(new Date());
		trade.setSalePriceReal(trade.getSecurity().latestPrice());
		tradeDao.update(trade);
		log.info(String.format("Profit________________ %f", trade.calProfit()));
	}

	public Trade getTradeById(int id) {
		return tradeDao.queryById(id);
	}

	public Trade createTrade(TradeVO tradeVO) {
		Trade trade = toTrade(tradeVO);
		tradeDao.save(trade);
		return trade;
	}

	

	

	public List<TradeVO> getPendings() {
		List<TradeVO> VOs=new ArrayList<TradeVO>();
		List<Trade> trades =tradeDao.queryByStatus(TradeStatus.OPEN);
		trades.addAll(tradeDao.queryByStatus(TradeStatus.CREATED));
		trades.addAll(tradeDao.queryByStatus(TradeStatus.CLOSING));
		trades.sort(null);
		trades.forEach(trade->{
			VOs.add(toVO(trade));
		});
		return VOs;
	}

	private TradeVO toVO(Trade trade) {
		TradeVO VO=new TradeVO();
		VO.setId(trade.getId());
		
		VO.setStatus(trade.getStatus());
		VO.setType(trade.getType());
		VO.setPosition(trade.getPosition());
		VO.setCode(trade.getSecurity().getNameAbbreviation());
		VO.setBuyprice(numf.format(trade.getBuyPriceReal()));
		VO.setQuantity(trade.getQuantity());
		VO.setStarttime(datef.format(trade.getStart_time()));
		VO.setProfit(numf.format(trade.calProfit()));
		VO.setNowprice(numf.format(trade.getSecurity().latestPrice()));
		return VO;
	}

	public void closeOrCancleTrade(Trade trade) {
		
		if(trade.getStatus()==TradeStatus.CREATED)
			cancleTrade(trade);
		else if(trade.getStatus()==TradeStatus.OPEN) {
			closeTrade(trade);
		}
	}
	
	public void closeOrCancleTrade(TradeVO VO) {
		Trade trade=tradeDao.queryById(VO.getId());
		closeOrCancleTrade(trade);
	}

	public void cancleTrade(Trade trade) {
		tradeDao.update(trade);
		trade.setStatus(TradeStatus.CANCLED);
		trade.setEnd_time(new Date());
		tradeDao.update(trade);
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
				trade.setBuyPrice(Double.parseDouble(tradeVO.getBuyprice()));
			}
			return trade;
		}
}
