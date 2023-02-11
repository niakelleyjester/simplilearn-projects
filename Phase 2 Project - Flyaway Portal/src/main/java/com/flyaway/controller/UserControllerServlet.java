package com.flyaway.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.flyaway.jdbc.utilities.UserDbUtil;
import com.flyaway.model.Airport;
import com.flyaway.model.BookingHistory;
import com.flyaway.model.User;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	//Properties
	private static final long serialVersionUID = 1L;
    private UserDbUtil userDbUtil;
    
    //Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/flyaway") //comes from the context.xml (alternatively, I could have used properties file. Chose this method for more readable code.)
    private DataSource dataSource;

    //Methods
       	
  	@Override
  	public void init() throws ServletException {
  		//Ultimately, we want to initialize UserDbUtil.
  		/* In the Servlet lifecyle, they have a special method that you can override called init()
  	  	 * The init() method will be called by the Java EE app server or by Tomcat when this servlet is first loaded/initialized
  	  	 * This is a good place for you to put your own custom initialization work, inside of this init() method 
  	  	 * So work that you would normally do in a constructor, when you're working with servlets, 
  	  	 * you can put that code inside the init method.  
  	  	 */	
  		  		
  		//create our datasource, and pass in the connection pool/datasource
  		try {
  			userDbUtil = new UserDbUtil(dataSource);
  		}
  		catch (Exception e) {
  			throw new ServletException(e); //in case there is an error reading from the database or other problem
  		}
  		
  	}//end init()
  	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//try-catch block
		try {
			
			System.out.println("Inside UserControllerServlet doGet()...");
			
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			System.out.println("theCommand = " + theCommand);
			
			//if the command is missing, then default to listing users
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method in the UserDbUtil	
			switch(theCommand) {			
			
			case "LIST":				
				System.out.println("case: LIST...");
				System.out.println("hidden command = " + theCommand);
				listUsers(request, response);
				break;
				
			case "ADD":				
				System.out.println("case: ADD...");
				System.out.println("hidden command = " + theCommand);
				addUser(request, response);
				break;
				
			case "EDIT":
			case "UPDATE":
				System.out.println("case: EDIT/UPDATE...");
				System.out.println("hidden command = " + theCommand);
				updateUser(request, response);
				break;
				
			case "LOAD":
				System.out.println("case: LOAD...");
				System.out.println("hidden command = " + theCommand);
				loadUser(request, response);
				break;
				
			case "HISTORY":
				System.out.println("case: HISTORY...");
				System.out.println("hidden command = " + theCommand);
				viewBookingHistory(request, response);
				break;
				
			case "DELETE":
				System.out.println("case: DELETE...");
				System.out.println("hidden command = " + theCommand);
				deleteUser(request, response);
				break;
				
			default:
				System.out.println("case: default...");
				System.out.println("hidden command = " + theCommand);				
				listUsers(request, response);
				break;
			}//end switch	
			
		} catch (Exception e) {			
			e.printStackTrace();
			throw new ServletException(e);
		} 
		
	}//end doGet()

	/* *********** CRUD METHODS ************/
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Inside UserControllerServlet listUsers()...");
		
		//1. Reading the Users from the database using the DB Util
		List<User> users = userDbUtil.getUsers();
		System.out.println("user list: ");
		System.out.println(users);
		
		//2. Add users list to the request object
		request.setAttribute("USERS_LIST", users);		
				
		//3. Send to list-users.jsp JSP page (view) using dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-users.jsp");
		dispatcher.forward(request, response);		
	}//end listUsers()
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Inside UserControllerServlet addUser()...");
		
		//1. Read info from form
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");		
		String phone = request.getParameter("phonenumber");
		String role = request.getParameter("userrole");		
		
		System.out.println("fname = " + fname);
		System.out.println("lname = " + lname);
		System.out.println("username = " + username);
		System.out.println("email = " + email);
		System.out.println("password = " + password);
		System.out.println("phonenumber = " + phone);
		System.out.println("userrole = " + role);
		
		//2. Create a new User object
		User tempUser = new User(fname, lname, username, email, password, phone, role);
		
		//3. Add the User to the database
		userDbUtil.addUser(tempUser);
		
		//4. Send back to the main page and refresh the User list
		listUsers(request, response);
		
	}//end addUser()
	
	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside UserControllerServlet.loadUser()...");
		//Step 1. read user id from form data
		int theUserId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("theUserId = " + theUserId);
		
		//Step 2. get user from database via db util
		User user = userDbUtil.getUser(theUserId);
		System.out.println("user object is: \n" + user.toString());
		
		//Step 3. place student in the request attribute
		request.setAttribute("THE_USER", user); //(attribute name, object)
		
		//Step 4. send to jsp page: update-user-form.jsp and pre-populate database information
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-user-form.jsp");
		dispatcher.forward(request, response);
		
	}//end loadUser()
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		//Step 1. read values from update-user-form
		int id = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");				
		String phoneNumber = request.getParameter("phoneNumber");
		
		//Step 2. create a new user object
		User user = new User(id, firstName, lastName, userName, email, pwd, phoneNumber);
		
		//Step 3. perform update on the database
		userDbUtil.updateUser(user);
		
		if(session.getAttribute("isAdminUser").toString().toLowerCase().equals("admin")) {
			//Step 4. send to "list users" page (for admin users only)
			listUsers(request, response);
		}
		else {
			//Step 4. send to jsp page: update-user-form.jsp and pre-populate database information
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		
	}//end updateUser()
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// Step 1. read user id from update-user-form data
		int id = Integer.parseInt(request.getParameter("userId"));
		
		// Step 2. delete User from database
		userDbUtil.deleteUser(id);
		
		// Step 3. redirect to the "list users" page
		listUsers(request, response);
		
	}//end deleteUser()

	private void viewBookingHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside UserControllerServlet.viewBookingHistory()");
		
		HttpSession session = request.getSession();
		List<Airport> airports = (List<Airport>) session.getAttribute("CITIES_LIST");
		System.out.println("airports list: \n" + airports.toString());
		
		//Step 1. read user id from form data
		int theUserId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("theUserId = " + theUserId);
		
		//Step 2. get requested information from database via db util
		List<BookingHistory> bh = userDbUtil.getBookingHistory(theUserId);
		System.out.println("booking history list: \n" + bh.toString());
		
		//Fill in travel day, source airport name, dest airport name
		for(BookingHistory x: bh) {
			String tempSrcCode = x.getSourceAirportCode();
			String tempDestCode = x.getDestAirportCode();
			String tempTravelDate = x.getTravelDate();
			
			//1b. Formatting Date and Day fields
			SimpleDateFormat sdfDepart = new SimpleDateFormat("yyyy-MM-dd");
			Date departDate = sdfDepart.parse(tempTravelDate); //Date object has date and time
			String tempDay = new SimpleDateFormat("EEEE").format(departDate);
			x.setTravelDay(tempDay);
			
			for(Airport y: airports) {
				if(tempSrcCode.equals(y.getAirportCode())){
					System.out.println("airport codes match!");
					x.setSourceAirportName(y.getAirportName());
				}//end tempSrcCode
				if(tempDestCode.equals(y.getAirportCode())){
					System.out.println("airport codes match!");
					x.setDestAirportName(y.getAirportName());
				}//end tempDestCode
			}//end forEach
		}//end forEach
		
		//Step 3. place any necessary data in the request or session
		request.setAttribute("BOOKING_HISTORY_LIST", bh); //(attribute name, object)
		
		//Step 4. send to jsp page: update-user-form.jsp and pre-populate database information
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/booking-history.jsp");
		dispatcher.forward(request, response);
		
	}//end viewBookingHistory()

}//end servlet
