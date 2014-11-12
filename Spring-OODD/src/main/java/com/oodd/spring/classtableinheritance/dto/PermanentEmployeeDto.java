package com.oodd.spring.classtableinheritance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PermanentEmployeeDto extends EmployeeDto {
	
	@JsonProperty("leaves")
	private Integer leaves;
	@JsonProperty("salary")
	private Integer salary;
	
	public Integer getLeaves() {
		return leaves;
	}
	public void setLeaves(Integer leaves) {
		this.leaves = leaves;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
}