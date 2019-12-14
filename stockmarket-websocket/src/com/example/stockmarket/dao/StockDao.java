package com.example.stockmarket.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named
@Singleton
public class StockDao {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;

	public Stock findStockBySymbol(String symbol) {
		return entityManager.find(Stock.class, symbol);
	}

	public List<Stock> findStocks() {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class)
				.getResultList();
	}

	@Transactional
	public void createStock(Stock stock) {
		entityManager.persist(stock);
	}

	@Transactional
	public void updateStocks(List<Stock> stocks) {
		stocks.forEach(this::updateStock);
	}

	@Transactional
	public void updateStock(Stock stock) {
		String symbol = stock.getSymbol();
		Stock managed = entityManager.find(Stock.class, symbol);
		if (Objects.nonNull(managed)) {
			managed.setCompany(stock.getCompany());
			managed.setPrice(stock.getPrice());
		}
	}

	@Transactional
	public Optional<Stock> removeStockBySymbol(String symbol) {
		Stock managed = entityManager.find(Stock.class, symbol);
		if (Objects.nonNull(managed))
			entityManager.remove(managed);
		return Optional.ofNullable(managed);
	}

}