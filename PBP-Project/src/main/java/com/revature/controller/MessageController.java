package com.revature.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.UserAccount;
import com.revature.services.GameService;
import com.revature.services.UserService;

@Controller
@CrossOrigin(origins= "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class MessageController {
	private static Logger log = Logger.getLogger(MessageController.class);
	private ObjectMapper om = new ObjectMapper();

	
	@Autowired
	private GameService gService;
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value="/message/newmessage", method=RequestMethod.POST)
	public void saveMessage(String messageContent, String timeStamp,
			HttpSession session, HttpServletResponse response, HttpServletRequest request) /*throws JsonProcessingException*/ {
		
		Message newMessage = new Message();
		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		Timestamp timestamp = toTimestamp(timeStamp);
		//Timestamp timestamp = Timestamp.valueOf(format.format(date));
		//Game currentGame = (Game) session.getAttribute("currentGame");
		UserAccount testUser = uService.getUser("user1", "password");
		Game testGame = new Game();
		//was set to 1 set to 121 cause it is in the database
		testGame.setId(121);
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
	
	/*@RequestMapping(value="/message/getnewmessages")
    public String getNewMessages(@RequestParam(value="timeStamp", required=false, defaultValue="1519067509050") String timeStamp, HttpSession session, HttpServletRequest request) throws JsonProcessingException {
        log.debug("The timestamp is: "+timeStamp);
        log.trace(request.getParameter("timeStamp"));
        Timestamp timestamp = toTimestamp(timeStamp);
        //Game currentGame = (Game) session.getAttribute("currentGame");
        Game currentGame = new Game();
        //matches above in save mes parameters
        currentGame.setId(121);
        List<Message> newMessages = gService.getNewMessages(currentGame, timestamp);
        return "YoDawg";
    }*/
	
	@RequestMapping(value="/message/getnewmessages", method=RequestMethod.GET)
    @ResponseBody
    public String getNewMessages() {
        try {
            String timeStamp = "1519067509050";
            log.debug("The timestamp is: "+timeStamp);
         //   log.trace(request.getParameter("timeStamp"));
            Timestamp timestamp = toTimestamp(timeStamp);
            //Game currentGame = (Game) session.getAttribute("currentGame");
            Game currentGame = new Game();
            //matches above in save mes parameters
            currentGame.setId(121);
            List<Message> newMessages = gService.getNewMessages(currentGame, timestamp);

            return om.writeValueAsString(newMessages);
        }
        catch (Exception ex) {
            return ex.toString();
        }

    }
	
	
	private Timestamp toTimestamp(String s) {
		long t = Long.parseLong(s);
		Timestamp timestamp = new Timestamp(t);
		return timestamp;
	}
	


}