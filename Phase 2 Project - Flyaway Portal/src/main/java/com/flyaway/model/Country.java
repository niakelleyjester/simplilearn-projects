package com.flyaway.model;

import java.io.Serializable;

public class Country implements Serializable{
	
	//Properties
	private static final long serialVersionUID = 1L;	
	private int countryid;
	private String countryName;
	
	//Parameterized Constructors
	public Country(int countryid, String countryName) {
		this.countryid = countryid;
		this.countryName = countryName;
	}
	
	//Default Constructor
	public Country() {}
	
	//Getters & Setters
	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "Country [countryid=" + countryid + ", countryName=" + countryName + "]";
	}	

}//end class