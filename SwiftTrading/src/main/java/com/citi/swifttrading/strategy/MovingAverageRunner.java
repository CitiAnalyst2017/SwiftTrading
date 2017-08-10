package com.citi.swifttrading.strategy;

import com.citi.swifttrading.domain.MovingAverage;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class MovingAverageRunner extends StrategyRunner {

	private int longPeriod;
	private int shortPeriod;

	public MovingAverageRunner(TradeManager tradeManager, MovingAverage movingAverage) {
		super(tradeManager, movingAverage);
		this.longPeriod = movingAverage.getLongPeriod();
		this.shortPeriod = movingAverage.getShortPeriod();
		this.setName("MovingAverageRunner for"+movingAverage.getStrategyName());
	}

	private boolean getStatus() {
		return target.getAverage(longPeriod) > target.getAverage(shortPeriod);
	}

	@Override
	public void run() {
		boolean status = getStatus();
		while (true) {
			 synchronized (lock) {
				 while(suspended) {
				 try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
			
				boolean newStatus = getStatus();
				if (request != null) {
					if (request.getStatus() ==TradeStatus.CREATED||request.getStatus() == TradeStatus.OPEN && status != newStatus || takeProfit()) {
						closeRequest(request);
					}
					request = null;
				} else if (status != newStatus) {
					if (status) {
						request = createLongRequest(target, exit);
					} else {
						request = createShortRequest(target, exit);
					}
				}
	
				status = newStatus;
	
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.info(e.toString());
					e.printStackTrace();
				}
			 }
		}

	}

}
