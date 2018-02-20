package com.revature.services;

import java.util.Set;

import com.revature.beans.Game;
import java.sql.Timestamp;
import java.util.List;

import com.revature.beans.Message;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.gamelogic.Square;

public interface GameService {
	//Service to DAO methods
	void createNewGame(Player player);
	Player createNewGame(UserAccount user);
	
	List<Message> getNewMessages(Game game, Timestamp timestamp);
	Set<Player> getUserPlayers (UserAccount user);
	Game getGame(Player player);
	
	Game updateGame(Player player);
	boolean updateGame(Game game);
	
	
	//Game Logic
	int[][] getBoardState(Player player);	
	void makeMove(Square move,  Player player);
	Set<Square> findValidMoves(Player player);
	
	//Service specific functionaliity
	Player joinGameAsNewUser(UserAccount user, int gameID);
	
	boolean saveMessage(Message message);
	



	
}
