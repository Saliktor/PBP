package com.revature.servlets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.UserService;

public class HelloServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		testCreatingUser();
		resp.getWriter().write("Hello World");
	}
	
	private void testCreatingUser(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter username: ");
		String username = reader.nextLine();
		System.out.println("Enter password: ");
		String password = reader.nextLine();
		System.out.println("Enter email: ");
		String email = reader.nextLine();
		
		userService.createUser(username, password, email);
		
		reader.close();
	}
}
