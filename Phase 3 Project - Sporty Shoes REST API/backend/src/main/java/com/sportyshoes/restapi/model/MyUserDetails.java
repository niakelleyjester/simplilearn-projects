package com.sportyshoes.restapi.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{
	//Properties
	private String userName;
	private String password;
	private boolean active;
	private String role;	
	
	//Default Constructor
	public MyUserDetails() {}
	
	//Parameterized Constructor
	public MyUserDetails(User user) {		
		this.userName = user.getUsername();
		this.password = user.getPassword();
		//this.active = user.isActive();
		this.active = true;
		this.role = user.getRoleType();		
	}
	
	//Methods

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return Collections.singleton(new SimpleGrantedAuthority(role)); 
	}

	@Override
	public String getPassword() {		
		return password; 
	}

	@Override
	public String getUsername() {		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return active;
	}

	@Override
	public String toString() {
		return "MyUserDetails [userName=" + userName + ", password=" + password + ", active=" + active + ", role="
				+ role + "]";
	}

}//end class
