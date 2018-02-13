package com.revature.TestingStuff;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.UserAccount;
import com.revature.dao.MessageDAO;
import com.revature.dao.MessageDAOImp;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.util.HibernateUtil;

public class Test {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	private static MessageDAO mDAO = new MessageDAOImp();
	
	public static void main(String[] args) {
		
		UserAccount u = new UserAccount();
		u.setUsername("user");
		u.setPassword("password");
		
		
		UserAccount newUser = us.getUser("user1", "password");
		Game game  = new Game();
		game.setId(1);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//Message test
		Message message = new Message();
		message.setMessageContent("Hello!");
		message.setTimeMade(timestamp);
		message.setUser(newUser);
		message.setGame(game);
		
		log.trace(mDAO.saveMessage(message));
		
		//log.trace(newUser.toString());
		
	}
}
