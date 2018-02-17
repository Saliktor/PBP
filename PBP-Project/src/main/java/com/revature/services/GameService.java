package com.revature.services;


import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.Player;
//import com.revature.gamelogic.Square;
//import com.revature.gamelogic.WorkingGame;

public interface GameService {

	List<Message> getNewMessages(Game game, Timestamp timestamp);
	boolean saveMessage(Message message);
	
	int[][] getBoardState(Player player);
	
	//WorkingGame makeMove(int xid, int yid, Player player);
	//Set<Square> findValidMoves(Player player);
	//update, create, retrieve
	boolean updateGame(Game game);
	Game createNewGame(Player player);
	Game getGame(Player player);

}
