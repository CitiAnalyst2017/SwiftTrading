package com.citi.swifttrading.service.trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import lombok.Setter;

@Setter
public class SecurityUpdater extends Thread {

	List<Double> target;
	List<Double> prices = new ArrayList<>();
	private static String a = "50.0000,50.3141,50.6279,50.9411,51.2533,51.5643,51.8738,52.1814,52.4869,52.7899,53.0902,53.3874,53.6812,53.9715,54.2578,54.5399,54.8175,55.0904,55.3583,55.6208,55.8779,56.1291,56.3742,56.6131,56.8455,57.0711,57.2897,57.5011,57.7051,57.9016,58.0902,58.2708,58.4433,58.6074,58.7631,58.9101,59.0483,59.1775,59.2978,59.4088,59.5106,59.6029,59.6858,59.7592,59.8229,59.8769,59.9211,59.9556,59.9803,59.9951,60.0000,59.9951,59.9803,59.9556,59.9211,59.8769,59.8229,59.7592,59.6858,59.6029,59.5106,59.4088,59.2978,59.1775,59.0483,58.9101,58.7631,58.6074,58.4433,58.2708,58.0902,57.9016,57.7051,57.5011,57.2897,57.0711,56.8455,56.6131,56.3742,56.1291,55.8779,55.6208,55.3583,55.0904,54.8175,54.5399,54.2578,53.9715,53.6812,53.3874,53.0902,52.7899,52.4869,52.1814,51.8738,51.5643,51.2533,50.9411,50.6279,50.3141,50.0000,49.6859,49.3721,49.0589,48.7467,48.4357,48.1262,47.8186,47.5131,47.2101,46.9098,46.6126,46.3188,46.0285,45.7422,45.4601,45.1825,44.9096,44.6417,44.3792,44.1221,43.8709,43.6258,43.3869,43.1545,42.9289,42.7103,42.4989,42.2949,42.0984,41.9098,41.7292,41.5567,41.3926,41.2369,41.0899,40.9517,40.8225,40.7022,40.5912,40.4894,40.3971,40.3142,40.2408,40.1771,40.1231,40.0789,40.0444,40.0197,40.0049,40.0000,40.0049,40.0197,40.0444,40.0789,40.1231,40.1771,40.2408,40.3142,40.3971,40.4894,40.5912,40.7022,40.8225,40.9517,41.0899,41.2369,41.3926,41.5567,41.7292,41.9098,42.0984,42.2949,42.4989,42.7103,42.9289,43.1545,43.3869,43.6258,43.8709,44.1221,44.3792,44.6417,44.9096,45.1825,45.4601,45.7422,46.0285,46.3188,46.6126,46.9098,47.2101,47.5131,47.8186,48.1262,48.4357,48.7467,49.0589,49.3721,49.6859,50.0000,50.3141,50.6279,50.9411,51.2533,51.5643,51.8738,52.1814,52.4869,52.7899,53.0902,53.3874,53.6812,53.9715,54.2578,54.5399,54.8175,55.0904,55.3583,55.6208,55.8779,56.1291,56.3742,56.6131,56.8455,57.0711,57.2897,57.5011,57.7051,57.9016,58.0902,58.2708,58.4433,58.6074,58.7631,58.9101,59.0483,59.1775,59.2978,59.4088,59.5106,59.6029,59.6858,59.7592,59.8229,59.8769,59.9211,59.9556,59.9803,59.9951,60.0000,59.9951,59.9803,59.9556,59.9211,59.8769,59.8229,59.7592,59.6858,59.6029,59.5106,59.4088,59.2978,59.1775,59.0483,58.9101,58.7631,58.6074,58.4433,58.2708,58.0902,57.9016,57.7051,57.5011,57.2897,57.0711,56.8455,56.6131,56.3742,56.1291,55.8779,55.6208,55.3583,55.0904,54.8175,54.5399,54.2578,53.9715,53.6812,53.3874,53.0902,52.7899,52.4869,52.1814,51.8738,51.5643,51.2533,50.9411,50.6279,50.3141,50.0000,49.6859,49.3721,49.0589,48.7467,48.4357,48.1262,47.8186,47.5131,47.2101,46.9098,46.6126,46.3188,46.0285,45.7422,45.4601,45.1825,44.9096,44.6417,44.3792,44.1221,43.8709,43.6258,43.3869,43.1545,42.9289,42.7103,42.4989,42.2949,42.0984,41.9098,41.7292,41.5567,41.3926,41.2369,41.0899,40.9517,40.8225,40.7022,40.5912,40.4894,40.3971,40.3142,40.2408,40.1771,40.1231,40.0789,40.0444,40.0197,40.0049,40.0000,40.0049,40.0197,40.0444,40.0789,40.1231,40.1771,40.2408,40.3142,40.3971,40.4894,40.5912,40.7022,40.8225,40.9517,41.0899,41.2369,41.3926,41.5567,41.7292,41.9098,42.0984,42.2949,42.4989,42.7103,42.9289,43.1545,43.3869,43.6258,43.8709,44.1221,44.3792,44.6417,44.9096,45.1825,45.4601,45.7422,46.0285,46.3188,46.6126,46.9098,47.2101,47.5131,47.8186,48.1262,48.4357,48.7467,49.0589,49.3721,49.6859,50.0000";

	public SecurityUpdater(List<Double> target) {
		this.target = target;
		Arrays.asList(a.split(",")).forEach(i -> prices.add(Double.parseDouble(i)));
	}

	@Override
	public void run() {
		ListIterator<Double> iter = prices.listIterator();
		while (true) {

			
			while (iter.hasNext()) {
				target.add(iter.next());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (iter.hasPrevious()) {
				target.add(iter.previous());
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
