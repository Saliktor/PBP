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
@RequestMapping(value="/login")
@CrossOrigin(origins= "http://localhost:4200")
public class LoginController {
	private ObjectMapper om = new ObjectMapper();
	
	
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
		if(user == null) {
			return "redirect:login";
		} else {
			session.setAttribute("user", user);
			return om.writeValueAsString(user);
		}
			
	}
}

