package com.revature.dao;

import org.hibernate.Session;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.util.HibernateUtil;

public class GameDAOImp implements GameDAO {
	
	private static HibernateUtil hu = HibernateUtil.getInstance();

	public Game updateGame(Game game) {
		Game updatedGame = null;
		Session session = hu.getSession();
		try {
			updatedGame = (Game) session.merge(game);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return updatedGame;
	}

	public Game createNewGame(Game game, Player player) {
		Game newGame = null;
		Session session = hu.getSession();
		try {
			newGame = (Game) session.save(game);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newGame;
	}

	public Game getGame(Player player) {
		Player currentPlayer = null;
		Session session = hu.getSession();
		try {
			currentPlayer = (Player) session.get(Player.class, player.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return currentPlayer.getGame();
	}

}
