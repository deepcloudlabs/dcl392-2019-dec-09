package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class OrderSimulationService {
	private ThreadLocalRandom random = ThreadLocalRandom.current();
	@Inject
	private ProductService productSrv;

	@Schedule(second = "*/3",hour = "*", minute = "*",persistent = true)
	public void sendRandomOrder() {
		String randomSku = "p" + random.nextInt(1, 4);
		productSrv.order(randomSku, 3);
	}
}
