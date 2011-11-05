package com.irengine.checkpoint.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.irengine.checkpoint.dao.StockDao;
import com.irengine.checkpoint.entity.Stock;

@Component
public class HibernateTemplateStockDao implements StockDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	public void insert(Stock stock) {
		hibernateTemplate.persist(stock);
	}

	public void update(Stock stock) {
		hibernateTemplate.merge(stock);
	}

	public void delete(Stock stock) {
		hibernateTemplate.delete(stock);
	}

	@SuppressWarnings("unchecked")
	public List<Stock> get() {
		return hibernateTemplate.find("from Stock s");
	}

	@SuppressWarnings("rawtypes")
	public Stock findByInventoryCode(String iCode) {
		Stock found = null;
		String query = "from Stock s where s.inventoryCode =?";
		List ret = hibernateTemplate.find(query, iCode);
		if (ret != null && ret.size() > 0) {
			found = (Stock) ret.get(0);
		}

		return found;
	}

	@SuppressWarnings("unchecked")
	public List<Stock> findAvailableStockBySymbol(String symbol) {
		String query = "from Stock s where s.symbol =? and s.quantityAvailable>0";
		return hibernateTemplate.find(query, symbol);
	}

}
