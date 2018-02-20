package com.revature.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.revature.beans.Player;

@Component
public class PlayerDAOImp implements PlayerDAO, HibernateSession {

	private Session session;
	
	public Player getPlayer(int PlayerId) {
		Player player = null;
		player = (Player) session.get(Player.class, PlayerId);
		return player;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
}