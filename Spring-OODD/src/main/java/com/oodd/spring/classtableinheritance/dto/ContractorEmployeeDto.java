package com.oodd.spring.classtableinheritance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

 
public class ContractorEmployeeDto extends EmployeeDto {
	
	@JsonProperty("hourlyRate")
	private Integer hourlyRate;
	@JsonProperty("overtimeRate")
	private Integer overtimeRate;
	
	public Integer getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(Integer hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public Integer getOvertimeRate() {
		return overtimeRate;
	}
	public void setOvertimeRate(Integer overtimeRate) {
		this.overtimeRate = overtimeRate;
	}
}