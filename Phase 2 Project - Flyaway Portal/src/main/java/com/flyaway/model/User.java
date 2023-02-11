package com.flyaway.model;

import java.io.Serializable;

//Bean Class
public class User implements Serializable {	
	//Properties
	private static final long serialVersionUID = 1L;	
	private int userid;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private String phoneNumber;
	private int roleid;
	private String role;
		
	//Parameterized Constructors
	public User(String firstName, String lastName, String email, String password) {	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;		
	}
	
	public User(String firstName, String lastName, String email, String phoneNumber, String role, String password) {	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String userName, String email, String password, String phoneNumber, String role) {	
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		setRoleid(role);
	}
	
	public User(int userid, String firstName, String lastName, String userName, String email, String password,
			String phoneNumber) {
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public User(int id, String firstName, String lastName, String email, String phoneNumber, String password) {	
		this.userid = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public User(int id, String firstName, String lastName, String username, String email, String phoneNumber, int roleid, String role) {	
		this.userid = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.roleid = roleid;
		this.role = role;
	}
	
	
	public User(int id, String firstName, String lastName, String username, String email, String phoneNumber, int roleid) {	
		this.userid = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.roleid = roleid;	
	}	
	
	public User(int userid, String firstName, String lastName, String userName, String email, String password,
			String phoneNumber, int roleid, String role) {
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roleid = roleid;
		this.role = role;
	}
	
	//Default constructor
	public User() {	}
	
	//Generate getters & setters
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}	

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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid() {
		switch(this.role) {
		case "Customer":
			this.roleid = 2;
			break;
			
		case "Admin":
			this.roleid = 1;
			break;
			
		default:
			this.roleid = 3; //guest
			break;		
		}//end switch
	}
	
	public void setRoleid(int rid) {
		this.roleid = rid;
	}
	
	public void setRoleid(String role) {
		switch(role) {
		case "Customer":
			this.roleid = 2;
			break;
			
		case "Admin":
			this.roleid = 1;
			break;
			
		default:
			this.roleid = 3; //guest
			break;		
		}//end switch
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//Other methods
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", roleid=" + roleid + ", role=" + role + "]";
	}	
	
}//end class
