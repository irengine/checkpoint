package com.irengine.checkpoint.runner;

import java.util.Calendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.irengine.checkpoint.dao.StockDao;
import com.irengine.checkpoint.dao.hibernate.HibernateTemplateStockDao;
import com.irengine.checkpoint.entity.Stock;

public class HibernateTemplateRunner {
    public static void main(String[] args) { 
        GenericApplicationContext context =  
            new AnnotationConfigApplicationContext( 
                "com.irengine.checkpoint.dao.hibernate"); 
 
        StockDao stockDao = 
                context.getBean("hibernateTemplateStockDao", HibernateTemplateStockDao.class); 
        Stock stock = 
                new Stock("ORAC", "HIBERNATEMAIN0001", "QQQQ", 120.0f, 1100, 
                        Calendar.getInstance().getTime()); 
        stockDao.insert(stock); 
 
        stock = stockDao.findByInventoryCode("HIBERNATEMAIN0001"); 
 
        if (stock != null) { 
            System.out.println("Stock Symbol :" + stock.getSymbol()); 
            System.out.println("Inventory Code :" + stock.getInventoryCode()); 
            System.out.println("purchased price:" + stock.getSharePrice()); 
            System.out.println("Exchange ID:" + stock.getExchangeId()); 
            System.out.println("Quantity Available :" + stock.getQuantityAvailable()); 
        } 
    }
}
