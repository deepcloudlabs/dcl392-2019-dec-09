package com.example.fibonacci.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.fibonacci.service.FibonacciService;

@WebServlet(urlPatterns = "/compute",asyncSupported = true)
public class FibonacciController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject private FibonacciService fibSrv;
       
    public FibonacciController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("fibonacci.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n=3;
		try {
			n = Integer.parseInt(request.getParameter("n"));
		} catch (NumberFormatException e) {
		}
		AsyncContext ac = request.startAsync();
		fibSrv.compute(ac,n);	
		ac.addListener(new AsyncListener() {
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
			}
			
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
			}
			
			@Override
			public void onError(AsyncEvent event) throws IOException {
			}
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				try {
					ServletRequest req = event.getAsyncContext().getRequest();
					req.getRequestDispatcher("fibonacci.jsp").forward(req, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}			       
			}
		});
	}

}
