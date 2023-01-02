package com.sportyshoes.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {	
	
	@GetMapping("/home")
	public String home() {
		return "<h1>Home Page</h1><p>This is the home page</p>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>Admin Page</h1><p>This is the Admin page</p>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>User Page</h1><p>This is the User page</p>";
	}


}//end class
