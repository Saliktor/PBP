package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/home")
@CrossOrigin
public class HomeController {
	
	@RequestMapping(method=RequestMethod.GET,  produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String goHome(HttpSession session) {
		return "Welcome to Home";
    }
}