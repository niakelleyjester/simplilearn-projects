package com.sportyshoes.restapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
	
	/* Spring Security offers several out-of-the-box implementations of UserDetailsService,
	 * including an in-memory user store, a JDBC user store, or an LDAP user store.
	 * You can also create your own implementation.
	 */	
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;	

}//end interface
