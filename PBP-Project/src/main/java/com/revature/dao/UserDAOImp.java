package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.beans.UserAccount;
import com.revature.game.util.HibernateUtil;

@Component
public class UserDAOImp implements UserDAO, HibernateSession {

	// private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(UserDAOImp.class);

	private Session session;

	@Override
	public void setSession(Session session) {
		this.session = session;

	}

	@Override
	public boolean createUser(UserAccount user) {
		// Session session = hu.getSession();
		session.save(user);
		return true;
	}

	@Override
	public UserAccount getUser(UserAccount user) {
		log.trace("USER ACCOUNT CALLED!");
		UserAccount newUser = null;

		Criteria criteria = session.createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("username", user.getUsername()))
				.add(Restrictions.eq("password", user.getPassword()));

		newUser = (UserAccount) criteria.uniqueResult();

		return newUser;
	}

	public boolean isEmailAvailable(String email) {
		UserAccount newUser = null;

		Criteria criteria = session.createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("email", email));

		newUser = (UserAccount) criteria.uniqueResult();
		if (newUser == null)
			return true;

		return false;
	}

	public boolean isUsernameAvailable(String username) {
		UserAccount newUser = null;
		log.trace(session);
		Criteria criteria = session.createCriteria(UserAccount.class);
		criteria.add(Restrictions.eq("username", username));

		newUser = (UserAccount) criteria.uniqueResult();
		if (newUser == null)
			return true;

		return false;
	}

	public boolean deleteUser(int userId) {

		return false;
	}

}
