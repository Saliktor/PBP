package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
		if(user == null) {
			return "null";
		} else {
			session.setAttribute("currentUser", user);
			return om.writeValueAsString(user);
		}	
	}
	
	@RequestMapping(value="/login-getplayers", method=RequestMethod.GET)
	public String getPlayers( HttpSession session) throws JsonProcessingException {
		UserAccount user = (UserAccount)session.getAttribute("currentUser");
		return om.writeValueAsString(user.getPlayers());
	}
}

