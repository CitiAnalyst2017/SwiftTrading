package com.citi.swifttrading.generator;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class GetTradeData{
	private static List<Trading> tradeDatas = new ArrayList<Trading>();
	private  long timeInterval = 0;
	public  String[] getSymbol() {
		String[] symbols = null;
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Symbol.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			symbols = p.getProperty("value").trim().split(",");
		//	System.out.println(Arrays.toString(symbols));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return symbols;
	}
	public static void generateBook(String symbol, double price) {
		Trading trade = new Trading();
		Trading trade2 = new Trading();	
		Random rand = new Random();
		// Bids
		double bprice = price * 0.97;
		int bdepth = rand.nextInt(10) + 1;
		for (int i = 0; i < bdepth; i++) {
			int bqty = rand.nextInt(20) + 5;
			trade2.setSymbol(symbol);
			trade2.setBide("B");
			trade.setPrice(price);
			trade.setQty(bqty);
			tradeDatas.add(trade);			
			bprice++;
		}	
		// Offers
		int odepth = rand.nextInt(10) + 1;
		double oprice = price * 1.03;
		for (int i = 0; i < odepth; i++) {
			int oqty = rand.nextInt(20) + 5;
			trade2.setSymbol(symbol);
			trade2.setBide("O");
			trade2.setPrice(price);
			trade2.setQty(oqty);
			tradeDatas.add(trade2);
			oprice++;
		}
	}


	public List<Trading> Generator() {
		// TODO Auto-generated method stub
		GetTradeData gtd = new GetTradeData();
		Random rand = new Random();
		for (String sym:gtd.getSymbol()) {
			int price = rand.nextInt(2) + 50;
			generateBook(sym, price);
		}
		System.out.println("tradeDatas.size():"+tradeDatas.size());
		return tradeDatas;
	}
	
	public synchronized void GetTradeDatas() {
	
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Generator.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			timeInterval =Integer.parseInt(p.getProperty("value")) ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable runnable = new Runnable() {
			public void run() {
				int count = 0;
				while (count<=3) {
				//while (true) {
					GetTradeData gtd = new GetTradeData();
					gtd.Generator();
					try {
						Thread.sleep(timeInterval);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
					count=count+1;
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetTradeData gtd = new GetTradeData();
		gtd.GetTradeDatas();
	}

}
