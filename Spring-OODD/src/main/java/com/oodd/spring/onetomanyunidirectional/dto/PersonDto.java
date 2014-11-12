package com.oodd.spring.onetomanyunidirectional.dto;

import java.util.List;

public class PersonDto {
	private Integer id;
	private String name;
	private List<String> numbers;
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
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
}
