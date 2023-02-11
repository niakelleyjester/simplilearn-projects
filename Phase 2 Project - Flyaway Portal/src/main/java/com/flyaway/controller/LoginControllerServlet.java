package com.flyaway.controller;

import java.io.IOException;

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
import com.flyaway.model.User;


@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	//Properties
	private static final long serialVersionUID = 1L;
    private UserDbUtil userDbUtil;    
    private String username; //Variable for the form data
    private String email; //Variable for the form data
    private String password; //Variable for the form data
    private User loggedInUser = null;
    private boolean isAdmin = false;
    
    //Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/flyaway") //comes from the context.xml (alternatively, I could have used properties file. Chose this method for more readable code.)
    private DataSource dataSource;
    
    //Constructors    
    public LoginControllerServlet() {
        // TODO Auto-generated constructor stub
    }

	//Methods
    //Ultimately, we want to initialize UserDbUtil. 
  	/* In the Servlet lifecyle, they have a special method that you can override called init()
  	 * The init() method will be called by the Java EE app server or by Tomcat when this servlet is first loaded/initialized
  	 * This is a good place for you to put your own custom initialization work, inside of this init() method 
  	 * So work that you would normally do in a constructor, when you're working with servlets, 
  	 * you can put that code inside the init method.  
  	 */	
  	@Override
  	public void init() throws ServletException {
  		
  		super.init();
  		
  		//create our datasource, and pass in the connection pool/datasource
  		try {
  			userDbUtil = new UserDbUtil(dataSource);
  		}
  		catch (Exception e) {
  			throw new ServletException(e); //in case there is an error reading from the database or other problem
  		}
  		
  	}//end init()	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//try-catch block
		try {
			HttpSession session = request.getSession();
			boolean usernameStatus = false;
			boolean passwordStatus = false;
			boolean emailStatus = false;			
			
			getFormData(request, response);
			usernameStatus = validateUsername(request,response);
			passwordStatus = validatePassword(request,response);
			System.out.println("usernameStatus = " + usernameStatus);
			System.out.println("passwordStatus = " + passwordStatus);
			
			if(usernameStatus && passwordStatus) {
			
				System.out.println("Adding the logged in user to the session object...");
				this.loggedInUser = authenticateUser(request, response);
				System.out.println("this.loggedInUser: \n" + this.loggedInUser.toString());
				session.setAttribute("loggedInUser", this.loggedInUser);				
				//session.setAttribute("loggedInUsername",this.username);
				emailStatus = validateEmail(request, response);
				System.out.println("emailStatus = " + emailStatus);
				this.isAdmin = checkUserRole(request, response);
				System.out.println("isAdmin = " + isAdmin);
				System.out.println("Adding the isAdmin user state to the session object...");
				session.setAttribute("isAdminUser", isAdmin);
			}
			
			//4. Send to index.jsp JSP page (view) using dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);			
			
		}catch (Exception e) {			
			e.printStackTrace();
			throw new ServletException(e);
		} 
	}//end doPost()
	

	/* *********** METHODS ************/
	private void getFormData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside LoginControllerServlet getFormData()...");
		
		//1. Read info from login form and set respective class properties		
		this.username = request.getParameter("userName");
		this.email = request.getParameter("email");
		this.password = request.getParameter("password");
		
		System.out.println("username from form: " + username);
		System.out.println("email from form: " + email);
		System.out.println("password from form: " + password);
		
	}//end getFormData()	
	
	private boolean validateUsername(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside LoginControllerServlet validateUsername()...");
		boolean checkUsername=false;
		
		//Lookup up username entered on the form in the database
		checkUsername = userDbUtil.checkUsername(this.username);
		
		return checkUsername;
		
	}//end validateUsername()
	
	private boolean validatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside LoginControllerServlet validatePassword()...");
		boolean checkPassword = false;
		
		//lookup password for the user
		checkPassword = userDbUtil.checkPassword(this.username, this.password);
		
		return checkPassword;		
	}//end validatePassword()
	
	private boolean validateEmail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside LoginControllerServlet validatePassword()...");
		boolean checkEmail = false;
		
		//lookup email for the user
		checkEmail = userDbUtil.checkEmail(this.username, this.password, this.email);
		
		return checkEmail;		
	}//end validateEmail()
	
	private boolean checkUserRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Inside LoginControllerServlet validatePassword()...");
		String userRole = "";
		boolean userIsAdmin = false;
		
		//lookup email for the user
		userRole = userDbUtil.checkUserRole(this.username);
		
		if(userRole.equals("admin"))
			userIsAdmin = true;
		
		return userIsAdmin;		
	}//end checkUserRole()

	private User authenticateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		User tempUser = new User();
		tempUser = userDbUtil.getUser(this.username, this.password);
		return tempUser;		
	}//end authenticateUser()
}//end servlet
