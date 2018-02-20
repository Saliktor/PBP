package com.revature.dao;

import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;

@Repository
public class GameDAOImp implements GameDAO, HibernateSession {
	
	private Session session;
	public void setSession(Session session) {
		this.session = session;
	}

	public Game updateGame(Game game) {
		Game updatedGame = null;
		updatedGame = (Game) session.merge(game);
		return updatedGame;
	}

	public Game createNewGame(Game game) {
		Game createdGame = null;
		session.save(game);
		createdGame = game;
		return createdGame;
	}

	public Game getGame(Player player) {
		Player currentPlayer = null;
		currentPlayer = (Player) session.get(Player.class, player.getId());
		return currentPlayer.getGame();
	}

	public Game getGame(int gameID) {
		Game currentGame = null;
		currentGame = (Game) session.get(Game.class, gameID);
		return currentGame;
	}

	public boolean createNewGameAndPlayer(Player player) {
		session.save(player);
		return true;
	}

	public boolean updateGame(Player player) {
		session.update(player);
		return true;
	}

	public Player joinGame(Game game) {
		return null;
	}

	public Set<Player> getUserPlayersAndGames(UserAccount user) {
		Set<Player> players = null;
		UserAccount thisUser = null;
		thisUser = (UserAccount) session.get(user.getClass(), user.getId());
		players = thisUser.getPlayers();
		return players;
	}

}