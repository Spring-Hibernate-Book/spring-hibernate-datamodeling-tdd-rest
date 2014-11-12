package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.WorkerDao;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.Worker;

@Service
@Transactional
public class WorkerDaoImpl implements WorkerDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@Override
	public void insert(Worker worker) {
		sessionFactory.getCurrentSession().save(worker);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Worker> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select worker from Worker worker order by worker.id desc").list();
	}

	@Override
	public Worker getById(Integer id) {
		return (Worker) sessionFactory.getCurrentSession().get(Worker.class,id);
	}

	@Override
	public void delete(Worker worker) {
		sessionFactory.getCurrentSession().delete(worker);
	}

	@Override
	public void update(Worker worker) {
		sessionFactory.getCurrentSession().merge(worker);
	}
}