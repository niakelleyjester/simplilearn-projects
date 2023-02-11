package com.flyaway.model;

import java.io.Serializable;

public class TravelClass implements Serializable {	
	
	//Properties
	private static final long serialVersionUID = 1L;
	private int travelClassid;
	private String travelClassName;
	
	//Parameterized Constructors
	public TravelClass(int travelClassid, String travelClassName) {	
		this.travelClassid = travelClassid;
		this.travelClassName = travelClassName;
	}
	
	//Default Constructor
	public TravelClass() {}
	
	//Getters & Setters
	public int getTravelClassid() {
		return travelClassid;
	}
	public void setTravelClassid(int travelClassid) {
		this.travelClassid = travelClassid;
	}
	public String getTravelClassName() {
		return travelClassName;
	}
	public void setTravelClassName(String travelClassName) {
		this.travelClassName = travelClassName;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "TravelClass [travelClassid=" + travelClassid + ", travelClassName=" + travelClassName + "]";
	}
}//end class
