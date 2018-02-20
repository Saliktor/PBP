package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.UserAccount;
import com.revature.services.UserService;

@RestController
@CrossOrigin(origins= "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class LoginController {
	private ObjectMapper om = new ObjectMapper();
	
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService uService;
	
	public void setLogin(UserService uService) {
		this.uService = uService;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session) throws JsonProcessingException {
		UserAccount user = uService.getUser(username, password);
		UserAccount u = (UserAccount) session.getAttribute("user");
		
		log.trace(u);
		if(user == null) {
			return "null";
		} else {
			session.setAttribute("currentUser", user);
			session.setAttribute("user", user);
			log.trace(user);
			return om.writeValueAsString(user);
		}	
	}
	
	/* Call to server that returns the set of all players attached to the current user of the session */
	@RequestMapping(value="/login-getplayers", method=RequestMethod.GET)
	public String getPlayers( HttpSession session) throws JsonProcessingException {
		UserAccount user = (UserAccount)session.getAttribute("currentUser");
		if(user == null)
			return "null";
		return om.writeValueAsString(user.getPlayers());
	}
	
	
	/* A simple call to invalidate the session on server side
	 * Returns a null string cause nothing to be sent back
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws JsonProcessingException {
		session.invalidate();
		log.trace("Logged out");
		return "null";
	}
	
	
}

