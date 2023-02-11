package com.flyaway.jdbc.utilities;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.flyaway.model.BookingHistory;
import com.flyaway.model.User;

public class UserDbUtil {
	
	//Properties	
	private DataSource dataSource;
	
	//Constructor with Dependency injection
	public UserDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//Default constructor
	public UserDbUtil() {}
	
	//Methods
	@Override
	public String toString() {
		return "UserDbUtil [dataSource=" + dataSource + "]";
	}
	
	/* ****** Database - CRUD METHODS ********************** *
	 * 										                 *
	 * 		Create (addUser)                                *
	 * 		Read (getUsers)                                  *
	 *  	Update (updateUser)                              *
	 *  	Delete (deleteUser)	                             *
	 *                                                       *
	 * ***************************************************** */
	public void addUser(User user) throws Exception
	{		
		System.out.println("Inside UserDbUtil.addUser(User user)...");
		System.out.println("tempUser object..\n" + user.toString());
		
		Connection myConn = null;
		PreparedStatement ps = null;
		
		try {				
				
				//Step 1. Establishing a connection
				System.out.println("1. Establishing a connection to the database..");
				myConn = dataSource.getConnection();
				System.out.println("myConn = " + myConn.toString());
				
				//Step 2. Create a SQL parameterized statement with placeholders
				System.out.println("2. Create SQL insert statement..");
				String sql = "INSERT INTO users" 
							+ " (firstname, lastname, username, email, password, phoneNumber, roleid)"
						    + " VALUES (?,?,?,?,?,?, ?)";
			
				ps = myConn.prepareStatement(sql);

				//Step 3. Set position and parameter values for the prepared statement
				System.out.println("3. Set position and parameter values for the prepared statement..");
				ps.setString(1, user.getFirstName()); //remember setString is one-based!!
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getUserName());
				ps.setString(4, user.getEmail());
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getPhoneNumber());
				ps.setInt(7, user.getRoleid());				
				
				//Step 4. Execute the query
				System.out.println("4. Executing the insert query...");
				ps.executeUpdate(); //Executes the SQL statement in this PreparedStatement object
		}finally {
			//Step 5. Close the connection & JDBC objects
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, ps, null);
		}
		
	}//end addUser()	

	public List<User> getUsers() throws Exception{
		
		System.out.println("Inside UserDbUtil.getUsers()...");
		
		List<User> users = new ArrayList<>();	//empty list	
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
			String sql = "SELECT * FROM users"
						+ " ORDER BY lastname"; //column name in the database is "lastname"
			myStmt = myConn.createStatement();
			
			//Step 3. Execute the query.
			System.out.println("3. Execute the select query...");
			myRs = myStmt.executeQuery(sql);
			
			//Step 4. Process the ResultSet object.
			System.out.println("4. Process the ResultSet Object...");
			while(myRs.next()) {
				
				//retrieve data from result set row
				int id = myRs.getInt("userid"); //column name in the database is "userid"
				int roleid = myRs.getInt("roleid");//column name is "roleid"
				String firstName = myRs.getString("firstname"); //column name in the database is "firstname"
				String lastName = myRs.getString("lastname"); //column name in the database is "lastname"
				String username = myRs.getString("username");//column name is "username"
				String email = myRs.getString("email"); //column name in the database is "email"
				String pwd = myRs.getString("password");//column name in the database is "password"
				String phoneNumber = myRs.getString("phoneNumber"); //column name in the database is "phoneNumber"
				String role = "";
				
				switch(roleid) {
				case 1:
					role = "Admin";
					break;
				case 2:
					role = "Customer";
					break;
				case 3:
				default:
					role = "Guest";
					break;
					
				}//end switch				
				
				//create new User object based on the result set row
				//System.out.println("Creating tempUser object...");
				User tempUser = new User(id, firstName, lastName, username, email, pwd, phoneNumber, roleid, role);
								
				//add User object to the list of Users (not writing to the database)
				//System.out.println("Add the tempUser to the users ArrayList");
				users.add(tempUser);			
			}//end while()
			
			return users;
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			System.out.println("5. Close the connection & JDBC objects...");
			DBUtil.close(myConn, myStmt, myRs);
		}		
	}//end getUsers()
	
	public void updateUser(User user) throws Exception{
		System.out.println("Inside UserDbUtil.updateUser(User user)...");
		System.out.println("user object...\n" + user.toString());		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		//try-finally block
		try {
			
			//Step 1. Establishing a connection.
			System.out.println("1. Establishing a connection to the database..");
			myConn = dataSource.getConnection();
			System.out.println("myConn = " + myConn.toString());
			
			//Step 2. Create a SQL parameterized statement with placeholders.
			System.out.println("2. Create SQL update statement..");
			String sql = "UPDATE users"
						+ " SET firstname=?, lastname=?, username=?, email=?, password=?, phoneNumber=?"				
						+ " WHERE userid=?"; //use database column names in the statement
			System.out.println("sql = " + sql);
			myStmt = myConn.prepareStatement(sql);
			//System.out.println("myStmt = \n" + myStmt.toString());
			
			//Step 3. Set position and param values for prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myStmt.setString(1, user.getFirstName());//remember that setString is one-based!!
			System.out.println("user.getFirstName(): " + user.getFirstName());
			myStmt.setString(2, user.getLastName());
			System.out.println("user.getLastName(): "+user.getLastName());
			myStmt.setString(3, user.getUserName());
			System.out.println("user.getUserName(): " + user.getUserName());
			myStmt.setString(4, user.getEmail());
			System.out.println("user.getEmail(): " + user.getEmail());
			myStmt.setString(5, user.getPassword());
			System.out.println("user.getPassword(): " + user.getPassword());
			myStmt.setString(6, user.getPhoneNumber());	
			System.out.println("user.getPhoneNumber(): " + user.getPhoneNumber());
			myStmt.setInt(7, user.getUserid());
			System.out.println("user.getUserid(): " + user.getUserid());
			System.out.println("myStmt = \n" + myStmt.toString());
			
			//Step 4. Execute the query.
			System.out.println("4. Executing the update query...");
			myStmt.execute(); //Executes the SQL statement in this PreparedStatement object, which may be any kind of SQL statement. There is no result set returned.	
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
		}
		
	}//end updateUser()
	
	public void deleteUser(int userId) throws Exception{
		System.out.println("Inside UserDbUtil.deleteUser(int userid)...");
		System.out.println("userid = " + userId);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		//try-finally block
		try {
			
			//Step 1. Establishing a connection.
			myConn = dataSource.getConnection();
			
			//Step 2. Create a SQL parameterized statement with placeholders.
			String sql = "delete from users where userid=? ";
			
			myStmt = myConn.prepareStatement(sql);
			
			//Step 3. Set position and param values for prepared statement		
			myStmt.setInt(1, userId); //remember that setString is one-based!!		 
			
			//Step 4. Execute the query.
			myStmt.execute(); //Executes the SQL statement in this PreparedStatement object, which may be any kind of SQL statement. There is no result set returned.		
			}
			finally {
				//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
				DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
			}
	}//end deleteUser()
	
	/* ******************************************* * 
	 * 			UTILITY METHODS 	               *
	 * 											   * 		 
	 * 	getUser(getUsers)                          *
	 *  checkUsername(username)					   *	
	 * ******************************************* */	
	public User getUser(int userId) throws Exception {
		
		System.out.println("Inside UserDbUtil.getUser(int userId)...");
		System.out.println("userId = " + userId);
		
		User tempUser = null;		
		
		//Define JDBC object
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		//try-finally block
		try {			
			
			//get connection to the database			
			myConn = dataSource.getConnection();
			
			//create sql to get selected user
			String sql = "SELECT * FROM users"
						+ " WHERE userid=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, userId);
			
			//execute statement
			myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
			
			//retrieve data from result set row
			if(myRs.next()) {
				int roleid = myRs.getInt("roleid");//column name is "roleid"
				String firstName = myRs.getString("firstname"); //column name in the database is "firstname"
				String lastName = myRs.getString("lastname"); //column name in the database is "lastname"
				String userName = myRs.getString("username");//column name is "username"
				String email = myRs.getString("email"); //column name in the database is "email"
				String pwd = myRs.getString("password"); //column name in the database is "password"
				String phoneNumber = myRs.getString("phoneNumber"); //column name in the database is "phoneNumber"
				String role = "";
				
				switch(roleid) {
				case 1:
					role = "Admin";
					break;
				case 2:
					role = "Customer";
					break;
				case 3:
				default:
					role = "Guest";
					break;
					
				}//end switch	
				//use the userId during construction
				tempUser = new User(userId, firstName, lastName, userName, email, pwd, phoneNumber, roleid, role);
				//System.out.println("tempUser = \n" + tempUser.toString());
			}
			
			else {
				throw new 
				Exception("Could  not find user id: " + userId);
			}
			System.out.println("tempUser = \n" + tempUser.toString());
			return tempUser;//end method
		}
		finally {
			//clean up JDBC objects
			DBUtil.close(myConn, myStmt, myRs);			
		}
		
	}//end getUser()
	
	public User getUser(String uname, String pass) throws Exception {
		
		System.out.println("Inside UserDbUtil.getUser(String uname)...");
		System.out.println("Passed in uname = " + uname);
		System.out.println("Passed in pass = " + pass);
		
		User tempUser = null;		
		
		//Define JDBC object
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		//try-finally block
		try {			
			
			//get connection to the database			
			myConn = dataSource.getConnection();
			
			//create sql to get selected user
			String sql = "SELECT users.userid"
						+ " ,users.roleid"
						+ " ,userroles.roletype"
						+ " ,users.firstname"
						+ " ,users.lastname"
						+ " ,users.username"
						+ " ,users.email"
						+ " ,users.password"
						+ " ,users.phonenumber"
						+ " FROM users"						
						+ " JOIN userroles ON users.roleid=userroles.roleid"
						+ " WHERE users.username=?"
						+ " AND users.password=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, uname);
			myStmt.setString(2, pass);
			
			//execute statement
			myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
			
			//retrieve data from result set row
			if(myRs.next()) {
				int userid = myRs.getInt("users.userid");
				int roleid = myRs.getInt("users.roleid");
				String role = myRs.getString("userroles.roletype");
				String firstName = myRs.getString("users.firstname"); 
				String lastName = myRs.getString("users.lastname"); 
				String userName = myRs.getString("users.username");
				String email = myRs.getString("users.email"); 
				String pwd = myRs.getString("users.password"); 
				String phoneNumber = myRs.getString("users.phonenumber"); 				
	
				//use the userId during construction
				tempUser = new User();
				tempUser.setUserid(userid);
				tempUser.setRoleid(roleid);
				tempUser.setRole(role);
				tempUser.setFirstName(firstName);
				tempUser.setLastName(lastName);
				tempUser.setUserName(userName);
				tempUser.setEmail(email);
				tempUser.setPassword(pwd);
				tempUser.setPhoneNumber(phoneNumber);
			}
			
			else {
				System.out.println("Could  not find username: " + uname);
			}
			System.out.println("tempUser = \n" + tempUser.toString());
			return tempUser;//end method
		}
		finally {
			//clean up JDBC objects
			DBUtil.close(myConn, myStmt, myRs);			
		}
		
	}//end getUser()

	public boolean checkUsername(String uname) throws Exception {
		
		boolean checkUser = false;
		
		//Define JDBC object
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		System.out.println("Inside UserDbUtil.checkUsername(String username)...");
		System.out.println("username passed in = " + uname);
				
		//try-finally block
		try {			
			
			//get connection to the database			
			myConn = dataSource.getConnection();
			
			//create sql to get selected user
			String sql = "SELECT * FROM users"
					+ " WHERE username=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, uname);
			
			//execute statement
			myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
			
			//retrieve data from result set row
			if(myRs.next()) {
				checkUser = true;
				int userid = myRs.getInt("userid");//column name is "userid"
				System.out.println("Found matching User ID = " + userid);
			}
			else {
				//didn't really need an exception, but a error message shown on the JSP is appropriate
				 /* throw new 
				Exception("Could  not find username: " + uname); */
				System.out.println("Could  not find username in the database: " + uname);
			}			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
		}
		
		return checkUser;//end method
	}//end checkUsername()
	
	public boolean checkPassword(String uname, String pass) throws Exception {
			
			boolean checkPass = false;
			
			//Define JDBC object
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			System.out.println("Inside UserDbUtil.checkPassword(String username, String pass)...");
			System.out.println("username passed in = " + uname);
			System.out.println("pass passed in = " + pass);
					
			//try-finally block
			try {			
				
				//get connection to the database			
				myConn = dataSource.getConnection();
				
				//create sql to get selected user
				String sql = "SELECT * FROM users"
							+ " WHERE username=?"
							+ " AND password=?";
				
				//create prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				//set params
				myStmt.setString(1, uname);
				myStmt.setString(2, pass);
				
				//execute statement
				myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
				
				//retrieve data from result set row
				if(myRs.next()) {
					checkPass = true;
					int userid = myRs.getInt("userid");//column name is "userid"
					System.out.println("Found matching User ID = " + userid);
				}
				else {
					//didn't really need an exception, but a error message shown on the JSP is appropriate
					 /* throw new 
					Exception("Could  not find username: " + uname); */
					System.out.println("Could not find matching password for username: " + uname + " in the database: ");
				}			
			}
			finally {
				//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
				DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
			}
			
			return checkPass;//end method
		}//end checkPassword()

	public boolean checkEmail(String uname, String pass, String email) throws Exception {
		
		boolean checkEmail = false;
		
		//Define JDBC object
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		System.out.println("Inside UserDbUtil.checkEmail(String username, String pass, String email)...");
		System.out.println("username passed in = " + uname);
		System.out.println("pass passed in = " + pass);
		System.out.println("email passed in = " + email);
				
		//try-finally block
		try {			
			
			//get connection to the database			
			myConn = dataSource.getConnection();
			
			//create sql to get selected user
			String sql = "SELECT * FROM users"
						+ " WHERE username=?"
						+ " AND password=?"
						+ " AND email=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, uname);
			myStmt.setString(2, pass);
			myStmt.setString(3, email);
			
			//execute statement
			myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
			
			//retrieve data from result set row
			if(myRs.next()) {
				checkEmail = true;
				int userid = myRs.getInt("userid");//column name is "userid"
				System.out.println("Found matching User ID = " + userid);
			}
			else {				
				System.out.println("Could not find matching password for username: " + uname + " in the database: ");
			}			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
		}
		
		return checkEmail;//end method
	}//end checkEmail()

	public String checkUserRole(String uname) throws Exception {
		
		String userRoleString = "";
		
		//Define JDBC object
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		System.out.println("Inside UserDbUtil.checkUserRole(String username, String pass, String email)...");
		System.out.println("username passed in = " + uname);	
				
		//try-finally block
		try {			
			
			//get connection to the database			
			myConn = dataSource.getConnection();
			
			//create sql to get selected user
			String sql = "SELECT users.userid"
					+ " ,users.username"
					+ " ,userroles.roletype"
					+ " FROM users"
					+ " JOIN userroles ON users.roleid=userroles.roleid"
					+ " WHERE users.username=?";
			System.out.println("sql = " + sql);
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, uname);		
			
			//execute statement
			myRs = myStmt.executeQuery();//Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.
			
			//retrieve data from result set row
			if(myRs.next()) {			
				userRoleString = myRs.getString("userroles.roletype");//column name is "userid"			
			}
			else {
				//didn't really need an exception, but a error message shown on the JSP is appropriate
				 /* throw new 
				Exception("Could  not find username: " + uname); */
				System.out.println("Could not find matching password for username: " + uname + " in the database: ");
			}			
		}
		finally {
			//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
			DBUtil.close(myConn, myStmt, null); //since we don't have a resultset object, we can pass in null for the 3rd argument
		}
		
		return userRoleString.toLowerCase();//end method
	}//end checkUserRole()
	
	public List<BookingHistory> getBookingHistory(int userid) throws Exception{
		System.out.println("Inside UserDbUtil.getBookingHistory(int userId)...");
		System.out.println("userId = " + userid);
		
		List<BookingHistory> bookingHistory = new ArrayList<>(); //empty list		
		
		//Define JDBC object
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
			String sql = "SELECT ticketdetails.bookingid"
						+ " ,bookingstatus.bookingstatusname"
						+ " ,ticketdetails.purchaseDate"
						+ " ,ticketdetails.flightnumber"
				        + " ,airlines.companyName"
				        + " ,travelclass.travelclassName"
				        + " ,ticketdetails.travelDate"
				        + " ,flightdetails.src_airportCode"        
				        + " ,flightdetails.dest_airportCode"
				        + " ,ticketdetails.totalPassengers"
				        + " ,ticketdetails.totalfare"
						+ " FROM ticketdetails"
				        + " JOIN flightdetails ON ticketdetails.flightnumber=flightdetails.flightnumber"
				        + " JOIN airlines ON flightdetails.airlineid=airlines.airlineid"
				        + " JOIN bookingstatus ON ticketdetails.bookingstatusid=bookingstatus.bookingstatusid"
				        + " JOIN travelclass ON ticketdetails.travelclassid=travelclass.travelclassid"
						+ " WHERE ticketdetails.userid=?";			
			System.out.println("sql = " + sql);			
			myPs = myConn.prepareStatement(sql);
			
			//Step 3. Set position and parameter values for the prepared statement
			System.out.println("3. Set position and parameter values for the prepared statement..");
			myPs.setInt(1, userid);
			
			//Step 4. Execute the query
			myRs = myPs.executeQuery();
			
			//Step 5. Process the ResultSet object.
			System.out.println("5. Process the ResultSet Object...");
			
			while(myRs.next()) {
				//retrieve data from result set row				
				int bid = myRs.getInt("ticketdetails.bookingid");
				String bookingStatus = myRs.getString("bookingstatus.bookingstatusname");				
				String purchaseDate = myRs.getString("ticketdetails.purchaseDate");
				int flightNum = myRs.getInt("ticketdetails.flightnumber");
				String airlineName = myRs.getString("airlines.companyName");
				String travelClassName = myRs.getString("travelclass.travelclassName");
				String travelDate = myRs.getString("ticketdetails.travelDate");
				String srcAirportCode = myRs.getString("flightdetails.src_airportCode");        
				String destAirportCode = myRs.getString("flightdetails.dest_airportCode");
				int ticketPassengers = myRs.getInt("ticketdetails.totalPassengers");
		        BigDecimal totalFare = myRs.getBigDecimal("ticketdetails.totalfare");
				
				
				//create new BookingHistory object based on result set
				BookingHistory tempBH = new BookingHistory(bid, bookingStatus, purchaseDate, flightNum
						,airlineName, travelClassName, travelDate, srcAirportCode, destAirportCode, ticketPassengers, totalFare);
				
				//add tempBH object to the list of bookingHistory (not writing to the database)				
				bookingHistory.add(tempBH);
			}//end while()	
			
			return bookingHistory;
			}
			finally {
				//Step 5. Close the connection & JDBC objects. (clean-up code to ensure that you don't run out of resources, esp for prod)
				System.out.println("5. Close the connection & JDBC objects...");
				//DBUtil.close(myConn, myStmt, myRs);
				DBUtil.close(myConn, myPs, myRs);
			}	
	}//end getBookingHistory

}//end class
