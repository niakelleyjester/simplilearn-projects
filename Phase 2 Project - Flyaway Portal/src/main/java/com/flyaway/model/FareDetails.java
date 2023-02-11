package com.flyaway.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class FareDetails implements Serializable{	
	
	//Properties
	private static final long serialVersionUID = 1L;
	private SearchResult searchResult;
	private String travelClass;
	private BigDecimal fare;
	
	//Parameterized Constructor
	public FareDetails(SearchResult searchResult, String travelClass, BigDecimal fare) {
		this.searchResult = searchResult;
		this.travelClass = travelClass;
		this.fare = fare;
	}
	
	//Default Constructor
	public FareDetails() {}

	//Getters & Setters
	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	//Other Methods
	@Override
	public String toString() {
		return "FareDetails [searchResult=" + searchResult + ", travelClass=" + travelClass + ", fare=" + fare + "]";
	}	
	
}//end class
