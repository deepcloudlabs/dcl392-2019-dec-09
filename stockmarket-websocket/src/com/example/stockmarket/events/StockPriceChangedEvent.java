package com.example.stockmarket.events;

import javax.json.bind.annotation.JsonbProperty;

public class StockPriceChangedEvent {
	@JsonbProperty("s")
	private String symbol;
	@JsonbProperty("old")
	private double oldPrice;
	@JsonbProperty("price")
	private double newPrice;

	public StockPriceChangedEvent() {
	}

	public StockPriceChangedEvent(String symbol, double oldPrice, double newPrice) {
		this.symbol = symbol;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	@Override
	public String toString() {
		return "StockPriceChangedEvent [symbol=" + symbol + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + "]";
	}

}
