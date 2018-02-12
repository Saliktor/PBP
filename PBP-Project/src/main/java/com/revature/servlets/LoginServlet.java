package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.UserServiceImp;
import com.revature.services.UserServiceImp;

public class LoginServlet extends HttpServlet{

	private static final Logger log = Logger.getLogger(LoginServlet.class);
	private static final UserDAO uDAO = new UserDAOImp();
	private static final UserServiceImp uService = new UserServiceImp();
	private ObjectMapper om = new ObjectMapper();

	
	//private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		addCorsHeader(request.getRequestURI(), response);
		if("OPTIONS".equals(request.getMethod())) {
			return;
		}
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserAccount user = uService.getUser(username, password);
		

		
		UserAccount newUser = uService.getUser(username, password);

		if(newUser!= null) {
			session.setAttribute("loggedUser", user);
			response.setStatus(HttpServletResponse.SC_OK);
			String u = om.writeValueAsString(newUser);
			StringBuilder sb = new StringBuilder("{\"user\":");
			sb.append(u);
			sb.append("}");
			response.getWriter().write(sb.toString());
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No user found with those credentials");
		}

		
		
		
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