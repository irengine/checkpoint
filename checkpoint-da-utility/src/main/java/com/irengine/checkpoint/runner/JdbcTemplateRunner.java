package com.irengine.checkpoint.runner;

import java.util.Calendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.irengine.checkpoint.dao.StockDao;
import com.irengine.checkpoint.entity.Stock;
 
public class JdbcTemplateRunner { 
    public static void main(String[] args) { 
        GenericApplicationContext context = 
                new AnnotationConfigApplicationContext( 
                        "com.irengine.checkpoint.dao.jdbc"); 
        StockDao stockDao = 
                context.getBean("jdbcTemplateStockDao", StockDao.class); 
        Stock stock = new Stock("ORAC", "JDBCTPL0001", "QQQQ", 120.0f, 1100, 
                Calendar.getInstance().getTime()); 
        stockDao.insert(stock); 
 
        stock = new Stock("APRS", "JDBCTPL0002", "QQQQ", 150.00F, 1500, 
                Calendar.getInstance().getTime()); 
        stockDao.insert(stock); 
 
        stock = stockDao.findByInventoryCode("JDBCTPL0001"); 
 
        if (stock != null) { 
            System.out.println("Template Version"); 
            System.out.println("Stock Symbol :" + stock.getSymbol()); 
            System.out.println("Inventory Code :" + stock.getInventoryCode()); 
            System.out.println("purchased price:" + stock.getSharePrice()); 
            System.out.println("Exchange ID:" + stock.getExchangeId()); 
            System.out.println("Quantity Available :" + stock.getQuantityAvailable()); 
        } 
    } 
} 
