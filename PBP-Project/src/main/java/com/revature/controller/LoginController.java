package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.UserAccount;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;

@Controller
@RequestMapping(value="/login")
@CrossOrigin(origins= "http://localhost:4200")
public class LoginController {
	public static void main(String[] args) {
		UserService us = new UserServiceImp();
		
		UserAccount u = us.getUser("testuser", "password");
		//UserAccount u  = us.createUser("testuser", "password", "test@email.com");
		log.trace(u.toString());
		
	}
	private ObjectMapper om = new ObjectMapper();
	
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService uService;
	
	public void setLogin(UserService uService) {
		this.uService = uService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String goLogin(HttpSession session) {
		if(session.getAttribute("user")!=null)
			return "home";
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String login(String username, String password, HttpSession session) throws JsonProcessingException {
		System.out.println(username);
		System.out.println(password);

		UserAccount user = uService.getUser(username, password);
		System.out.println(user);
		if(user == null) {
			return om.writeValueAsString(null);
		} else {
			session.setAttribute("currentUser", user);
			return om.writeValueAsString(user);
		}
			
	}
}

