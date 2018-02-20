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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.game.util.HibernateUtil;

@Component
public class MessageDAOImp implements MessageDAO, HibernateSession {
	private static Logger log = Logger.getLogger(MessageDAOImp.class);
	private Session session;

	@Override
	public void setSession(Session session) {
		this.session = session;

	}

	@Override
	public boolean saveMessage(Message message) {
		session.save(message);
		return true;
	}

	@Override
	public Message getMessageByMessageId(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getLatestMessage() {
		Message message = null;
		DetachedCriteria maxTime = DetachedCriteria.forClass(Message.class);
		maxTime.setProjection(Projections.max("timePosted"));
		Criteria query = session.createCriteria(Message.class);
		query.add(Property.forName("timePosted").eq(maxTime));
		message = (Message) query.uniqueResult();
		return message;
	}

	@Override
	public List<Message> getGameMessages(Game game) {
		List<Message> messages = null;
		messages = session.createCriteria(Message.class).add(Restrictions.ge("game", game)).list();
		return messages;

	}

	@Override
	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		List<Message> messages = null;
		messages = session.createCriteria(Message.class).add(Restrictions.eq("game", game))
				.add(Restrictions.ge("timePosted", timestamp)).list();
		return messages;

	}

}
