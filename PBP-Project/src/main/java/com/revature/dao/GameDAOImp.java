package com.revature.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;

import com.revature.beans.Game;
import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.game.util.HibernateUtil;

public class GameDAOImp implements GameDAO, HibernateSession {
	private static ApplicationContext ac;
	private Session session;

	@Override
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

	@Override
	public boolean createNewGameAndPlayer(Player player) {
		session.save(player);
		return true;
	}

	@Override
	public boolean updateGame(Player player) {
		session.update(player);
		return true;
	}

	@Override
	public Player joinGame(Game game) {

		return null;
	}

	@Override
	public Set<Player> getUserPlayersAndGames(UserAccount user) {
		Set<Player> players = null;
		UserAccount thisUser = null;
		thisUser = (UserAccount) session.get(user.getClass(), user.getId());
		players = thisUser.getPlayers();
		return players;
	}

}
