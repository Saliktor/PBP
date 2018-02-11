package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;

public class LoginServlet extends HttpServlet{

	public static Logger log = Logger.getLogger(LoginServlet.class);
	public static UserDAO uDAO = new UserDAOImp();
	public static UserService uService = new UserServiceImp();
	
	//private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		addCorsHeader(request.getRequestURI(), response);
		if("OPTIONS".equals(request.getMethod())) {
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserAccount user = uService.getUser(username, password);
		log.trace(user.toString());
		

		
		UserAccount newUser = uService.getUser(username, password);
		log.trace(newUser.toString());
		
		
		
	}
	
	
	private void addCorsHeader(String requestURI, HttpServletResponse response) {
		log.trace("adding headers");
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.addHeader("Vary", "Origin");
		//if I don't care about getting my cookie, this will work
		//response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.addHeader("Produces", "application/json");
	}
}
