package com.oodd.spring.onetomanybidirectional.dto;

import java.util.List;

public class ItemDto {
	private Integer id;
	private String name;
	private List<String> featureList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFeatureList() {
		return featureList;
	}
	public void setFeatureList(List<String> featureList) {
		this.featureList = featureList;
	}
}
