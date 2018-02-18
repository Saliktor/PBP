package com.revature.TestingStuff;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Game;
import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.game.services.GameService;
import com.revature.game.services.GameServiceImp;
import com.revature.game.services.UserService;
import com.revature.game.services.UserServiceImp;
import com.revature.game.util.HibernateUtil;

public class test22 {
	private static ApplicationContext ac;
	//private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	private static GameService gs = new GameServiceImp();
	
	public static void main(String[] args) {
	    ac = new ClassPathXmlApplicationContext("beans.xml");
		UserDAO uDAO = ac.getBean(UserDAO.class);
		UserAccount user = new UserAccount();
		user.setUsername("user1");
		user.setPassword("password");
		log.trace(uDAO.getUser(user));;

		
	}

}
