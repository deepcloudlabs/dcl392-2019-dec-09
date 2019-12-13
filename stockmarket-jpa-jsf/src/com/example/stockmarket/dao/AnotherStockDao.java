package com.example.stockmarket.dao;

import java.util.Objects;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.example.stockmarket.entity.Stock;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named
@Singleton
public class AnotherStockDao {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;

	@Transactional(value = TxType.REQUIRED)
	public void updateStock(Stock stock) {
		String symbol = stock.getSymbol();
		Stock managed = entityManager.find(Stock.class, symbol);
		if (Objects.nonNull(managed)) {
			managed.setCompany(stock.getCompany());
			managed.setPrice(stock.getPrice());
		}
	}

}
