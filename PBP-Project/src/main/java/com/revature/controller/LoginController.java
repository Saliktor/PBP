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

@Controller
@RequestMapping(value="/login")
@CrossOrigin(origins= "*")
public class LoginController {
	private ObjectMapper om = new ObjectMapper();
	
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService uService;
	
	public void setLogin(UserService uService) {
		this.uService = uService;
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String login(String username, String password, HttpSession session) throws JsonProcessingException {
		UserAccount user = uService.getUser(username, password);
		System.out.println(user);
		if(user == null) {
			return "null";
		} else {
			session.setAttribute("user", user);
			return om.writeValueAsString(user);
		}	
	}
}

