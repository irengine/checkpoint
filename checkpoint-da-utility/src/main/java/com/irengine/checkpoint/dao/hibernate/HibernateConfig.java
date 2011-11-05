package com.irengine.checkpoint.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.ImportResource; 
import org.springframework.core.io.Resource; 
import org.springframework.orm.hibernate3.HibernateTemplate; 
import org.springframework.orm.hibernate3.LocalSessionFactoryBean; 

import com.irengine.checkpoint.DataAccessBaseConfig;
 
@Configuration 
@ImportResource("classpath:META-INF/checkpoint-da-utility.xml") 
public class HibernateConfig extends DataAccessBaseConfig { 
 
    @Value("hibernate.cfg.xml") 
    private Resource hibernateConfigResource; 
 
    Resource hibernateConfigResource() { 
        return hibernateConfigResource; 
    } 
 
    // Local Session Factory for getting hibernate connections 
    @Bean 
    LocalSessionFactoryBean sessionFactory() { 
        LocalSessionFactoryBean sb = new LocalSessionFactoryBean(); 
        sb.setDataSource(dataSource()); 
        sb.setConfigLocation(hibernateConfigResource()); 
        return sb; 
    } 
 
    // Hibernate Template 
    @Bean 
    public HibernateTemplate hibernateTemplate() { 
        return new HibernateTemplate((SessionFactory) sessionFactory().getObject());
    } 
}
