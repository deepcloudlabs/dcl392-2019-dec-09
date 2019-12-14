package com.example.stockmarket.service.scheduled;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.events.StockPriceChangedEvent;

@Stateless
public class ScheduledService {
	@Inject
	private StockDao stockDao;
	private static final ThreadLocalRandom random = ThreadLocalRandom.current();
	@Inject
	private Event<StockPriceChangedEvent> event;

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void updateStockPrices() {
		stockDao.findStocks().forEach(stock -> {
			double oldPrice = stock.getPrice();
			double newPrice = oldPrice * (1. + (random.nextDouble() - 0.5) * 0.01);
			String symbol = stock.getSymbol();
			stock.setPrice(newPrice);
			event.fire(new StockPriceChangedEvent(symbol, oldPrice, newPrice));
		});
	}
}
