package com.revature.controller;

import javax.servlet.http.HttpSession;

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
@RequestMapping(value="/register")
@CrossOrigin(origins= "*")
public class RegisterController {
	@Autowired
	private UserService uService;
	
	public void setLogin(UserService uService) {
		this.uService = uService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getRegister(HttpSession session) {
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String register(String username, String password, String email, ObjectMapper om, HttpSession session) throws JsonProcessingException {
		UserAccount user = uService.createUser(username, password, email);
		if(user == null) {
			return om.writeValueAsString("null");
		} else {
			session.setAttribute("user", user);
			return om.writeValueAsString(user);
		}
			
	}
}

