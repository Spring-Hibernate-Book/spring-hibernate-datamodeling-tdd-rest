package com.oodd.spring.onetomanyselfreference.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyselfreference.dao.CategoryDao;
import com.oodd.spring.onetomanyselfreference.entity.Category;
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(Category category) {
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public Category getById(Integer id) {
		return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
	}

	@Override
	public void delete(Category category) {
		sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	public void update(Category category) {
		sessionFactory.getCurrentSession().merge(category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select category from Category category order by id desc").list();
	}
}
