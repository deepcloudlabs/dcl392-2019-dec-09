package com.example.stockmarket.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Stateless
@LocalBean
public class StockProducer {
	@Inject
	private StockDao stockDao;
	private static final ThreadLocalRandom random = ThreadLocalRandom.current();
	@Inject
	private JMSContext context;
	@Resource(mappedName = "java:jboss/jms/stockQueue")
	private Queue stockQueue;

	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
	public void sendStockMessages() {
		stockDao.findAll().forEach(stock -> {
			stock.setPrice(stock.getPrice() * (1. + (random.nextDouble() - 0.5) * 0.01));
			context.createProducer().send(stockQueue, createJson(stock));
		});
	}

	private String createJson(Stock stock) {
		return Json.createObjectBuilder().add("symbol", stock.getSymbol()).add("price", stock.getPrice()).build()
				.toString();
	}
}
