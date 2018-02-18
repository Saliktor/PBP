package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.gamelogic.WorkingGame;
import com.revature.services.GameService;

@RestController
@CrossOrigin(origins= "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class GameController {
	private ObjectMapper om = new ObjectMapper();

	@Autowired
	private GameService gameService;
	
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
	
	/* POST call that takes the passed player object and properly formats and persist the
	 * Player and Game beans. Return player after formatting to angular so it can update its
	 * copy of player and game with appropriate id's
	 */
	@RequestMapping(value="/game-new", method=RequestMethod.POST)
	public String createNewGame(@RequestBody Player player, ObjectMapper om, HttpSession session) throws JsonProcessingException {
		session.setAttribute("player", player);
		gameService.createNewGame(player);
		
		System.out.println("Player: " + session.getAttribute("player"));
		return om.writeValueAsString(player);
	}
	
	
	/* GET call that takes the passed player object and properly formats and persist the
	 * Player and Game beans. Return player after formatting to angular so it can update its
	 * copy of player and game with appropriate id's
	 */
	@RequestMapping(value="/game-new", method=RequestMethod.GET)
	public String createNewGame(ObjectMapper om, HttpSession session) throws JsonProcessingException {
		UserAccount user = (UserAccount)session.getAttribute("currentUser");
		Player player = gameService.createNewGame(user);

		//Store the player as an attribute in the session for quick access in the future
		session.setAttribute("player", player);
		
		return om.writeValueAsString(player);
	}
	
	
	
	/* GET call to retrieve the WorkingGame for current player */
	@RequestMapping(value="/game-workinggame", method=RequestMethod.POST)
	@ResponseBody
	public String getWorkingGame(@RequestBody Player player, ObjectMapper om, HttpSession session) throws JsonProcessingException {
		Game game = player.getGame();
		WorkingGame wgame = new WorkingGame(game);
		wgame.team = player.getTeam();
		return om.writeValueAsString(wgame);
	}
	
	
	@RequestMapping(value="/game-boardstate", method=RequestMethod.GET)
	@ResponseBody
	public String getBoardState(HttpSession session) throws JsonProcessingException {
		Player player = (Player)session.getAttribute("player");
		return om.writeValueAsString(gameService.getBoardState(player));
	}
	
	
	//This needs to be changed to a post and the parameter should take in a game
	@RequestMapping(value="/game-player", method=RequestMethod.POST)
	@ResponseBody
	public String playerSignIn(@RequestBody Player player , HttpSession session) throws JsonProcessingException {
		System.out.println("Player Sign In");
		System.out.println(player.toString());
		return null;
	}
	
	
	@RequestMapping(value="/game", method=RequestMethod.GET)
	@ResponseBody
	public String sendValidMoves(HttpSession session) throws JsonProcessingException {
		Player player = (Player)session.getAttribute("player");
		return om.writeValueAsString(gameService.findValidMoves(player));
	}
	
	
	@RequestMapping(value="/game", method=RequestMethod.POST)
	@ResponseBody
	public String recieveMove(int xid, int yid, int team, HttpSession session) throws JsonProcessingException {
		//Need to add player to session upon user first joining the game
		Player player = (Player)session.getAttribute("player");
		WorkingGame wg = gameService.makeMove(xid, yid, player);
		return om.writeValueAsString(wg.boardstate);
	}
}
