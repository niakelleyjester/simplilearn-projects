package com.sportyshoes.restapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="sizes")
public class Size {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sizeId")    
	private Long id; //primary key
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "genderId", referencedColumnName="genderId")
	private Gender genderId; //foreign key configured to the name of the column in the genders table
	
	private String sizeName;	
	
	//Default Constructor
	public Size() {}	
	
	//Parameterized Constructor
	public Size(Long id, Gender genderId, String sizeName) {
		this.id = id;
		this.genderId = genderId;
		this.sizeName = sizeName;
	}

	//Getters & Setters
	public Gender getGenderId() {
		return genderId;
	}

	public void setGenderId(Gender genderId) {
		this.genderId = genderId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Long getId() {
		return id;
	}

	//Other Methods
	@Override
	public String toString() {
		return "Size [id=" + id + ", genderId=" + genderId + ", sizeName=" + sizeName + "]";
	}	

}//end class
