package com.flyaway.model;

import java.io.Serializable;

public class SearchResult implements Serializable  {
	
	//Properties
	private static final long serialVersionUID = 1L;
	private int flightNumber;	
	private String airlineName;
	private String day;
	private String sourceAirport;
	private String sourceAiportFullName;
	private String destinationAirport;
	private String destinationAiportFullName;
	
	//Parameterized Constructors
	public SearchResult(int flightNumber, String airlineName, String day, String sourceAirport,
			String destinationAirport) {
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.day = day;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
	}
	
	public SearchResult(int flightNumber, String airlineName, String day, String sourceAirport,
			String sourceAiportFullName, String destinationAirport, String destinationAiportFullName) {
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.day = day;
		this.sourceAirport = sourceAirport;
		this.sourceAiportFullName = sourceAiportFullName;
		this.destinationAirport = destinationAirport;
		this.destinationAiportFullName = destinationAiportFullName;
	}

	//Default Constructor
	public SearchResult() {}
	
	//Getters & Setters
	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getSourceAiportFullName() {
		return sourceAiportFullName;
	}

	public void setSourceAiportFullName(String sourceAiportFullName) {
		this.sourceAiportFullName = sourceAiportFullName;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getDestinationAiportFullName() {
		return destinationAiportFullName;
	}

	public void setDestinationAiportFullName(String destinationAiportFullName) {
		this.destinationAiportFullName = destinationAiportFullName;
	}

	@Override
	public String toString() {
		return "SearchResult [flightNumber=" + flightNumber + ", airlineName=" + airlineName + ", day=" + day
				+ ", sourceAirport=" + sourceAirport + ", sourceAiportFullName=" + sourceAiportFullName
				+ ", destinationAirport=" + destinationAirport + ", destinationAiportFullName="
				+ destinationAiportFullName + "]";
	}

}//end class
