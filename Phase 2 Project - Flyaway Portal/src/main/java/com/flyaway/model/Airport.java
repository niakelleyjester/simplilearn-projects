package com.flyaway.model;

import java.io.Serializable;

public class Airport implements Serializable{
	
	//Properties
	private static final long serialVersionUID = 1L;	
	private int airportid;
	private int countryid;	
	private String airportCode;
	private String airportName;
	
	//default constructor
	public Airport() {}

	//Parameterized Constructors
	public Airport(int airportid, int countryid, String airportCode, String airportName) {		
		this.airportid = airportid;
		this.countryid = countryid;
		this.airportCode = airportCode;
		this.airportName = airportName;
	}

	//Getters & Setters
	public int getAirportid() {
		return airportid;
	}

	public void setAirportid(int airportid) {
		this.airportid = airportid;
	}

	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	//Other Methods
	@Override
	public String toString() {
		return "Airport [airportid=" + airportid + ", countryid=" + countryid + ", airportCode=" + airportCode
				+ ", airportName=" + airportName + "]";
	}
}//end class
