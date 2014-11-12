package com.oodd.spring.concretetableinheritance.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BUILDING")
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="ID")),  
    @AttributeOverride(name="name", column=@Column(name="NAME"))  
})  
public class Building extends Estate {
	
	private Integer floors;

	@Column(name = "FLOORS", nullable = false, length = 100)
	public Integer getFloors() {
		return floors;
	}

	public void setFloors(Integer floors) {
		this.floors = floors;
	}
}