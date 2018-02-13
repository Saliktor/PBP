package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
@CrossOrigin
public class HomeController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String goHome(HttpSession session) {
		return "<p>Welcome to Home</p>";
    }
}