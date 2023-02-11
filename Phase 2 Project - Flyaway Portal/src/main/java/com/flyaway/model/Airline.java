package com.flyaway.model;

import java.io.Serializable;

public class Airline implements Serializable{
	
	//Properties
	private static final long serialVersionUID = 1L;	
	private int airlineid;
	private String companyName;
	
	//Parameterized Constructors
	public Airline(int airlineid, String companyName) {		
		this.airlineid = airlineid;
		this.companyName = companyName;
	}
	
	public Airline(String companyName) {	
		this.companyName = companyName;
	}
	
	//Default constructor
	public Airline() {}

	//Getters & Setters
	public int getAirlineid() {
		return airlineid;
	}

	public void setAirlineid(int airlineid) {
		this.airlineid = airlineid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	//other methods
	@Override
	public String toString() {
		return "Airline [airlineid=" + airlineid + ", companyName=" + companyName + "]";
	}	

}//end class