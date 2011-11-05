package com.irengine.checkpoint.dao.jdbc;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.irengine.checkpoint.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PSStockCreater implements PreparedStatementCreator {
	private Stock stock;

	public PSStockCreater(Stock stock) {
		this.stock = stock;
	}

	public PreparedStatement createPreparedStatement(Connection connection)
			throws SQLException {
		String sql = "INSERT INTO "
				+ "STOCKS (SYMBOL, INVENTORY_CODE, PRICE_PER_SHARE,"
				+ "QUANTITY_AVAILABLE, EXCHANGE_ID, PURCHASE_DATE) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, stock.getSymbol());
		ps.setString(2, stock.getInventoryCode());
		ps.setFloat(3, stock.getSharePrice());
		ps.setFloat(4, stock.getQuantityAvailable());
		ps.setString(5, stock.getExchangeId());
		ps.setDate(6, new java.sql.Date(stock.getPurchaseDate().getTime()));
		return ps;
	}
}
