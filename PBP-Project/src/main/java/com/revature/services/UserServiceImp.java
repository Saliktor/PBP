package com.revature.services;


import java.util.Set;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Service;


import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.dao.GameDAO;
import com.revature.dao.PlayerDAO;
import com.revature.dao.UserDAO;


@Service
public class UserServiceImp implements UserService {
	static final Logger log = Logger.getLogger(UserServiceImp.class);
	
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	private static PlayerDAO playerDAO = ac.getBean(PlayerDAO.class);
	private static GameDAO gameDAO = ac.getBean(GameDAO.class);
	@Autowired
	private UserDAO userDAO;
	
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
	
	
	/* Creates a new admin account. Not updated, do not use without testing */
	public UserAccount createAdmin(String username, String password, String email) {
		UserAccount user = createUser(username, password, email);
		user.setIsAdmin(1);
		return user;
	}
	
	
	/* Retrieve a user account from database with passed parameters */
	public UserAccount getUser(String username, String password) {
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(password);
		user = userDAO.getUser(user);
		
		if(user == null) {
			return null;
		}else {
			user.setPlayers(this.getPlayers(user));
		}
		
		return user;
	}
		

	/* Retrieve a player from database with passed player id */
	public Player getPlayer(int id) {
		Player player = playerDAO.getPlayer(id);
		return player;
	}
	
	
	/* Retrieve a set of players attached to a user account*/
	public Set<Player> getPlayers(UserAccount user){
		Set<Player> players = gameDAO.getUserPlayersAndGames(user);
		return players;
	}
	
	
	/* Update the user account to the database */
	public UserAccount editUser(UserAccount user) {
		return userDAO.updateUser(user);
	}
}
