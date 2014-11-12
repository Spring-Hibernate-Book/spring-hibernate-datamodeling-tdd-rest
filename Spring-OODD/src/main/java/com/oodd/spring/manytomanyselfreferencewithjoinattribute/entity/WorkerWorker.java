package com.oodd.spring.manytomanyselfreferencewithjoinattribute.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="WORKER_WORKER")
@AssociationOverrides({
    @AssociationOverride(name = "workerWorkerId.worker1", joinColumns = @JoinColumn(name = "WORKER1_ID", referencedColumnName="ID")),
    @AssociationOverride(name = "workerWorkerId.worker2", joinColumns = @JoinColumn(name = "WORKER2_ID", referencedColumnName="ID")) })
public class WorkerWorker implements Serializable {
	
	private static final long serialVersionUID = 2165276994610614854L;
	
	private WorkerWorkerId workerWorkerId = new WorkerWorkerId();
	private String relationshipType;
	
	@EmbeddedId
	public WorkerWorkerId getWorkerWorkerId() {
		return workerWorkerId;
	}
	public void setWorkerWorkerId(WorkerWorkerId workerWorkerId) {
		this.workerWorkerId = workerWorkerId;
	}
	
	@Transient
	public Worker getWorker1() {
		return getWorkerWorkerId().getWorker1();
	}

	public void setWorker1(Worker worker1) {
		getWorkerWorkerId().setWorker1(worker1);
	}
	
	@Transient
	public Worker getWorker2() {
		return getWorkerWorkerId().getWorker2();
	}

	public void setWorker2(Worker worker2) {
		getWorkerWorkerId().setWorker1(worker2);
	}
	
	@Column(name="RELATIONSHIP_TYPE", nullable=false, length=100)
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		WorkerWorker that = (WorkerWorker) o;

		if (getWorkerWorkerId() != null ? !getWorkerWorkerId().equals(that.getWorkerWorkerId())
				: that.getWorkerWorkerId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getWorkerWorkerId() != null ? getWorkerWorkerId().hashCode() : 0);
	}
}