package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.util.HibernateUtil;

public class MessageDAOImp implements MessageDAO {
	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(MessageDAOImp.class);
	
	@Override
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

	@Override
	public Message getMessageByMessageId(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getLatestMessage() {
		Session session = hu.getSession();
		Message message  = null;
		try {
			DetachedCriteria maxTime = DetachedCriteria.forClass( Message.class );
			maxTime.setProjection(Projections.max("timePosted"));
			
			Criteria query = session.createCriteria( Message.class );
			query.add( Property.forName("timePosted").eq( maxTime ) );
			message = (Message) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

		return message;
	}

	
	
	
	
	@Override
	public List<Message> getGameMessages(Game game) {
		Session session = hu.getSession();
		List<Message> messages = null;
		try {
			
			messages = session.createCriteria( Message.class )
					.add(Restrictions.ge("game", game)).list();
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			return messages;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		Session session = hu.getSession();
		List<Message> messages = null;
		try {
			
			messages = session.createCriteria( Message.class )
					.add(Restrictions.eq("game", timestamp)).list();
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			return messages;
		} finally {
			session.close();
		}
		
	}



}
