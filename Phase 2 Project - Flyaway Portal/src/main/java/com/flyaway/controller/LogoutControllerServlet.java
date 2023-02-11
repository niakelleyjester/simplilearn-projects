package com.flyaway.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutControllerServlet")
public class LogoutControllerServlet extends HttpServlet {
	
	//Properties
	private static final long serialVersionUID = 1L;
	
    
    //Methods     	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//try-catch block
		try {
			HttpSession session = request.getSession();
			session.invalidate(); //When this invalidate method is called on the session, it removes all the objects that are bound to that session.
			
			//Send to index.jsp JSP page (view) using dispatcher
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);	
			
		}catch (Exception e) {			
			e.printStackTrace();
			throw new ServletException(e);
		} 
	}//end doPost()

}//end servlet
