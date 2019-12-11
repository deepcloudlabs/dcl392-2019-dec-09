package com.example.service.event;

public class OutOfStockEvent {
	private String sku;
	private int quantity;

	public OutOfStockEvent() {
	}

	public OutOfStockEvent(String sku, int quantity) {
		this.sku = sku;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "OutOfStockEvent [sku=" + sku + ", quantity=" + quantity + "]";
	}

}
