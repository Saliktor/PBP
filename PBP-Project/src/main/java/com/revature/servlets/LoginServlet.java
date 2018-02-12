package com.revature.servlets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.UserService;
import com.revature.TestingStuff.Test;

public class HelloServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private Test testClass = new Test();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		testClass.testCreateUser();
		resp.getWriter().write("Hello World");
	}
	
}
