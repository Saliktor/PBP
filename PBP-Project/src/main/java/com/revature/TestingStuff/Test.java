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
		
		
		UserAccount newUser = us.getUser("anaser91", "password");
		log.trace(newUser);
		
		
	}
}
