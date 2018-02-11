package com.revature.TestingStuff;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

public class Test {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService userService = new UserService();
	private static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {

		
	}
	
	public void testCreateUser() {
		System.out.println("Enter username: ");
		String username = reader.nextLine();
		System.out.println("Enter password: ");
		String password = reader.nextLine();
		System.out.println("Enter email: ");
		String email = reader.nextLine();
		
		userService.createUser(username, password, email);

	}

}
