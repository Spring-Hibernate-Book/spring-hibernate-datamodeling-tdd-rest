package com.oodd.spring.standalone.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
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
}
