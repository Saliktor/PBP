package com.revature.dao;

import com.revature.beans.Game;
import com.revature.beans.Player;

public interface GameDAO {

	Game updateGame(Game game);
	Game createNewGame(Game game, Player player);
	Game getGame(Player player);
}
