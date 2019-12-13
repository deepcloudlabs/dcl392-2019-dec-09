package com.example.stockmarket.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.example.stockmarket.dao.StockMarketDao;
import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Singleton
public class StockMarketSimulator {
	@Inject
	private StockMarketDao stockMarketDao;
	private ThreadLocalRandom random = ThreadLocalRandom.current();

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void updateStocks() {
		List<Stock> stocks = stockMarketDao.findStocks(0, 10);
		stocks.forEach(stock -> {
			stock.setPrice(stock.getPrice() * (1. + (random.nextDouble() - 0.5) * 0.01));
		});
		stockMarketDao.updateStocks(stocks);
	}
}
