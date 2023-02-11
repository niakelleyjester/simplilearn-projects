package com.flyaway.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;

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
import com.flyaway.model.Ticket;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	//Properties
	private static final long serialVersionUID = 1L;
	private BookingDbUtil bookingDbUtil;
    
    //Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/flyaway") //comes from the context.xml (alternatively, I could have used properties file. Chose this method for more readable code.)
    private DataSource dataSource;
       
    //Default Constructor
    public PaymentServlet() {
    }
    
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
		System.out.println("Inside the PaymentServlet....");
		//try-catch block
			try {
				
				//read the "command" parameter
				String theCommand = request.getParameter("command");
				System.out.println("theCommand = " + theCommand);
				
				//if the command is missing, then default to listing users
				if(theCommand == null) {
					theCommand = "PAY";
				}
				
				//route to the appropriate method in the UserDbUtil	
				switch(theCommand) {
					case "PAY":		
					default:
						System.out.println("case: PAY...");
						System.out.println("hidden command = " + theCommand);
						collectPayment(request, response);
						break;
						
					case "PAYMENT_SUCCESS":					
						System.out.println("case: PAYMENT_SUCCESS...");
						System.out.println("hidden command = " + theCommand);
						finishBooking(request, response);
						break;
				}//end switch
			} catch (Exception e) {			
				e.printStackTrace();
				throw new ServletException(e);
			} 		
	}//end doGet()

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}//end doPost()
	
	private void collectPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Inside PaymentServlet.collectPayment()....");
		
		HttpSession session = request.getSession();
		Ticket tempTicket = (Ticket) session.getAttribute("pendingTicket");
		Ticket savedTicket = new Ticket();
		
		BigDecimal totalCost = tempTicket.getTotalFare();		
		System.out.println("totalCost = " + totalCost);
		System.out.println("tempTicket: \n" + tempTicket.toString());
		
		//update bookingStatus
		System.out.println("Updating booking status to 2-confirmed, since the user confirmed the details...");
		tempTicket.setBookingStatusid(2); //2-confirmed //it will be changed to 3-purchased after successful processing
		
		//save the ticket to the database and get the assigned bookingid from the database
		int x = bookingDbUtil.storeTicket(tempTicket);
		System.out.println("assigning savedTicket to the ticket retrieved from the database ");
		savedTicket = bookingDbUtil.getTicket(x);
		savedTicket.setTravelDay(tempTicket.getTravelDay());		
		savedTicket.setDestAirportCode(tempTicket.getDestAirportCode());
		savedTicket.setSrcAirportCode(tempTicket.getSrcAirportCode());
		savedTicket.setDestAirportFullName(tempTicket.getDestAirportFullName());
		savedTicket.setSrcAiportFullName(tempTicket.getSrcAiportFullName());
		System.out.println("savedTicket: \n" + savedTicket.toString());		
		
		session.setAttribute("savedTicket", savedTicket);
		session.removeAttribute("pendingTicket"); //moved to the end		
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/payment-form.jsp"); 
		dispatcher.forward(request, response);
	}//end collectPayment
	
	private void finishBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Ticket updatedTicket = (Ticket) session.getAttribute("savedTicket");
		
		//update bookingStatusid to 3-purchased
		bookingDbUtil.updateBookingStatus(updatedTicket.getBookingid(), 3);
		request.setAttribute("purchasedTicket", updatedTicket);
		//session.removeAttribute("pendingTicket");
		session.removeAttribute("savedTicket");			
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/payment-confirmation.jsp"); 
		dispatcher.forward(request, response);
		
	}//end finishBooking()

}//end servlet
