package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.beans.Game;
import com.revature.beans.Message;


@Repository
public class MessageDAOImp implements MessageDAO, HibernateSession {	
	private Session session;
	private static final String TIMEPOSTED = "timePosted";

	
	public void setSession(Session session) {
		this.session = session;
	}

	public boolean saveMessage(Message message) {
		session.save(message);
		return true;
	}

	public Message getMessageByMessageId(Message message) {
		return null;
	}

	public Message getLatestMessage() {
		Message message = null;
		DetachedCriteria maxTime = DetachedCriteria.forClass(Message.class);
		maxTime.setProjection(Projections.max(TIMEPOSTED));
		Criteria query = session.createCriteria(Message.class);
		query.add(Property.forName(TIMEPOSTED).eq(maxTime));
		message = (Message) query.uniqueResult();
		return message;
	}

	public List<Message> getGameMessages(Game game) {
		List<Message> messages = null;
		messages = session.createCriteria(Message.class).add(Restrictions.ge("game", game)).list();
		return messages;

	}

	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		List<Message> messages = null;
		messages = session.createCriteria(Message.class)
				.add(Restrictions.eq("game", game))
				.add(Restrictions.ge(TIMEPOSTED, timestamp)).list();
		return messages;

	}

}