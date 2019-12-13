package com.example.stockmarket.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
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
public class StockMarketDao {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;
	@Inject
	private AnotherStockDao anotherStockDao;

	public Stock findStockBySymbol(String symbol) {
		return entityManager.find(Stock.class, symbol);
	}

	public List<Stock> findStocks(int pageNo, int pageSize) {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class).setFirstResult(pageNo * pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Transactional
	public void createStock(Stock stock) {
		entityManager.persist(stock);
	}

	@Transactional
	public void updateStocks(List<Stock> stocks) {
		stocks.parallelStream().forEach(anotherStockDao::updateStock);
	}

	@Transactional
	public Optional<Stock> removeStockBySymbol(String symbol) {
		Stock managed = entityManager.find(Stock.class, symbol);
		if (Objects.nonNull(managed))
			entityManager.remove(managed);
		return Optional.ofNullable(managed);
	}

}