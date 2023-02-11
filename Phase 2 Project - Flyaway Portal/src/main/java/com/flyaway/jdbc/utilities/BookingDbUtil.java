package com.flyaway.jdbc.utilities;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.flyaway.model.Airport;
import com.flyaway.model.FareDetails;
import com.flyaway.model.SearchResult;
import com.flyaway.model.Ticket;

public class BookingDbUtil {
	//Properties
	private DataSource dataSource;
	
	//Constructor with Dependency injection
	public BookingDbUtil(DataSource dataSource) {		
		this.dataSource = dataSource;
	}
	
	//Default Constructor
	public BookingDbUtil() {}
	
	//Methods
	/* ****** Database - CRUD METHODS ********************** *
	 * 										                 *
	 * 		Create ()                           	         *
	 * 		Read (getAirports, getAirportNames, 			 *
	 *            getAirportCodes, getSearchResults, 		 *	
	 *            getFareDetails)             				 *
	 *  	Update ()                               
	 *  	Delete ()	                              
	 *                                                       *
	 * ***************************************************** */

	public List<String> getAirportCodes() throws Exception{
		System.out.println("Inside BookingDbUtil.getAirportCodes()...");
		List<String> codes = new ArrayList<>(); //empty list
		
		//JDBC variables
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;	
		
		//try-finally block		
		try {
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a statement.
			System.out.println("2. Create SQL select statement..");
			String sql = "select * from airportcodes"; 
			myStmt = myConn.createStatement();
			
			//Step 3. Execute the query.
			System.out.println("3. Execute the select query...");
			myRs = myStmt.executeQuery(sql);
			
			//Step 4. Process the ResultSet object.
			System.out.println("4. Process the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				String airportCode = myRs.getString("airportCode");
				codes.add(airportCode);			
			}//end while()
			return codes;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myStmt, myRs);
		}
		
	}//end getAirportCodes()
	
	public List<String> getAirportNames() throws Exception{
		System.out.println("Inside BookingDbUtil.getAirportCodes()...");
		List<String> airportNames = new ArrayList<>(); //empty list
		
		//JDBC variables
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;	
		
		//try-finally block		
		try {
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a statement.
			System.out.println("2. Create SQL select statement..");
			String sql = "select * from airportcodes"; 
			myStmt = myConn.createStatement();
			
			//Step 3. Execute the query.
			System.out.println("3. Execute the select query...");
			myRs = myStmt.executeQuery(sql);
			
			//Step 4. Process the ResultSet object.
			System.out.println("4. Process the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				String airportName = myRs.getString("airportName");
				airportNames.add(airportName);			
			}//end while()
			return airportNames;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myStmt, myRs);
		}
		
	}//end getAirportNames()
	
	public String getAirportName(String code) throws Exception{
		System.out.println("Inside BookingDbUtil.getAirportName(String airportcode)...");
		System.out.println("passed in code = " + code);
		String airportName = null;
		
		//JDBC variables
		Connection myConn = null;
		PreparedStatement myPs = null;
		ResultSet myRs = null;
		
		//try-finally block		
		try {
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a statement.
			System.out.println("2. Create SQL select statement..");
			String sql = "SELECT * from airportcodes"
						+ " WHERE airportCode=?";
			myPs = myConn.prepareStatement(sql);				
			
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setString(1, code);				
			
			System.out.println("4. Executing query...");
			myRs = myPs.executeQuery();			
			
			//Step 5. Process the ResultSet object.
			System.out.println("5. Process the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				airportName = myRs.getString("airportName");
				System.out.println("Airport code (" + code + ") = " + airportName);
			}//end while()
			return airportName;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myPs, myRs);
		}
		
	}//end getAirportName()
	
	public List<Airport> getAirports() throws Exception{
		
		System.out.println("Inside BookingDbUtil.getAirports()...");
		
		List<Airport> airports = new ArrayList<>(); //empty list		
		
		//JDBC variables
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;	
		
		//try-finally block		
		try {
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a statement.
			System.out.println("2. Create SQL select statement..");
			String sql = "select * from airportcodes"; 
			myStmt = myConn.createStatement();
			
			//Step 3. Execute the query.
			System.out.println("3. Execute the select query...");
			myRs = myStmt.executeQuery(sql);
			
			//Step 4. Process the ResultSet object.
			System.out.println("4. Process the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				int airportid = myRs.getInt("airportCodeid");
				int countryid = myRs.getInt("countryid");
				String airportCode = myRs.getString("airportCode");
				String airportName = myRs.getString("airportName");
				//create new Airport object based on the result set row
				Airport tempAirport = new Airport(airportid, countryid, airportCode, airportName);
								
				//add User object to the list of Users (not writing to the database)
				//System.out.println("Add the tempUser to the users ArrayList");
				airports.add(tempAirport);			
			}//end while()
			
			return airports;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myStmt, myRs);
		}	
		
	}//end getAirports()
	
	public List<SearchResult> getSearchResults(String day, String src, String dest) throws Exception{
		
		System.out.println("Inside BookingDbUtil.getSearchResults()...");
		
		List<SearchResult> results = new ArrayList<>(); //empty list
		
		//JDBC variables
		Connection myConn = null;
		PreparedStatement myPs = null;
		ResultSet myRs = null;	
		
		//try-finally block		
		try {
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL JOIN parameterized statement with placeholders
			System.out.println("2. Create SQL JOIN statement..");
			String sql = "SELECT flightdetails.flightnumber"
						+ " ,airlines.companyName"
						+ " ,flightdays.dayName"
						+ " ,flightdetails.src_airportCode"
						+ " ,flightdetails.dest_airportCode"
						+ " FROM flightdays" 
						+ " JOIN flightdetails ON flightdetails.flightnumber=flightdays.flightnumber"
						+ " JOIN airlines ON flightdetails.airlineid=airlines.airlineid"
						+ " WHERE flightdays.dayName=?"
						+ " AND flightdetails.src_airportCode=?"
						+ " AND flightdetails.dest_airportCode=?"; 
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setString(1, day);
			myPs.setString(2, src);
			myPs.setString(3, dest);
			
			//Step 4. Execute the query
			myRs = myPs.executeQuery();
			
			//Step 5. Process the ResultSet object.
			System.out.println("5. Process the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				int flightnumber = myRs.getInt("flightdetails.flightnumber");
				String airportName = myRs.getString("airlines.companyName");
				String dayOfWeek = myRs.getString("flightdays.dayName");
				String source = myRs.getString("flightdetails.src_airportCode");
				String destination = myRs.getString("flightdetails.dest_airportCode");
				
				System.out.println("flightnumber = " + flightnumber);
				System.out.println("airportName = " + airportName);
				System.out.println("day = " + dayOfWeek);
				System.out.println("source = " + source);
				System.out.println("dest = " + destination);
				
				//create new SearchResult object based on the result set row
				SearchResult tempSearchResult = new SearchResult(flightnumber, airportName, dayOfWeek, source, destination);
				System.out.println("tempSearchResult Object:\n" + tempSearchResult.toString());
								
				//add SearchResult object to the list of SearchResults (not writing to the database)				
				results.add(tempSearchResult);
			}//end while()
			
			return results;			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			//DBUtil.close(myConn, myStmt, myRs);
			DBUtil.close(myConn, myPs, myRs);
		}		
	}//end getSearchResults()
	
	public List<FareDetails> getFareDetails(SearchResult searchResult) throws Exception{
	
		System.out.println("Inside BookingDbUtil.getFareDetails()...");
		
		List<FareDetails> results = new ArrayList<>(); //empty list
		
		//JDBC variables
		Connection myConn = null;
		PreparedStatement myPs = null;
		ResultSet myRs = null;	
		
		//try-finally block
		try {	
			
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL JOIN parameterized statement with placeholders
			System.out.println("2. Create SQL JOIN statement..");
			String sql = "SELECT flightdetails.flightnumber"
					+ " ,airlines.companyName"
					+ " ,flightdays.dayName"
					+ " ,flightdetails.src_airportCode"
					+ " ,flightdetails.dest_airportCode"
					+ " ,travelclass.travelclassName"
					+ " ,faredetails.fare"
					+ " FROM flightdays" 
					+ " JOIN flightdetails ON flightdetails.flightnumber=flightdays.flightnumber"
					+ " JOIN airlines ON flightdetails.airlineid=airlines.airlineid"
					+ " JOIN faredetails ON flightdetails.flightnumber=faredetails.flightnumber"
					+ " JOIN travelclass ON faredetails.travelclassid=travelclass.travelclassid"
					+ " WHERE flightdetails.flightnumber=?"
					+ " AND flightdays.dayName=?"
					+ " AND flightdetails.src_airportCode=?"
					+ " AND flightdetails.dest_airportCode=?";
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setInt(1, searchResult.getFlightNumber());
			myPs.setString(2, searchResult.getDay());
			myPs.setString(3, searchResult.getSourceAirport());
			myPs.setString(4, searchResult.getDestinationAirport());
			
			//Step 4. Execute the query
			System.out.println("4. Executing query...");
			myRs = myPs.executeQuery();
			
			//Step 5. Process the ResultSet object.
			System.out.println("5. Processing the ResultSet Object...");
			while(myRs.next()) {
				//retrieve data from result set row
				int flightNumber = myRs.getInt("flightdetails.flightnumber");
				String airline = myRs.getString("airlines.companyName");
				String day = myRs.getString("flightdays.dayName");
				String srcAirport = myRs.getString("flightdetails.src_airportCode");
				String destAirport = myRs.getString("flightdetails.dest_airportCode");
				String travelClass = myRs.getString("travelclass.travelclassName");
				BigDecimal fare = myRs.getBigDecimal("faredetails.fare");
				
				SearchResult tempSearchResult = new SearchResult(flightNumber, airline,day,srcAirport, destAirport);				
				
				//create new FareDetails object based on the result set row
				FareDetails tempFare = new FareDetails(tempSearchResult, travelClass, fare);
				System.out.println("tempFare Object:\n" + tempFare.toString());
								
				//add SearchResult object to the list of SearchResults (not writing to the database)				
				results.add(tempFare);
			}//end while()
			
			return results;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			//DBUtil.close(myConn, myStmt, myRs);
			DBUtil.close(myConn, myPs, myRs);
		}
	}//end getFareDetails()

	public int storeTicket(Ticket tempTicket) throws Exception{
		System.out.println("Inside BookingDbUtil.storeTicket()...");
		System.out.println("Passed in tempTicket: \n" + tempTicket);
		
		int assignedid = 0;
		
		//JDBC variables
		Connection myConn=null, myConn2 = null;
		PreparedStatement myPs=null, myPs2 = null;
		ResultSet myRs=null;
		
		//try-finally block
		try {	
			
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL INSERT parameterized statement with placeholders
			System.out.println("2. Create SQL INSERT statement..");
			String sql = "INSERT INTO ticketdetails"
						+ " (userid, bookingstatusid, directionid, flightnumber, travelclassid, travelDate, totalPassengers, totalfare)"
						+ " VALUES (?,?,?,?,?,?,?,?)";
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);			
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setInt(1, tempTicket.getUserid());
			myPs.setInt(2, tempTicket.getBookingStatusid());
			myPs.setInt(3, tempTicket.getDirectionid());
			myPs.setInt(4, tempTicket.getFlightNumber());
			myPs.setInt(5, tempTicket.getTravelClassid());
			myPs.setString(6, tempTicket.getTravelDate());
			myPs.setInt(7, tempTicket.getTotalPassengers());
			myPs.setBigDecimal(8, tempTicket.getTotalFare());							
			
			//Step 4. Execute the Update query
			System.out.println("4. Executing query...");
			myPs.executeUpdate(); //Executes the SQL statement in this PreparedStatement object
			
			//DBUtil.close(myConn, myPs, null);		
			
			myConn2 = dataSource.getConnection();	
			System.out.println("myConn2 = " + myConn2.toString());
			
			String sql2 = "SELECT bookingid FROM ticketdetails"
					+ " WHERE userid=?"
					+ " AND bookingstatusid=?"
					+ " AND directionid=?"
					+ " AND flightnumber=?"
					+ " AND travelclassid=?"
					+ " AND travelDate=?"
					+ " AND totalPassengers=?"
					+ " AND totalfare=?";
			System.out.println("sql2 = " + sql2);
			myPs2 = myConn2.prepareStatement(sql2);	
			
			System.out.println("tempTicket: \n" + tempTicket.toString());
			
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs2.setInt(1, tempTicket.getUserid());
			myPs2.setInt(2, tempTicket.getBookingStatusid());
			myPs2.setInt(3, tempTicket.getDirectionid());
			myPs2.setInt(4, tempTicket.getFlightNumber());
			myPs2.setInt(5, tempTicket.getTravelClassid());
			myPs2.setString(6, tempTicket.getTravelDate());
			myPs2.setInt(7, tempTicket.getTotalPassengers());
			myPs2.setBigDecimal(8, tempTicket.getTotalFare());	
			
			System.out.println("4. Executing query...");
			myRs = myPs2.executeQuery();
			
			while(myRs.next()) {
				System.out.println("inside the resultset while...");
				assignedid = myRs.getInt("bookingid");
				System.out.println("assignedid = " + assignedid);				
			}
			
			return assignedid;			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");			
			DBUtil.close(myConn, myPs, null);
			DBUtil.close(myConn2, myPs2, myRs);
		}
	}//end storeTicket()	
	
	public void updateBookingStatus(int bookingid, int bookingStatus) throws Exception{
		System.out.println("Inside BookingDbUtil.updatedBookingStatus(int, bookingid, int bookingStatus)...");
		System.out.println("passed in bookingid=" + bookingid + ", bookingStatus=" + bookingStatus);
		
		Connection myConn = null;
		PreparedStatement myPs = null;		
		
		//try-finally block		
		try {
			
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL JOIN parameterized statement with placeholders
			System.out.println("2. Create SQL INSERT statement..");
			String sql = "UPDATE ticketdetails"
						+ " SET bookingstatusid=?"
						+ " WHERE bookingid = ?";
			
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);			
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setInt(1, bookingStatus);
			myPs.setInt(2, bookingid);
			
			//Step 4. Execute the Update query
			System.out.println("4. Executing query...");
			myPs.executeUpdate(); //Executes the SQL statement in this PreparedStatement object		
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myPs, null);
		}		
	}//end updateBookingStatus()
	
	public Ticket getTicket(int bookingid) throws Exception {		
		System.out.println("Inside BookingDbUtil.getTicket()..");
		System.out.println("passed in bookingid=" + bookingid);
		
		Ticket theTicket = new Ticket();
		//JDBC Objects
		Connection myConn = null;
		PreparedStatement myPs = null;
		ResultSet myRs = null;	
		
		//try-finally block		
		try {
			
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL SELECT parameterized statement with placeholders
			System.out.println("2. Create SQL UPDATE statement..");
			String sql = "SELECT * from ticketdetails"
						+ " WHERE bookingid=?";
			
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);			
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setInt(1, bookingid);			
			
			//Step 4. Execute the Update query
			System.out.println("4. Executing query...");
			myRs = myPs.executeQuery(); //Executes the SQL statement in this PreparedStatement object
			
			while(myRs.next()) {				
				theTicket.setBookingid(myRs.getInt("bookingid"));
				theTicket.setUserid(myRs.getInt("userid"));
				theTicket.setBookingStatusid(myRs.getInt("bookingstatusid"));
				theTicket.setDirectionid(myRs.getInt("directionid"));
				theTicket.setFlightNumber(myRs.getInt("flightnumber"));
				theTicket.setTravelClassid(myRs.getInt("travelclassid"));
				theTicket.setTravelDate(myRs.getString("travelDate"));
				theTicket.setTotalPassengers(myRs.getInt("totalPassengers"));
				theTicket.setTotalFare(myRs.getBigDecimal("totalFare"));
			}
			return theTicket;			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");			
			DBUtil.close(myConn, myPs, myRs);
		}
		
	}//end getTicket()
	
}//end class
