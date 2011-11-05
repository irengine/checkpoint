package com.irengine.checkpoint.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.PreparedStatementCreator; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.support.GeneratedKeyHolder; 
import org.springframework.jdbc.support.KeyHolder; 
import org.springframework.stereotype.Component; 
 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 

import com.irengine.checkpoint.dao.StockDao;
import com.irengine.checkpoint.entity.Stock;

@Component
public class JdbcTemplateStockDao implements StockDao {
	private JdbcTemplate jdbcTemplate; 
	 
    @Autowired  
    public void setJdbcTemplate(JdbcTemplate t){  
        this.jdbcTemplate = t; 
    } 
 
    public void insert(Stock stock) { 
        KeyHolder keyHolder = new GeneratedKeyHolder(); 
        this.jdbcTemplate.update(new PSStockCreater(stock), keyHolder); 
 
        stock.setId(keyHolder.getKey().intValue()); 
    } 
 
    public void update(final Stock stock) { 
        jdbcTemplate.update(new PreparedStatementCreator() { 
            public PreparedStatement createPreparedStatement(Connection connection) 
                    throws SQLException { 
                String sql = "UPDATE STOCKS SET SYMBOL = ?, INVENTORY_CODE = ?, " + 
                        "PRICE_PER_SHARE = ?, QUANTITY_AVAILABLE = ?, " + 
                        "EXCHANGE_ID = ?, PURCHASE_DATE = ? where ID = ?"; 
                PreparedStatement ps = connection.prepareStatement(sql); 
                ps.setString(1, stock.getSymbol()); 
                ps.setString(2, stock.getInventoryCode()); 
                ps.setFloat(3, stock.getSharePrice()); 
                ps.setFloat(4, stock.getQuantityAvailable()); 
                ps.setString(5, stock.getExchangeId()); 
                ps.setDate(6, new java.sql.Date(stock.getPurchaseDate().getTime())); 
                ps.setInt(7, stock.getId()); 
                return ps; 
            } 
        }); 
    } 
 
    public void delete(Stock stock) { 
        jdbcTemplate.update("delete from STOCKS where id = ?", stock.getId()); 
    } 
 
    public List<Stock> findAvailableStockBySymbol(String symbol) { 
        String sql = " SELECT * from STOCKS" + 
                " WHERE SYMBOL = ? order by PRICE_PER_SHARE"; 
        List<Stock> ret = jdbcTemplate.query( 
                sql, 
                new Object[]{symbol}, 
                new RowMapper<Stock>() { 
                    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException { 
                        Stock s = new Stock( 
                                rs.getString("SYMBOL"), 
                                rs.getString("INVENTORY_CODE"), 
                                rs.getString("EXCHANGE_ID"), 
                                rs.getFloat("PRICE_PER_SHARE"), 
                                rs.getInt("QUANTITY_AVAILABLE"), 
                                rs.getDate("PURCHASE_DATE") 
                        ); 
                        return s; 
                    } 
                }); 
 
        return ret; 
    } 
 
    public List<Stock> get() { 
        List<Stock> ret = jdbcTemplate.query("select * from STOCKS", new StockMapper()); 
        return ret; 
    } 
 
    public Stock findByInventoryCode(String iCode) { 
        String sql = " SELECT * from STOCKS" + 
                " WHERE INVENTORY_CODE = ?"; 
        return jdbcTemplate.queryForObject(sql, 
                new Object[]{iCode}, 
                new StockMapper()); 
    }
}
