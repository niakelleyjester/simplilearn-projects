package com.sportyshoes.restapi.exception;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException(String message) {
		super(message);
		System.out.println(message);
	}

}//end class
