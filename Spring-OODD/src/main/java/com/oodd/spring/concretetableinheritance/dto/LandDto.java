package com.oodd.spring.concretetableinheritance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LandDto extends EstateDto {
	
	@JsonProperty("area")
	private Integer area;

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}
}