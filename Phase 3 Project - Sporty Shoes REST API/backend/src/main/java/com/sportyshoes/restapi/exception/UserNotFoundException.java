package com.sportyshoes.restapi.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {        
       super(message);
       System.out.println(message);
    }
}//end class
