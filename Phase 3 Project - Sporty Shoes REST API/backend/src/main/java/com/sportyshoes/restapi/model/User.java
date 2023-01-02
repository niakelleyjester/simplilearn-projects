package com.sportyshoes.restapi.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Entity //One User to Many Orders
@Table(name="users")
public class User implements Serializable{
	
	//Properties
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="username", nullable=false)
	private String username; //primary key; needed for UserDetails
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid", nullable=false)    
	private Long id;
	
	private String roleType; 
	
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;	
	
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="phoneNumber")
	private String phoneNumber;
	@Column(nullable=false)
	private boolean enabled;	
	@Column(name="dateCreated")
	private Timestamp dateCreated;
	
	//Default Constructor
	public User() {}
	
	//Parameterized Constructor
	public User(String username, String roleType, String firstName, String lastName, String email, String password,
			String phoneNumber, boolean enabled, Timestamp dateCreated) {		
		this.username = username;
		this.roleType = roleType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.dateCreated = dateCreated;
	}

	//Getters & Setters	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	//Other Methods	
	/* Implementations of UserDetails will provide some essential user information to the framework, 
	 * such as what authorities are granted to the user and whether the user’s account is enabled. */
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// should return a collection of authorities granted to the User
//		// for Sporty Shoes, return a collection indicating that Users will have been granted ROLE_USER authority
//		return Arrays.asList(new SimpleGrantedAuthority(roleType));
//	}

	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", roleType=" + roleType + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", enabled=" + enabled + ", dateCreated=" + dateCreated + "]";
	}

//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}

//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	public boolean isActive() {
//		// TODO Auto-generated method stub
//		return true;
//	}
	

}//end class
