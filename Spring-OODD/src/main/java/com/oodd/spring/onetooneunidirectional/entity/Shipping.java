package com.oodd.spring.onetooneunidirectional.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="SHIPPING")
public class Shipping {
	private Integer id;
	private String city;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",length=20)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="city",length=50)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
