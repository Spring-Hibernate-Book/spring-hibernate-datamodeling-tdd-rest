package com.oodd.spring.onetomanyunidirectional.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PHONE")
public class Phone {
	private Integer id;
	private String number;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID",length=20)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="NUMBER",length=20)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
