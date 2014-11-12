package com.oodd.spring.classtableinheritance.dao;

import java.util.List;

import com.oodd.spring.classtableinheritance.entity.Employee;

public interface EmployeeDao {
	public void insert(Employee employee) ;
	public List<Employee> getAll();
	public Employee getById(Integer id);
	public void delete(Employee employee) ;
	public void update(Employee employee);
}