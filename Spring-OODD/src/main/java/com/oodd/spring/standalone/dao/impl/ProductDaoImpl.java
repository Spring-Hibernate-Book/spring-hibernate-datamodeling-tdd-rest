package com.oodd.spring.standalone.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.oodd.spring.standalone.dao.ProductDao;
import com.oodd.spring.standalone.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl  implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select product from Product product order by product.id desc").list();
	}
	@Override
	public Product getById(Integer id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}
	@Override
	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
	}
	@Override
	public void update(Product product) {
		sessionFactory.getCurrentSession().merge(product);
	}
}
