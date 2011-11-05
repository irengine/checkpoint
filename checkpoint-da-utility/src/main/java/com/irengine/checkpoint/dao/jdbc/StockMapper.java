package com.irengine.checkpoint.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.irengine.checkpoint.entity.Stock;
 
public class StockMapper implements RowMapper<Stock> { 
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException { 
         Stock stock = new Stock( 
                                 rs.getString("SYMBOL"), 
                                 rs.getString("INVENTORY_CODE"), 
                                 rs.getString("EXCHANGE_ID"), 
                                 rs.getFloat("PRICE_PER_SHARE"), 
                                 rs.getInt("QUANTITY_AVAILABLE"), 
                                 rs.getDate("PURCHASE_DATE") 
                         ); 
        stock.setId(rs.getInt("ID")); 
                         return stock; 
    } 
}