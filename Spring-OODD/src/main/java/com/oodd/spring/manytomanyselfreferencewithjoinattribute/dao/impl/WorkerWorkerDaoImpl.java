package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao.WorkerWorkerDao;
import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorker;

@Repository
@Transactional
public class WorkerWorkerDaoImpl implements WorkerWorkerDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerWorker> getAll() {
		return sessionFactory.getCurrentSession().createQuery("select distinct ww from WorkerWorker ww").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerWorker> isPresent(Integer workerId1, Integer workerId2) {
		String hql = "select distinct ww from WorkerWorker ww where ww.workerWorkerId.worker1.id=:workerId1 and ww.workerWorkerId.worker2.id=:workerId2";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("workerId1", workerId1);
		query.setParameter("workerId2", workerId2);
		return query.list();
	}
	
	@Override
	public void delete(WorkerWorker workerWorker) {
		sessionFactory.getCurrentSession().delete(workerWorker);
	}

	@Override
	public void insert(WorkerWorker workerWorker) {
		sessionFactory.getCurrentSession().save(workerWorker);
	}
}