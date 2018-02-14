package com.revature.TestingStuff;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.UserAccount;
import com.revature.dao.MessageDAO;
import com.revature.dao.MessageDAOImp;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.GameService;
import com.revature.services.GameServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.util.HibernateUtil;

public class Test {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	private static MessageDAO mDAO = new MessageDAOImp();
	private static GameService gService = new GameServiceImp();
	
	//Just testing stuff
	public static void main(String[] args) throws ParseException {
		
		UserAccount u = new UserAccount();
		u.setUsername("user");
		u.setPassword("password");
		
		
		UserAccount newUser = us.getUser("user1", "password");
		Game game  = new Game();
		game.setId(1);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = dateFormat.parse("30/1/2007");
		long d = date.getDate();
		Timestamp t = new Timestamp(d);
		//Message test
		Message message = new Message();
		message.setMessageContent("what's up");
		message.setTimeMade(timestamp);
		message.setUser(newUser);
		message.setGame(game);
		log.trace(t);
		List<Message> messages = gService.getNewMessages(game, t);
		//log.trace(mDAO.saveMessage(message));
		log.trace(messages.toString());
		// Message message2 = mDAO.getLatestMessage();
		// log.trace(message2.toString());
		//log.trace(newUser.toString());
		
	}
}
