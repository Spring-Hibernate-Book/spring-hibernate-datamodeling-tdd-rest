package com.oodd.spring.classtableinheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CONTRACTOR_EMPLOYEE")
@PrimaryKeyJoinColumn(name="ID")  
public class ContractorEmployee extends Employee {
	
	private Integer hourlyRate;
	private Integer overtimeRate;
	
	@Column(name = "HOURLEY_RATE", nullable = false, length = 100)
	public Integer getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(Integer hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	@Column(name = "OVERTIME_RATE", nullable = false, length = 100)
	public Integer getOvertimeRate() {
		return overtimeRate;
	}
	public void setOvertimeRate(Integer overtimeRate) {
		this.overtimeRate = overtimeRate;
	}
}