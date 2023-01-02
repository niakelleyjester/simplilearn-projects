package com.sportyshoes.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="features")
public class Feature {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="featureId")    
	private Long id; //primary key
	private String featureName;
	
	//Default Constructor
	public Feature() {}
	
	//Parameterized Constructor
	public Feature(Long id, String featureName) {		
		this.id = id;
		this.featureName = featureName;
	}
	
	//Getters & Setters
	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public Long getId() {
		return id;
	}

	//Other Methods
	@Override
	public String toString() {
		return "Feature [id=" + id + ", featureName=" + featureName + "]";
	}	

}//end class
