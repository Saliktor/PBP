package com.revature.TestingStuff;

import java.text.ParseException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.beans.Player;
import com.revature.beans.UserAccount;
import com.revature.dao.MessageDAO;
import com.revature.dao.MessageDAOImp;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.GameService;
import com.revature.services.GameServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.util.HibernateUtil;

public class Test {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	private static MessageDAO mDAO = new MessageDAOImp();
	private static GameService gService = new GameServiceImp();
	
	//Just testing stuff
	public static void main(String[] args) throws ParseException {
		Session session = hu.getSession();
		UserAccount user = us.getUser("mrBitch", "password");
		log.trace(user);
		//Team team = new Team();
		//team.setId(1);
		//team.setTeamName("Black");
//		Player player = (Player) session.get(Player.class, 3);
//		Player player2 = (Player) session.get(Player.class, 1);
//		Player player3 = new Player();
//		player3.setUser(user);
//		Game game = (Game) session.get(Game.class, 3);
		
		
		//player3.setGame(player.getGame());
		Set<Player> players = gService.getUserPlayers(user);
		log.trace(players);
		//Transaction tx = session.getTransaction();
	//	tx.begin();
		// session.save(player3);
		// tx.commit();
//		player.setGame(game);
//		player.setTeam(team);
//		player.setUser(user);
//		user.addPlayer(player);
//		
		
//		
//		Transaction tx = session.getTransaction();
//		
//		tx = session.beginTransaction();
//		session.merge(player);
//		tx.commit();
		session.close();
		
		
		
		
		
	}
}
