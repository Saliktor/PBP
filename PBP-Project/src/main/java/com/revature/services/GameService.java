package com.revature.services;

import java.util.Set;

import com.revature.beans.Player;
import com.revature.gamelogic.Square;
import com.revature.gamelogic.WorkingGame;

public interface GameService {
	WorkingGame makeMove(int xid, int yid, Player player);
	Set<Square> findValidMoves(Player player);

}
