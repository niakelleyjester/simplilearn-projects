package com.flyaway.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.flyaway.jdbc.utilities.BookingDbUtil;
import com.flyaway.model.Airport;
import com.flyaway.model.FareDetails;
import com.flyaway.model.SearchResult;
import com.flyaway.model.Ticket;
import com.flyaway.model.User;


@WebServlet("/BookFlightControllerServlet")
public class BookFlightControllerServlet extends HttpServlet {
	
	//Properties
	private static final long serialVersionUID = 1L;
	private BookingDbUtil bookingDbUtil;
    
    //Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/flyaway") //comes from the context.xml (alternatively, I could have used properties file. Chose this method for more readable code.)
    private DataSource dataSource;
    
    //Default Constructor
    public BookFlightControllerServlet() {}

    //Methods   
  	
  	@Override
  	public void init() throws ServletException {  		
  		/* In the Servlet lifecyle, they have a special method that you can override called init()
  	  	 * The init() method will be called by the Java EE app server or by Tomcat when this servlet is first loaded/initialized
  	  	 * This is a good place for you to put your own custom initialization work, inside of this init() method 
  	  	 * So work that you would normally do in a constructor, when you're working with servlets, 
  	  	 * you can put that code inside the init method.  
  	  	 */	
  		//create our datasource, and pass in the connection pool/datasource
  		try {
  			bookingDbUtil = new BookingDbUtil(dataSource);
  		}
  		catch (Exception e) {
  			throw new ServletException(e); //in case there is an error reading from the database or other problem
  		}
  		
  	}//end init()
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside BookFlightControllerServlet doGet()...");
		//try-catch block
		try {
			
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			System.out.println("theCommand = " + theCommand);
			
			//if the command is missing, then default to listing users
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method in the UserDbUtil	
			switch(theCommand) {			
				
			case "LOAD":
				System.out.println("case: LOAD...");
				System.out.println("hidden command = " + theCommand);
				loadFlight(request, response);
				break;
				
			case "SEARCH":
				System.out.println("case: SEARCH...");
				System.out.println("hidden command = " + theCommand);
				searchFlights(request, response);
				break;
				
			case "BOOK_FARE":
				System.out.println("case: BOOK_FARE...");
				System.out.println("hidden command = " + theCommand);
				bookFare(request, response);
				break;			
				
			case "LIST":
			default:
				System.out.println("case: LIST/default...");
				System.out.println("hidden command = " + theCommand);				
				listCities(request, response);				
				break;
			}//end switch
		} catch (Exception e) {			
			e.printStackTrace();
			throw new ServletException(e);
		} 
	}//end doGet()	

	private void listCities(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside BookFlightControllerServlet listCities()...");
		HttpSession session = request.getSession();
		
		//1. Reading the Cities from the database using the DB Util
		List<Airport> airports = bookingDbUtil.getAirports();
		System.out.println("airports: ");
		System.out.println(airports);
		
		//2. Add cities list to the request object
		session.setAttribute("CITIES_LIST", airports);		
				
		//3. Send to book-flight.jsp JSP page (view) using dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-flight.jsp");
		dispatcher.forward(request, response);
	}//end listCities()
	
	private void loadFlight(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside BookFlightControllerServlet.loadFlight()...");
		
		//Step 1. read flightnumber from form data (BookTicket button)
		int theFlightNumber = Integer.parseInt(request.getParameter("flightNumber"));
		System.out.println("theFlightNumber = " + theFlightNumber);
		
		String theAirline = request.getParameter("airline");
		String theDay = request.getParameter("day");
		String theDate = request.getParameter("date");
		String theSrc = request.getParameter("src");//airportcode
		String theSrcFullName = request.getParameter("srcFullName");
		String theDest = request.getParameter("dest");	//airportcode
		String theDestFullName = request.getParameter("destFullName");
		
		SearchResult theSearchResult = new SearchResult(theFlightNumber, theAirline, theDay, theSrc, theSrcFullName, theDest, theDestFullName);
		System.out.println("theSearchResult = \n" + theSearchResult);
		
		//Step 2. get flight details from the database
		List<FareDetails> fareDetails = bookingDbUtil.getFareDetails(theSearchResult);
		
		for(FareDetails x : fareDetails) {
			String temp1 = x.getSearchResult().getDestinationAirport();
			x.getSearchResult().setDestinationAiportFullName(bookingDbUtil.getAirportName(temp1));
			String temp2 = x.getSearchResult().getSourceAirport();
			x.getSearchResult().setSourceAiportFullName(bookingDbUtil.getAirportName(temp2));
		}
		
		//Step 3. place flight details in the request by setting attribute that we can refer to in the JSP
		request.setAttribute("FARE_DETAILS_LIST", fareDetails); //(attribute name, object)
		request.setAttribute("SELECTED_DATE", theDate);
		
		//Step 4. send to jsp form
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/fare-details.jsp");
		dispatcher.forward(request, response);		
	}//end loadFlight()	
	
	private void searchFlights(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("Inside BookFlightControllerServlet searchFlights()...");
		
		//Properties
		List<SearchResult> departResults = new ArrayList<>(); //empty list
		List<SearchResult> returnResults = new ArrayList<>(); //empty list
		HttpSession session = request.getSession();
		
		//1. Read info from book-flight.jsp form		
		String flightDirection = request.getParameter("flightDirection");		
		String departureCityString = request.getParameter("departureCity");		
		String arrivalCityString = request.getParameter("arrivalCity");		
		String departDateString = request.getParameter("departDate");		
		String numTravelers = request.getParameter("numOfTravelers");
				
		//Decomposing departureCityString & extracting airportCode		
		String[] str1 = departureCityString.split(" | ");
		System.out.println("str.length = " + str1.length);		
		String departureAirportCode = str1[0];		
		System.out.println("departureAirportCode = " + departureAirportCode);
		System.out.println("departureCityString = " + departureCityString);
		
		//Decomposing arrivalCityString & extracting airportCode
		String[] str2 = arrivalCityString.split(" | ");
		System.out.println("str.length = " + str2.length);		
		String arrivalAirportCode = str2[0];		
		System.out.println("arrivalAirportCode = " + arrivalAirportCode);
		System.out.println("arrivalCityString = " + arrivalCityString);
		
		//1b. Formatting Date and Day fields
		SimpleDateFormat sdfDepart = new SimpleDateFormat("yyyy-MM-dd");
		Date departDate = sdfDepart.parse(departDateString); //Date object has date and time
		String departDay = new SimpleDateFormat("EEEE").format(departDate);
		
		System.out.println("flightDirection = " + flightDirection);		
		System.out.println("departDateString = " + departDateString);
		System.out.println("departDate = " + departDate);
		System.out.println("departDay = " + departDay);		
		System.out.println("numTravelers = " + numTravelers);				
				
		System.out.println("Building the departing flight list...");
		//2. Get matching results from the database using the DB Util
		departResults = bookingDbUtil.getSearchResults(departDay, departureAirportCode, arrivalAirportCode); //departDay, departCity, arrivalCity
		
		for(SearchResult x : departResults) {
			String temp1 = x.getDestinationAirport();
			x.setDestinationAiportFullName(bookingDbUtil.getAirportName(temp1));
			String temp2 = x.getSourceAirport();
			x.setSourceAiportFullName(bookingDbUtil.getAirportName(temp2));
		}
		
		System.out.println("Updated departResults List:\n" + departResults);
		
		//3. Add results to request object for the search-results.page.jsp
		request.setAttribute("DEPARTING_SEARCH_RESULTS_LIST", departResults);		
		
		//4. Add results to session object for future use		
		session.setAttribute("departDate", departDateString);
		session.setAttribute("flightDirection", flightDirection);
		session.setAttribute("passengers", numTravelers);
		session.setAttribute("arrivalAC", arrivalAirportCode);
		session.setAttribute("arrivalAirportFullName", arrivalCityString);
		session.setAttribute("departingAirportFullName", departureCityString);
		session.setAttribute("departAC", departureAirportCode);
		
		if(flightDirection.equals("roundtrip")) {
			System.out.println("In the roundtrip block....");
			//1. Read info from book-flight.jsp form					
			String returnDateString = request.getParameter("returnDate");
		
			//Formatting Date and Day fields
			SimpleDateFormat sdfReturn = new SimpleDateFormat("yyyy-MM-dd");
			Date returnDate = sdfReturn.parse(returnDateString);
			String returnDay = new SimpleDateFormat("EEEE").format(returnDate);		
			
			System.out.println("returnDateString = " + returnDateString);
			System.out.println("returnDate = " + returnDate);
			System.out.println("returnDay = " + returnDay);
			System.out.println("arrivalAirportCode = " + arrivalAirportCode);
			System.out.println("departureAirportCode = " + departureAirportCode);
						
			System.out.println("Building the return flight list...");
			//2. Get matching results from the database using the DB Util
			returnResults = bookingDbUtil.getSearchResults(returnDay, arrivalAirportCode, departureAirportCode); //arrivalDay, arrivalCity
			
			for(SearchResult x : returnResults) {
				String temp1 = x.getDestinationAirport();
				x.setDestinationAiportFullName(bookingDbUtil.getAirportName(temp1));
				String temp2 = x.getSourceAirport();
				x.setSourceAiportFullName(bookingDbUtil.getAirportName(temp2));
			}
			
			System.out.println("Updated returnResults List:\n" + returnResults);
			
			//3. Add results to request object for the search-results.page.jsp
			request.setAttribute("RETURN_SEARCH_RESULTS_LIST", returnResults);
			
			//4. Add results to session object for future use
			session.setAttribute("returnDate", returnDateString);			
		}
		
		//5. Send to search-results-page.jsp JSP page (view) using dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("search-results-page.jsp");
		dispatcher.forward(request, response);
	}//end searchFlights()
	
	private void bookFare(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//Properties
		HttpSession session = request.getSession();
		User customer = (User) session.getAttribute("loggedInUser");
		Ticket tempTicket = new Ticket();		
		
		//1. Read FareDetails info from fare-details.jsp form
		String selectedFlightNumber = request.getParameter("flightNumber");
		String selectedAirline = request.getParameter("airline");
		String selectedDay = request.getParameter("day");
		String selectedTravelClass = request.getParameter("travelClass");
		String selectedSource = request.getParameter("src");//airport code
		String selectedSourceFN = request.getParameter("srcFullName");
		String selectedDest = request.getParameter("dest");//airport code
		String selectedDestFN = request.getParameter("destFullName");
		String selectedFare = request.getParameter("fareUnitCost");
		String selectedDate = request.getParameter("date");
		String selectedDirection = session.getAttribute("flightDirection").toString().toLowerCase();
		System.out.println("selectedDirection = " + selectedDirection);
		
		//Formatting necessary String data
		BigDecimal test = BigDecimal.valueOf(Double.valueOf(selectedFare));
		int passengers = Integer.parseInt(session.getAttribute("passengers").toString());
		BigDecimal totalCost = test.multiply(new BigDecimal(passengers));
				
		//Debug Statements
		System.out.println("selectedFlightNumber = " + selectedFlightNumber);
		System.out.println("selectedAirline = " + selectedAirline);
		System.out.println("selectedTravelClass = " + selectedTravelClass);
		System.out.println("selectedSource = " + selectedSource);
		System.out.println("selectedSourceFN = " + selectedSourceFN);
		System.out.println("selectedDest = " + selectedDest);
		System.out.println("selectedDestFN = " + selectedDestFN);
		System.out.println("selectedFare = " + selectedFare);
		System.out.println("selectedDay = " + selectedDay);
		System.out.println("selectedDate = " + selectedDate);
		System.out.println("passengers (session) = " + passengers);		
		System.out.println("total cost = " + totalCost);
		
		//3. Created a temporary ticket with the data. 
		//The object will passed along to the payment confirmation screen
		//tempTicket.bookingid will be assigned at the database level
		tempTicket.setUserid(customer.getUserid());
		tempTicket.setBookingStatusid(1); //1-pending; will get set to 2-confirmed when selected button is pressed, and 3-purchased when payment has been processed
		//tempTicket directionid logic
		switch(selectedDirection) {			
			case "one-way":
			case "oneway":
				tempTicket.setDirectionid(1);
				break;		
			
			case "roundtrip":
			case "round trip":
			default:
				tempTicket.setDirectionid(2);
				break;
		}
		tempTicket.setFlightNumber(Integer.parseInt(selectedFlightNumber));
		//tempTicket travelClass logic
		switch(selectedTravelClass.toLowerCase()) {
		case "business":
			tempTicket.setTravelClassid(2);
			break;
			
		case "first/premium class":
			tempTicket.setTravelClassid(3);
			break;
			
		case "economy":
		default:	
			tempTicket.setTravelClassid(1);
			break;		
		}
		tempTicket.setTravelDay(selectedDay);
		tempTicket.setTravelDate(selectedDate);
		tempTicket.setTotalPassengers(passengers);
		tempTicket.setTotalFare(totalCost);		
		tempTicket.setSrcAirportCode(selectedSource);
		tempTicket.setSrcAiportFullName(selectedSourceFN);
		tempTicket.setDestAirportCode(selectedDest);
		tempTicket.setDestAirportFullName(selectedDestFN);
		System.out.println("Temp Ticket: \n" + tempTicket.toString());
		
		//4. Add results to request object for the search-results.page.jsp
		request.setAttribute("selectedFlightNumber", selectedFlightNumber);
		request.setAttribute("selectedTravelClass", selectedTravelClass);
		request.setAttribute("selectedTotalCost", totalCost);
		
		session.setAttribute("selectedAirline", selectedAirline);		
		session.setAttribute("selectedSrc", selectedSource);
		session.setAttribute("selectedSourceFN", selectedSourceFN);
		session.setAttribute("selectedDest", selectedDest);
		session.setAttribute("selectedDestFN", selectedDestFN);
		session.setAttribute("selectedDay", selectedDay);		
		session.setAttribute("selectedDate", selectedDate);
		session.setAttribute("pendingTicket", tempTicket); //will be sent to the database in the next step
		
		//Step 5. send to jsp form
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/booking-details.jsp"); 
		dispatcher.forward(request, response);
		
	}//end bookFare()
	
	/* ************************************************************* *
	 * 																 *
	 * Methods that I created, but didn't use. My logic was to have	 * 
	 * create application specific information that that came from 	 *
	 * the database tables that had application scope 				 *
	 * (i.e. countries, userroles, travel classes, etc.)			 *
	 * Due to time constraints, I was not able to fully implement	 *
	 * the intended logic completely.								 * 
	 * 																 * 	
	 * ************************************************************* */
	private void listAirports(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside BookFlightControllerServlet listAirports()...");
		//1. Reading the Cities from the database using the DB Util
		List<String> airportCodes = bookingDbUtil.getAirportCodes();
		System.out.println("airportCodes: ");
		System.out.println(airportCodes);
		
		//2. Add cities list to the request object
		request.setAttribute("AIRPORT_CODES_LIST", airportCodes);
		
		List<String> airportNames = bookingDbUtil.getAirportNames();
		System.out.println("airport names: ");
		System.out.println(airportNames);
		
		//2. Add cities list to the request object
		request.setAttribute("AIRPORT_NAMES_LIST", airportNames);
				
		//3. Send to book-flight.jsp JSP page (view) using dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-flight.jsp");
		dispatcher.forward(request, response);
	}//end listAirports()
	
	private void listAirportCodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside BookFlightControllerServlet listAirportCodes()...");
		//1. Reading the Cities from the database using the DB Util
		List<String> airportCodes = bookingDbUtil.getAirportCodes();
		System.out.println("airportCodes: ");
		System.out.println(airportCodes);
		
		//2. Add cities list to the request object
		request.setAttribute("AIRPORT_CODES_LIST", airportCodes);		
				
		//3. Send to book-flight.jsp JSP page (view) using dispatcher
		//RequestDispatcher dispatcher = request.getRequestDispatcher("book-flight.jsp");
		//dispatcher.forward(request, response);
	}//end listAirportCodes()
	
	private void listAirportNames(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside BookFlightControllerServlet listAirportNames()...");
		//1. Reading the Cities from the database using the DB Util
		List<String> airportNames = bookingDbUtil.getAirportNames();
		System.out.println("airport names: ");
		System.out.println(airportNames );
		
		//2. Add cities list to the request object
		request.setAttribute("AIRPORT_NAMES_LIST", airportNames);		
				
		//3. Send to book-flight.jsp JSP page (view) using dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-flight.jsp");
		dispatcher.forward(request, response);
	}//end listAirportNames()

}//end servlet
