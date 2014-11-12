package com.oodd.spring.standalone.dao;

import java.util.List;
import com.oodd.spring.standalone.entity.Product;

public interface ProductDao {
	public void insert(Product product ) ;
	public List<Product> getAll();
	public Product getById(Integer id);
	public void delete(Product product) ;
	public void update(Product product);
}
