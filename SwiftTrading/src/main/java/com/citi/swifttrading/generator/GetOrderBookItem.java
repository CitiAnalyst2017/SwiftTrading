package com.citi.swifttrading.generator;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GetOrderBookItem {
//	private static List<OrderBookItem> orderDatas;

	public  List<OrderBookItem> generateBook() {
		OrderBookItem obItem = null;
		List<OrderBookItem> orderDatas = new ArrayList<OrderBookItem>();
		Random rand = new Random();
		DecimalFormat df = new DecimalFormat("#.00");
		double price = 50+rand.nextFloat()*5;
		for (int i = 0; i < 20; i++) {
			obItem = new OrderBookItem();
			int bqty = rand.nextInt(20) + 5;
			obItem.setPrice(Double.parseDouble(df.format(price)));
			obItem.setQty(bqty);
			orderDatas.add(obItem);
			price = price+(0.712 + Math.floor(Math.random())) * 0.99;
		}

		Collections.sort(orderDatas, new Comparator<OrderBookItem>() {
			public int compare(OrderBookItem item1, OrderBookItem item2) {
				return item2.getPrice().compareTo(item1.getPrice());
			}
		});
		int pos=rand.nextInt(10);
		orderDatas = orderDatas.subList(pos, pos+10);
		for (int i = 0; i < orderDatas.size(); i++) {
			if (i < 5) {
				orderDatas.get(i).setBid("Offer" + (5 - i));
			} else {
				orderDatas.get(i).setBid("Bid" + (i - 4));
			}
		}
		
		return orderDatas;
	}
	
	public static void main(String[] args) {
		GetOrderBookItem gtd = new GetOrderBookItem();
		List<OrderBookItem> bb = new ArrayList<OrderBookItem>();	
		bb=gtd.generateBook();
		//bb = gtd.GetTradeDatas();
		for(int i=0;i<bb.size();i++){		
			System.out.println(bb.get(i).toString());
		}
	}

}
