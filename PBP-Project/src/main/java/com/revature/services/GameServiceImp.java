package com.revature.services;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.beans.Move;
import com.revature.beans.Player;
import com.revature.beans.Team;
import com.revature.dao.MessageDAO;
import com.revature.dao.MessageDAOImp;


@Component
public class GameServiceImp implements GameService {

	private static MessageDAO mDAO = new MessageDAOImp();
	private static boolean noMoreMoves = false;

	
	
	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		return mDAO.getNewMessages(game, timestamp);
	}

	public boolean saveMessage(Message message) {
		if (mDAO.saveMessage(message))
			return true;
		
		return false;
	}

	public int[][] getBoardState(Player player) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean updateGame(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

	public Game createNewGame(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	public Game getGame(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

		