package com.example.stockmarket.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.stockmarket.dao.StockMarketDao;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.service.StockMarketSimulator;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebServlet(urlPatterns = "/test")
public class StockMarketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private StockMarketSimulator simulator;
	@Inject
	private StockMarketDao stockMarketDao;

	public StockMarketController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getOutputStream().println("<h1>" + simulator.getClass().toString() + "</h1>");
		Stock stock = new Stock();
		stock.setSymbol("1ABC");
		stock.setPrice(100);
		stock.setDescription("Description");
		stock.setCompany("My Company");
		stockMarketDao.createStock(stock);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
