package com.irengine.checkpoint.dao;

import java.util.List;

import com.irengine.checkpoint.entity.Stock;

public interface StockDao {

	public void insert(Stock stock);

	public void update(Stock stock);

	public void delete(Stock product);

	public Stock findByInventoryCode(String iCode);

	public List<Stock> findAvailableStockBySymbol(String symbol);

	public List<Stock> get();
}
