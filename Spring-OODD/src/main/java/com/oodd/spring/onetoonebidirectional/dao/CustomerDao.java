package com.oodd.spring.onetoonebidirectional.dao;

import java.util.List;

import com.oodd.spring.onetoonebidirectional.entity.Customer;

public interface CustomerDao {
	public void insert(Customer customer);
	public List<Customer> getAll();
	public Customer getById(int id);
	public void delete(Customer customer);
	public void update(Customer customer);
}
