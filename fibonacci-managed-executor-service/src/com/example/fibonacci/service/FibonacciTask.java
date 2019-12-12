package com.example.fibonacci.service;

import java.util.Objects;
import java.util.WeakHashMap;

import javax.servlet.AsyncContext;

public class FibonacciTask implements Runnable {
	private int n;
	private AsyncContext asyncContext;
	private static final WeakHashMap<Long, Long> cache = new WeakHashMap<>();
	static {
		cache.put(0L, 1L);
		cache.put(1L, 1L);
	}

	public FibonacciTask(AsyncContext asyncContext, int n) {
		this.n = n;
		this.asyncContext = asyncContext;
	}

	@Override
	public void run() {
		asyncContext.getRequest().setAttribute("fibonacci", fib(n));
		asyncContext.complete();
	}

	private long fib(long n) {
		Long value = cache.get(n);
		if (Objects.isNull(value)) {
			value = fib(n - 1) + fib(n - 2);
			cache.put(n, value);
		}
		return value;
	}

}
