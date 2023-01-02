package com.sportyshoes.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genders")
public class Gender {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genderId")    
	private Long id; //primary key
	private String genderName;
	
	//Default Constructor
	public Gender() {}
	
	//Parameterized Constructor
	public Gender(Long id, String genderName) {
		this.id = id;
		this.genderName = genderName;
	}

	//Getters & Setters
	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Long getId() {
		return id;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "Gender [id=" + id + ", genderName=" + genderName + "]";
	}	

}//end class
