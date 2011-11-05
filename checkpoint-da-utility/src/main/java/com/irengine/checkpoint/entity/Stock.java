package com.irengine.checkpoint.entity;

import java.util.Date;

public class Stock {
	private int id;
	private String inventoryCode;
	private String symbol;
	private String exchangeId;
	private float sharePrice;
	private int quantityAvailable;
	private Date purchaseDate;
	
	public Stock() {
		
	}

	public Stock(String symbol, String inventoryCode, String exchangeId, float sharePrice,
			int quantityAvailable, Date purchaseDate) {
		this.inventoryCode = inventoryCode;
		this.symbol = symbol;
		this.exchangeId = exchangeId;
		this.sharePrice = sharePrice;
		this.quantityAvailable = quantityAvailable;
		this.purchaseDate = purchaseDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public float getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(float sharePrice) {
		this.sharePrice = sharePrice;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
