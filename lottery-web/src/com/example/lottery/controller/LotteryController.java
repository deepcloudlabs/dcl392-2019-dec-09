package com.example.lottery.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lottery.aspect.Audit;
import com.example.lottery.model.LotteryViewModel;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.impl.StudyMultipleImpl;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebServlet(urlPatterns = "/draw")
public class LotteryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject	private LotteryViewModel model;
	@EJB private LotteryService lotteryService;
     @Inject private StudyMultipleImpl impl;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("lottery.jsp").forward(request, response);
	}

	@Audit
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int n = 1;
		impl.fun();
		try {
			n = Integer.parseInt(request.getParameter("n"));
		} catch (NumberFormatException e) {
		}
		List<List<Integer>> numbers = lotteryService.draw(n);
		model.getNumbers().addAll(numbers);
		System.err.println(model.getClass());
		System.err.println(lotteryService.getClass());
		request.getRequestDispatcher("lottery.jsp").forward(request, response);
	}

}
