package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

	public Game createNewGame(Game game) {
		Transaction tx = null;
		Session session = hu.getSession();
		try {
			tx = session.beginTransaction();
			session.save(game);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}
		return game;
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
	
	public Game getGame(int gameID) {
		Game currentGame = null;
		Session session = hu.getSession();
		try {
			currentGame = (Game) session.get(Game.class, gameID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return currentGame;
	}

}
