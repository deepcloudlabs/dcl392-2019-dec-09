package com.example.stockmarket.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.stockmarket.service.StockMarketSimulator;

/**
 * Servlet implementation class StockMarketController
 */
@WebServlet(urlPatterns = "/test")
public class StockMarketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject private StockMarketSimulator simulator;
    
    public StockMarketController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println("<h1>"+simulator.getClass().toString()+"</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
