package com.flyaway.model;

import java.io.Serializable;

public class FlightDirection implements Serializable  {
	
	//Properties
	private static final long serialVersionUID = 1L;	
	private int directionid;
	private String directionName;
	
	//Parameterized Constructors
	public FlightDirection(int directionid, String directionName) {		
		this.directionid = directionid;
		this.directionName = directionName;
	}
	
	//Default Constructor
	public FlightDirection() {}

	//Getters & Setters
	public int getDirectionid() {
		return directionid;
	}

	public void setDirectionid(int directionid) {
		this.directionid = directionid;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	//Other Methods
	@Override
	public String toString() {
		return "FlightDirection [directionid=" + directionid + ", directionName=" + directionName + "]";
	}

}//end class
