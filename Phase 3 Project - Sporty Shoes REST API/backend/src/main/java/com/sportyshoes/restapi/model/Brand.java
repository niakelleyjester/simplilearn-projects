package com.sportyshoes.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="brands")
public class Brand {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="brandId")    
	private Long id; //primary key
	private String brandName;
	
	//Default Constructor
	public Brand() {}
	
	//Parameterized Constructor
	public Brand(Long id, String brandName) {
		this.id = id;
		this.brandName = brandName;
	}
	
	//Getters & Setters
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getId() {
		return id;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "Brand [id=" + id + ", brandName=" + brandName + "]";
	}

}//end class
