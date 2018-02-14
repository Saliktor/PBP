package com.revature.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.UserAccount;
import com.revature.services.GameService;
import com.revature.services.UserService;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class MessageController {
	private ObjectMapper om = new ObjectMapper();

	
	@Autowired
	private GameService gService;
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value="message/newmessage")
	public void saveMessage(String messageContent, String timeStamp,
			HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		
		Message newMessage = new Message();
		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		Timestamp timestamp = toTimestamp(timeStamp);
		//Timestamp timestamp = Timestamp.valueOf(format.format(date));
		//Game currentGame = (Game) session.getAttribute("currentGame");
		UserAccount testUser = uService.getUser("user1", "password");
		Game testGame = new Game();
		testGame.setId(1);
		//UserAccount currentUser = (UserAccount) session.getAttribute("currentUser");
		Timestamp timeMade = new Timestamp(System.currentTimeMillis());
		newMessage.setGame(testGame);
		newMessage.setUser(testUser);
		newMessage.setMessageContent(messageContent);
		newMessage.setTimeMade(timeMade);
		
		if (gService.saveMessage(newMessage)){
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
					
	}
	
	@RequestMapping(value="/message/getnewmessages")
	@ResponseBody
	public String getNewMessages(String timeStamp, 
			HttpSession session, HttpServletRequest request) throws JsonProcessingException {
		System.out.println(timeStamp + " ===================================================================");
		Timestamp timestamp = toTimestamp(timeStamp);
		Timestamp t3 = new Timestamp(Long.parseLong(timeStamp));
		Timestamp t2 = new Timestamp(System.currentTimeMillis());
		//Game currentGame = (Game) session.getAttribute("currentGame");
		Game currentGame = new Game();
		currentGame.setId(1);
		List<Message> newMessages = gService.getNewMessages(currentGame, t3);
		
		return om.writeValueAsString(newMessages);
	}
	
	private Timestamp toTimestamp(String s) {
		long t = Long.parseLong(s);
		Timestamp timestamp = new Timestamp(t);
		return timestamp;
	}
	

}
