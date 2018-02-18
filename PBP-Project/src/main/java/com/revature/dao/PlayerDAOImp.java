package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.revature.beans.Player;
import com.revature.game.util.HibernateUtil;

@Component
public class PlayerDAOImp implements PlayerDAO, HibernateSession {

	private Session session;
	//private static HibernateUtil hu = HibernateUtil.getInstance();
	@Override
	public Player getPlayer(int PlayerId) {
		Player player = null;
		player = (Player) session.get(Player.class, PlayerId);
		return player;
	}
	
	@Override
	public void setSession(Session session) {
		this.session = session;
		
	}

}
