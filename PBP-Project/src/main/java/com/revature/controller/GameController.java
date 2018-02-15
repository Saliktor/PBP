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
import com.revature.beans.Player;
import com.revature.gamelogic.WorkingGame;
import com.revature.services.GameService;

@Controller
@RequestMapping(value="/game")
@CrossOrigin(origins= "*")
public class GameController {
	private ObjectMapper om = new ObjectMapper();

	@Autowired
	private GameService gameService;
	
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String sendValidMoves(HttpSession session) throws JsonProcessingException {
		Player player = (Player)session.getAttribute("player");
		return om.writeValueAsString(gameService.findValidMoves(player));
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public String recieveMove(int xid, int yid, int team, HttpSession session) throws JsonProcessingException {
		//Need to add player to session upon user first joining the game
		Player player = (Player)session.getAttribute("player");
		WorkingGame wg = gameService.makeMove(xid, yid, player);
		return om.writeValueAsString(wg.boardstate);
	}
}
