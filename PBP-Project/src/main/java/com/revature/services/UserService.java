package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.beans.User;

public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);
	
	/*
	 * Creates a user object based on passed parameters
	 * Returns newly created user object if successful
	 * Returns null otherwhys
	 */
	public User createUser(String username, String password, String email) {
		User user = new User();
		
		if(isUserNameTaken(username))
			return null;
		else 
			user.setUsername(username);
		
		if(isEmailUsed(email))
			return null;
		else
			user.setEmail(email);
		
		user.setPassword(password);
		user.setEmail(email);
		
		//UserDao.createUser(User user);
		log.info("Successfully created new user");
		return user;
	}
	
	
	/*
	 * Returns a boolean determining username is already in use
	 */
	public boolean isUserNameTaken(String username) {
		/*
		 * User user =  UserDao.getUserByUsername(String username);
		 * if(user != null){
		 *   log.info(username + " is already used");
		 * 	 return true;
		 * }else
		 *   return false;
		 */
		
		return false;
	}
	
	/*
	 * Returns a boolean determining if the email is already in use
	 */
	public boolean isEmailUsed(String username) {
		/*
		 * User user = UserDao.getUserByEmail(String email);
		 * if(user != null){
		 *   log.info(email + " is already tied to a user");
		 *   return true;
		 * }else
		 *   return false;
		 */
		
		return false;
	}
	
	
	public User createAdmin(String username, String password, String email) {
		User user = createUser(username, password, email);
		user.setAdmin(true);
		return user;
	}
}
