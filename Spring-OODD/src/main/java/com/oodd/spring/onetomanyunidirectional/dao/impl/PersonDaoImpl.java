package com.oodd.spring.onetomanyunidirectional.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.onetomanyunidirectional.dao.PersonDao;
import com.oodd.spring.onetomanyunidirectional.entity.Person;
import com.oodd.spring.onetomanyunidirectional.entity.Phone;
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao{
	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Person person) {
		sessionFactory.getCurrentSession().save(person);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		return (List<Person>)sessionFactory.getCurrentSession().
				createQuery("select person from Person person order by person.id desc").list();
	}

	@Override
	public Person getById(Integer id) {
		return (Person)sessionFactory.getCurrentSession().get(Person.class,id);
	}

	@Override
	public void delete(Person person) {
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public void update(Person person) {
		sessionFactory.getCurrentSession().merge(person);
	}
}
