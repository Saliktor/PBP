package com.revature.dao;

import java.util.Set;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;

public interface GameDAO {
	boolean createNewGameAndPlayer(Player player);
	
	Game getGame(Player player);
	Game getGame (int id);
	Set<Player> getUserPlayersAndGames (UserAccount user);

	Game updateGame(Game game);
	boolean updateGame(Player player);
	
	Player joinGame(Game game);
	
}