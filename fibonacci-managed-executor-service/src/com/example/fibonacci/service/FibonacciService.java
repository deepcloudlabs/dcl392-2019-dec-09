package com.example.fibonacci.service;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;

@Singleton
public class FibonacciService {
	@Resource(mappedName = "java:jboss/ee/concurrency/executor2")
	private ManagedExecutorService executorService;
 
	public void compute(AsyncContext ac,int n) {
		executorService.execute(new FibonacciTask(ac,n));
	}

}
