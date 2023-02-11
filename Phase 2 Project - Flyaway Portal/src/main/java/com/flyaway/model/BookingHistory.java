package com.flyaway.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookingHistory  implements Serializable{	
	
	//Properties
	private static final long serialVersionUID = 1L;
	
	private int bookingid;
	private String bookingStatus;
	private String purchaseDate;
	private int flightNumber;
	private String airline;
	private String travelClass;
	private String travelDay;
	private String travelDate;
	private String sourceAirportCode;
	private String sourceAirportName;
	private String destAirportCode;
	private String destAirportName;
	private int totalPassengers;
	private BigDecimal totalFare;
	
	//Parameterized Constructor
	public BookingHistory(int bookingid, String bookingStatus, String purchaseDate, int flightNumber, String airline,
			String travelClass, String travelDay, String travelDate, String sourceAirportCode, String sourceAirportName,
			String destAirportCode, String destAirportName, int totalPassengers, BigDecimal totalFare) {
		this.bookingid = bookingid;
		this.bookingStatus = bookingStatus;
		this.purchaseDate = purchaseDate;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.travelClass = travelClass;
		this.travelDay = travelDay;
		this.travelDate = travelDate;
		this.sourceAirportCode = sourceAirportCode;
		this.sourceAirportName = sourceAirportName;
		this.destAirportCode = destAirportCode;
		this.destAirportName = destAirportName;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}
	
	public BookingHistory(int bookingid, String bookingStatus, String purchaseDate, int flightNumber, String airline,
			String travelClass, String travelDate, String sourceAirportCode, String destAirportCode,
			int totalPassengers, BigDecimal totalFare) {
		this.bookingid = bookingid;
		this.bookingStatus = bookingStatus;
		this.purchaseDate = purchaseDate;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.travelClass = travelClass;
		this.travelDate = travelDate;
		this.sourceAirportCode = sourceAirportCode;
		this.destAirportCode = destAirportCode;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}

	//Default Constructor
	public BookingHistory() {
		// TODO Auto-generated constructor stub
	}

	//Getters & Setters
	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	public String getTravelDay() {
		return travelDay;
	}

	public void setTravelDay(String travelDay) {
		this.travelDay = travelDay;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getSourceAirportCode() {
		return sourceAirportCode;
	}

	public void setSourceAirportCode(String sourceAirportCode) {
		this.sourceAirportCode = sourceAirportCode;
	}

	public String getSourceAirportName() {
		return sourceAirportName;
	}

	public void setSourceAirportName(String sourceAirportName) {
		this.sourceAirportName = sourceAirportName;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}

	public String getDestAirportName() {
		return destAirportName;
	}

	public void setDestAirportName(String destAirportName) {
		this.destAirportName = destAirportName;
	}

	public int getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	public BigDecimal getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(BigDecimal totalFare) {
		this.totalFare = totalFare;
	}

	//Other Methods
	@Override
	public String toString() {
		return "BookingHistory [bookingid=" + bookingid + ", bookingStatus=" + bookingStatus + ", purchaseDate="
				+ purchaseDate + ", flightNumber=" + flightNumber + ", airline=" + airline + ", travelClass="
				+ travelClass + ", travelDay=" + travelDay + ", travelDate=" + travelDate + ", sourceAirportCode="
				+ sourceAirportCode + ", sourceAirportName=" + sourceAirportName + ", destAirportCode="
				+ destAirportCode + ", destAirportName=" + destAirportName + ", totalPassengers=" + totalPassengers
				+ ", totalFare=" + totalFare + "]";
	}	

}//end class
