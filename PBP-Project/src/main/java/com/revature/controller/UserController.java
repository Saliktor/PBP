package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.UserAccount;
import com.revature.services.UserService;

public class UserController {
	
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value= "/edituser")
	@ResponseBody
	public String editUser(String username, String password, String newEmail, HttpSession session) throws JsonProcessingException {
		UserAccount newAccount = uService.getUser(username, password);
		
		UserAccount currentUser = (UserAccount)session.getAttribute("currentUser");
		if (newAccount.getUsername().equals(currentUser.getUsername())
				&& newAccount.getPassword().equals(currentUser.getPassword())) {
			newAccount.setEmail(newEmail);
			return om.writeValueAsString(newAccount);
		}
		return null;
	}
}
