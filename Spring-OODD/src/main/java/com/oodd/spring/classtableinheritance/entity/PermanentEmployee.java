package com.oodd.spring.classtableinheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="PERMANENT_EMPLOYEE")
@PrimaryKeyJoinColumn(name="ID") 
public class PermanentEmployee extends Employee {
	
	private Integer leaves;
	private Integer salary;
	
	@Column(name = "LEAVES", nullable = false, length = 100)
	public Integer getLeaves() {
		return leaves;
	}
	public void setLeaves(Integer leaves) {
		this.leaves = leaves;
	}
	
	@Column(name = "SALARY", nullable = false, length = 100)
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
}