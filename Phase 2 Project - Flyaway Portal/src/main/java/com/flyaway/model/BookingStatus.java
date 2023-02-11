package com.flyaway.model;

import java.io.Serializable;

public class BookingStatus implements Serializable{

	//Properties
	private static final long serialVersionUID = 1L;
	private int bookingStatusid;
	private String bookingStatusName;
	
	//Parameterized Constructors
	public BookingStatus(int bookingStatusid, String bookingStatusName) {
		this.bookingStatusid = bookingStatusid;
		this.bookingStatusName = bookingStatusName;
	}
	
	//Default Constructor
	public BookingStatus() {}

	//Getters & Setters
	public int getBookingStatusid() {
		return bookingStatusid;
	}
	public void setBookingStatusid(int bookingStatusid) {
		this.bookingStatusid = bookingStatusid;
	}
	public String getBookingStatusName() {
		return bookingStatusName;
	}
	public void setBookingStatusName(String bookingStatusName) {
		this.bookingStatusName = bookingStatusName;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "BookingStatus [bookingStatusid=" + bookingStatusid + ", bookingStatusName=" + bookingStatusName + "]";
	}
	
}//end POJO
