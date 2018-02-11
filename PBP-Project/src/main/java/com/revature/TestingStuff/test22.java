package com.revature.TestingStuff;

import org.apache.log4j.Logger;

import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.util.HibernateUtil;

public class test22 {
	
	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	
	public static void main(String[] args) {
		
		UserAccount u = new UserAccount();
		u.setUsername("user");
		u.setPassword("password");
		
		UserAccount newUser = us.getUser("anaser91", "password");
		log.trace(newUser.toString());
		
	}

}
