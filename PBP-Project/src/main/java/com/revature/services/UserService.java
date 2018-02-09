package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.User;

public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);
	
	public User createUser(String firstname, String lastname, String username, String password, String email) {
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		return user;
	}
	
	
	public User createAdmin(String firstname, String lastname, String username, String password, String email) {
		User user = createUser(firstname, lastname, username, password, email);
		user.setAdmin(true);
		return user;
	}
}
