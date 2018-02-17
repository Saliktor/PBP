package com.revature.services;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.dao.PlayerDAO;
import com.revature.dao.PlayerDAOImp;
import com.revature.dao.UserDAOImp;

@Component
public class UserServiceImp implements UserService {
	static final Logger log = Logger.getLogger(UserServiceImp.class);
	
	private static UserDAOImp userDAO = new UserDAOImp();
	private static PlayerDAO playerDAO = new PlayerDAOImp();
	
	/*
	 * Creates a user object based on passed parameters
	 * Returns newly created user object if successful
	 * Returns null otherwhys
	 */
	public UserAccount createUser(String username, String password, String email) {
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		
		if(!userDAO.isUsernameAvailable(username)) {
			log.error("Username is already taken");
			return null;
		}
		
		if(!userDAO.isEmailAvailable(email)) {
			log.error("Email is already being used by another user");
			return null;
		}
		
		userDAO.createUser(user);
		log.info("Successfully created new user");
		return user;

	}
	
	public UserAccount createAdmin(String username, String password, String email) {
		UserAccount user = createUser(username, password, email);
		user.setIsAdmin(1);
		return user;
	}
	
	public UserAccount getUser(String username, String password) {
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(password);
		user = userDAO.getUser(user);
		return user;
}

	@Override
	public Player getPlayer(int id) {
		
		Player player = playerDAO.getPlayer(id);
		return player;
	}
}
