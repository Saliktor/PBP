package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Message;
import com.revature.util.HibernateUtil;

public class MessageDAOImp implements MessageDAO {
	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(MessageDAOImp.class);
	
	public boolean saveMessage(Message message) {
		Session session = hu.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			log.trace(session.save(message));
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	public Message getMessageByMessageId(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	public Message getLatestMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
