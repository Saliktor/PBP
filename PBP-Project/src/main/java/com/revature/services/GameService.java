package com.revature.services;

import java.util.Set;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.gamelogic.Square;
import com.revature.gamelogic.WorkingGame;

public interface GameService {
	int[][] getBoardState(Player player);
	
	void makeMove(Square move,  Player player);
	Set<Square> findValidMoves(Player player);
	
	//update, create, retrieve
	boolean updateGame(Game game);
	void createNewGame(Player player);
	Player createNewGame(UserAccount user);
	
	Player joinGameAsNewUser(UserAccount user, int gameID);
	
	
	Game getGame(Player player);
	

}
