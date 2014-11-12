package com.oodd.spring.onetoonebidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetoonebidirectional.dao.CustomerDao;
import com.oodd.spring.onetoonebidirectional.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		return sessionFactory.getCurrentSession().
				createQuery("select customer from Customer customer order by customer.id desc").list();
	}

	@Override
	public Customer getById(int id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class,id);
	}

	@Override
	public void delete(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		sessionFactory.getCurrentSession().merge(customer);
	}
}
