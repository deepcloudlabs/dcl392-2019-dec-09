package com.example.fibonacci.service;

import javax.servlet.AsyncContext;

public class FibonacciTask implements Runnable {
	private int n;
	private AsyncContext asyncContext;

	public FibonacciTask(AsyncContext asyncContext,int n) {
		this.n = n;
		this.asyncContext = asyncContext;
	}

	@Override
	public void run() {
		asyncContext.getRequest().setAttribute("fibonacci", fib(n));
		asyncContext.complete();
	}

	private long fib(long n) {
		if (n == 0 || n == 1)
			return n;
		return fib(n - 1) + fib(n - 2);
	}

}
