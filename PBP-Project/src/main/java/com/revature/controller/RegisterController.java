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
@CrossOrigin(origins= "http://localhost:4200")
public class RegisterController {
	private ObjectMapper om = new ObjectMapper();
	
	
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
	public String register(String username, String password, String email, HttpSession session) throws JsonProcessingException {
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(uService);
		UserAccount user = uService.createUser(username, password, email);
		if(user == null) {
			return "redirect:home";
		} else {
			session.setAttribute("user", user);
			return om.writeValueAsString(user);
		}
			
	}
}

