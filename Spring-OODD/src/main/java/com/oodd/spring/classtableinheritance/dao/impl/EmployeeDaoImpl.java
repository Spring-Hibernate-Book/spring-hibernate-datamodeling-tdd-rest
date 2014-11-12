package com.oodd.spring.classtableinheritance.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.classtableinheritance.dao.EmployeeDao;
import com.oodd.spring.classtableinheritance.entity.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl  implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void insert(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select employee from Employee employee order by employee.id desc").list();
	}
	@Override
	public Employee getById(Integer id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}
	@Override
	public void delete(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}
	@Override
	public void update(Employee employee) {
		sessionFactory.getCurrentSession().merge(employee);
	}
}