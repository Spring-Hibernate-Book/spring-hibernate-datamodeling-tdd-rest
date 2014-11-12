package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dao;

import java.util.List;

import com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity.WorkerWorker;

public interface WorkerWorkerDao {
	public List<WorkerWorker> getAll();
	public List<WorkerWorker> isPresent(Integer workerId, Integer authorId);
	public void delete(WorkerWorker workerWorker);
	public void insert(WorkerWorker workerWorker);
}