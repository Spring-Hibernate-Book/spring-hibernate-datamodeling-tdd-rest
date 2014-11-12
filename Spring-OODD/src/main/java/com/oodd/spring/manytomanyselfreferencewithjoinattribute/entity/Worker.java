package com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="WORKER")
public class Worker {
	
	private Integer id;
	private String name;
	private Set<WorkerWorker> workers1 = new HashSet<WorkerWorker>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="NAME", nullable=false, length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workerWorkerId.worker1", cascade=CascadeType.ALL)
	public Set<WorkerWorker> getWorkers1() {
		return workers1;
	}
	public void setWorkers1(Set<WorkerWorker> workers1) {
		this.workers1 = workers1;
	}
}