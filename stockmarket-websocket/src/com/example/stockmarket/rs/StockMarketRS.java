package com.example.stockmarket.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.stockmarket.dao.StockDao;
import com.example.stockmarket.entity.Stock;

@Path("/stocks")
@RequestScoped
public class StockMarketRS {
	@Inject
	private StockDao stockDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{symbol}")
	public Stock findBySymbol(@PathParam("symbol") String symbol) {
		return stockDao.findStockBySymbol(symbol);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Stock> findAll() {
		return stockDao.findStocks();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Stock addStock(Stock stock) {
		stockDao.createStock(stock);
		return stock;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Stock updateStock(Stock stock) {
		stockDao.updateStock(stock);
		return stock;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{symbol}")
	public Stock removeBySymbol(@PathParam("symbol") String symbol) {
		return stockDao.removeStockBySymbol(symbol).orElse(new Stock());
	}
}
