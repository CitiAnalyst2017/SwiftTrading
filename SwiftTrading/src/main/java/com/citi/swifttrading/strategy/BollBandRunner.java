package com.citi.swifttrading.strategy;

import com.citi.swifttrading.domain.BollBand;
import com.citi.swifttrading.enumration.TradeStatus;
import com.citi.swifttrading.service.trade.TradeManager;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class BollBandRunner extends StrategyRunner {

	private int period;
	private double std;

	public BollBandRunner(TradeManager tradeManager, BollBand bollBand) {
		super(tradeManager, bollBand);
		this.period = bollBand.getPeriod();
		this.std = bollBand.getStd();
		this.setName("MovingAverageRunner for" +bollBand.getStrategyName());
	}

	private int getStatus() {
		if (target.latestPrice() - target.getAverage(period) > std * target.getStd(period))
			return 1;
		else if (target.latestPrice() - target.getAverage(period) < std * target.getStd(period))
			return -1;
		return 0;
	}

	@Override
	public void run() {
		while (true) {
			if (request != null) {
				if (request.getStatus() ==TradeStatus.CREATED||request.getStatus() == TradeStatus.OPEN && takeProfit()) {
					closeRequest(request);
				}
				request = null;
			} else{
				if (getStatus() == 1) {
					request = createLongRequest(target, exit);
				} else if(getStatus()==2){
					request = createShortRequest(target, exit);
				}
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				log.info(e.toString());
				e.printStackTrace();
			}
		}

	}
}
