package com.oodd.spring.manytomanyselfreferencewithjoinattribute.dto;


public class WorkerWorkerDto {

	private WorkerDto workerId1;
	private WorkerDto workerId2;
	private String relationshipType;
	
	public WorkerDto getWorkerId1() {
		return workerId1;
	}
	public void setWorkerId1(WorkerDto workerId1) {
		this.workerId1 = workerId1;
	}
	public WorkerDto getWorkerId2() {
		return workerId2;
	}
	public void setWorkerId2(WorkerDto workerId2) {
		this.workerId2 = workerId2;
	}
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
}