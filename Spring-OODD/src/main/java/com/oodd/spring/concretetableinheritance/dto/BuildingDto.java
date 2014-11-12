package com.oodd.spring.concretetableinheritance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildingDto extends EstateDto {
	
	@JsonProperty("floors")
	private Integer floors;

	public Integer getFloors() {
		return floors;
	}

	public void setFloors(Integer floors) {
		this.floors = floors;
	}
}