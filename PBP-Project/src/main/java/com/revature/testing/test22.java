package com.revature.testing;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.beans.Game;
import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.game.util.HibernateUtil;
import com.revature.services.GameService;
import com.revature.services.GameServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;

@Component
public class test22 {
	//private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	//private static UserService us = new UserServiceImp();
	private static GameService gs = new GameServiceImp();
	private static  ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

	@Autowired
	private UserService us;
	
	public UserAccount getUser() {
		
		return us.getUser("user1", "password");
	}
	public static void main(String[] args) {

		test22 t = ac.getBean(test22.class);
		log.trace(t.getUser());
		

		
	}

}
