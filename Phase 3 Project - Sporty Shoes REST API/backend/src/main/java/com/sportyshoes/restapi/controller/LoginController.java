package com.sportyshoes.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	/* This endpoint ensures whenever there is an invocation to the login URL (e.g.,
	   http:/ /localhost:8080/login), the login.html page is presented to the user. */
	@GetMapping("/login")
	public String login() {
		return ("login");
	}

}//end class
