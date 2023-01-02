package com.sportyshoes.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.restapi.exception.UserNotFoundException;
import com.sportyshoes.restapi.model.MyUserDetails;
import com.sportyshoes.restapi.model.User;
import com.sportyshoes.restapi.repo.UserRepo;

@Service
@Transactional
public class UserService implements UserDetailsService {
	//Properties
	@Autowired
	private final UserRepo userRepo;
	
	//Constructor with Dependency Injection of the User Repository	
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
		
	//CRUD Methods	
	public User addUser(User user) {        
        return userRepo.save(user);
    }
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public User updateUser(User user, Long id) {
		if(user.getId() == id)
			return userRepo.save(user);
		return user;
	}		
	
	public User findUserById(Long id) {
		return userRepo.findUserById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
	}
	
	//must have @Transactional annotation for this to work properly
	public void deleteUser(Long id) {
		userRepo.deleteUserById(id);
	}
	
	//Methods to support use of UserDetails implementation of the User class	
	/* Configure a User Store for Authentication Purposes */
	/* Create our own implementation of UserDetailsService that allows for a user store database. */
	/* For Sporty Shoes, the data will ultimately reside in a relational database, so you could use JDBC authentication. 
	 * But it’d be even better to leverage the Spring Data JPA repository used to store users. */	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		System.out.println("inside loadUserByUserName()...");
		System.out.println("username = " + username);
		User user = userRepo.findByUsername(username); //get the user from the database
				
		if (user != null) {
			System.out.println("Found User: " + user);
			return new MyUserDetails(user);
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");				
	}

}//end class
