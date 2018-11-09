package com.citi.swifttrading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citi.swifttrading.VO.OrderBookItemVO;
import com.citi.swifttrading.dao.PriceRepo;
import com.citi.swifttrading.generator.OrderBook;
import com.citi.swifttrading.generator.OrderBookItem;

@RestController
@RequestMapping("/security")
public class SecurityController {
	@Autowired
	PriceRepo priceRepo;

	@RequestMapping(value = "{abbr}/orderbook", method = RequestMethod.GET)
	public List<OrderBookItemVO> hello(@PathVariable("abbr") String abbr) {
		List<OrderBookItemVO> VOs = new ArrayList<>();
		OrderBook orderBook = priceRepo.getOrderBook(abbr);
		if (orderBook != null) {
			for (OrderBookItem item : priceRepo.getOrderBook(abbr).getOrderItem()) {
				VOs.add(toVO(abbr, item));
			}
		}

		return VOs;
	}

	private OrderBookItemVO toVO(String abbr, OrderBookItem item) {
		return new OrderBookItemVO(abbr, item.getBid(), item.getPrice(), item.getQty());
	}
}
