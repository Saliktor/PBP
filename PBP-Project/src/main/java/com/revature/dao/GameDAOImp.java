package com.revature.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;
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
		} finally {
			session.close();
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
		} finally {
			session.close();
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
		} finally {
			session.close();
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
		} finally {
			session.close();
		}
		return currentGame;
	}


	public boolean createNewGameAndPlayer(Player player) {
		Session session = hu.getSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.save(player);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}finally {
			session.close();
		}
		return true;
	}


	public boolean updateGame(Player player) {
		Session session = hu.getSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.update(player);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}finally {
			session.close();
		}
		return true;
	}


	public Player joinGame(Game game) {
		
		return null;
	}


	public Set<Player> getUserPlayersAndGames(UserAccount user) {
		Set<Player> players = null;
		UserAccount thisUser = null;
		Session session = hu.getSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			thisUser = (UserAccount) session.get(user.getClass(), user.getId());
			players = thisUser.getPlayers();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally {
			session.close();
		}
		return players;
	}
	

}