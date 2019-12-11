package com.example.service;

import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.service.event.OutOfStockEvent;

@Named
@Singleton
public class PurchaseService {

	public void handleOutOfStock(@Observes OutOfStockEvent event) {
		System.err.println("Event is received: " + event);
	}
}
