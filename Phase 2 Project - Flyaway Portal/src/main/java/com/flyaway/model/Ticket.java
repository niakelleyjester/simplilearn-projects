package com.flyaway.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ticket implements Serializable{

	//Properties
	private static final long serialVersionUID = 1L;
	private int bookingid;
	//private int ticketid; //no time to design implementation logic (especially for roundtrip tickets)
	private int userid;
	private int bookingStatusid;
	private int directionid;
	private int flightNumber;
	private int travelClassid;
	private String srcAirportCode;
	private String srcAiportFullName;
	private String destAirportCode;
	private String destAirportFullName;
	private String travelDay;
	private String travelDate;
	private int totalPassengers;
	private BigDecimal totalFare;
	
	//Parameterized Constructors
	public Ticket(int bookingid, int userid, int bookingStatusid, int directionid, int flightNumber, int travelClassid,
			String travelDay, String travelDate, int totalPassengers, BigDecimal totalFare) {
		this.bookingid = bookingid;
		this.userid = userid;
		this.bookingStatusid = bookingStatusid;
		this.directionid = directionid;
		this.flightNumber = flightNumber;
		this.travelClassid = travelClassid;
		this.travelDay = travelDay;
		this.travelDate = travelDate;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}
	
	public Ticket(int userid, int bookingStatusid, int directionid, int flightNumber, int travelClassid,
			String travelDay, String travelDate, int totalPassengers, BigDecimal totalFare) {		
		this.userid = userid;
		this.bookingStatusid = bookingStatusid;
		this.directionid = directionid;
		this.flightNumber = flightNumber;
		this.travelClassid = travelClassid;
		this.travelDay = travelDay;
		this.travelDate = travelDate;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}
	
	public Ticket(int bookingid, int userid, int bookingStatusid, int directionid, int flightNumber, int travelClassid,
			String travelDate, int totalPassengers, BigDecimal totalFare) {
		this.bookingid = bookingid;
		this.userid = userid;
		this.bookingStatusid = bookingStatusid;
		this.directionid = directionid;
		this.flightNumber = flightNumber;
		this.travelClassid = travelClassid;
		this.travelDate = travelDate;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}

	public Ticket(int bookingid, int userid, int bookingStatusid, int directionid, int flightNumber, int travelClassid,
			String srcAirportCode, String srcAiportFullName, String destAirportCode, String destAirportFullName,
			String travelDay, String travelDate, int totalPassengers, BigDecimal totalFare) {
		this.bookingid = bookingid;
		this.userid = userid;
		this.bookingStatusid = bookingStatusid;
		this.directionid = directionid;
		this.flightNumber = flightNumber;
		this.travelClassid = travelClassid;
		this.srcAirportCode = srcAirportCode;
		this.srcAiportFullName = srcAiportFullName;
		this.destAirportCode = destAirportCode;
		this.destAirportFullName = destAirportFullName;
		this.travelDay = travelDay;
		this.travelDate = travelDate;
		this.totalPassengers = totalPassengers;
		this.totalFare = totalFare;
	}

	//Default Constructor
	public Ticket() {}
	
	//Getters & Setters
	public int getBookingid() {
		return bookingid;
	}	

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBookingStatusid() {
		return bookingStatusid;
	}

	public void setBookingStatusid(int bookingStatusid) {
		this.bookingStatusid = bookingStatusid;
	}

	public int getDirectionid() {
		return directionid;
	}

	public void setDirectionid(int directionid) {
		this.directionid = directionid;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getTravelClassid() {
		return travelClassid;
	}

	public void setTravelClassid(int travelClassid) {
		this.travelClassid = travelClassid;
	}
	
	public String getSrcAirportCode() {
		return srcAirportCode;
	}

	public void setSrcAirportCode(String srcAirportCode) {
		this.srcAirportCode = srcAirportCode;
	}

	public String getSrcAiportFullName() {
		return srcAiportFullName;
	}

	public void setSrcAiportFullName(String srcAiportFullName) {
		this.srcAiportFullName = srcAiportFullName;
	}

	public String getDestAirportCode() {
		return destAirportCode;
	}

	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}

	public String getDestAirportFullName() {
		return destAirportFullName;
	}

	public void setDestAirportFullName(String destAirportFullName) {
		this.destAirportFullName = destAirportFullName;
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
		return "Ticket [bookingid=" + bookingid + ", userid=" + userid + ", bookingStatusid=" + bookingStatusid
				+ ", directionid=" + directionid + ", flightNumber=" + flightNumber + ", travelClassid=" + travelClassid
				+ ", srcAirportCode=" + srcAirportCode + ", srcAiportFullName=" + srcAiportFullName
				+ ", destAirportCode=" + destAirportCode + ", destAirportFullName=" + destAirportFullName
				+ ", travelDay=" + travelDay + ", travelDate=" + travelDate + ", totalPassengers=" + totalPassengers
				+ ", totalFare=" + totalFare + "]";
	}	

}//end class
