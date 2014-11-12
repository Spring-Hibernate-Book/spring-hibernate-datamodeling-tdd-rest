package com.oodd.spring.concretetableinheritance.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LAND")
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="ID")),  
    @AttributeOverride(name="name", column=@Column(name="NAME"))  
})  
public class Land extends Estate {
	
	private Integer area;

	@Column(name = "AREA", nullable = false, length = 100)
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}
}
