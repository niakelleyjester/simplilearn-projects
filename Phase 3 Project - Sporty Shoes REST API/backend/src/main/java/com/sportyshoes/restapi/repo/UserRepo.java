package com.sportyshoes.restapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.restapi.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	void deleteUserById(Long id);
	
	Optional<User> findUserById(Long id);	
	User findByUsername(String username);

}//end interface
