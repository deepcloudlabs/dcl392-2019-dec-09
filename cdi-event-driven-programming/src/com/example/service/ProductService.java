package com.example.service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.example.domain.Order;
import com.example.domain.Product;
import com.example.service.event.OutOfStockEvent;

@Named
@Singleton
public class ProductService {
	private Map<String, Product> products;
	@Inject
	private Event<OutOfStockEvent> event;

	@PostConstruct
	public void populate() {
		products = new ConcurrentHashMap<>();
		products.put("p1", new Product("p1", "aaa", 10, 100.));
		products.put("p2", new Product("p2", "bbb", 20, 150.));
		products.put("p3", new Product("p3", "ccc", 30, 75.));
	}

	public Optional<Order> order(String sku, int quantity) {
		Product product = products.get(sku);
		if (Objects.nonNull(product)) {
			if (product.getQuantity() < quantity) {
				OutOfStockEvent outOfStockEvent = new OutOfStockEvent(sku, quantity);
				System.err.println("Sending event: " + outOfStockEvent);
				event.fire(outOfStockEvent);
			} else {
				product.setQuantity(product.getQuantity() - quantity);
				return Optional.of(new Order());
			}
		}
		return Optional.empty();
	}
}
