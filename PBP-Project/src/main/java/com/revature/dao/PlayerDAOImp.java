package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Player;
import com.revature.util.HibernateUtil;

public class PlayerDAOImp implements PlayerDAO {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	
	public Player getPlayer(int PlayerId) {
		Player player = null;
		Session session = hu.getSession();
		Transaction tx = null;
		
		try {
			tx = session.getTransaction();
			tx.begin();
			 player = (Player) session.get(Player.class, PlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
			
		}
		return player;
	}

}
