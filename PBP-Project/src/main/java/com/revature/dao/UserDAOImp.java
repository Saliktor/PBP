package com.revature.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.TestingStuff.Test;
import com.revature.beans.UserAccount;
import com.revature.util.HibernateUtil;

public class UserDAOImp implements UserDAO {

	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	
	
	@Override
	public boolean createUser(UserAccount user) {
		Session session = hu.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			log.trace(session.save(user));
			tx.commit();
			return true;
		} catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				e.printStackTrace();
				return false;
			}
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteUser(int UserId) {
		
		return false;
	}

	@Override
	public UserAccount getUser(UserAccount user) {
		Session session = hu.getSession();
		UserAccount newUser = null;
		try {
			Criteria criteria = session.createCriteria(UserAccount.class);
			criteria.add(Restrictions.eq("username", user.getUsername()))
			.add(Restrictions.eq("password", user.getPassword()));
			
			newUser = (UserAccount) criteria.uniqueResult();
			
			
		} catch (Exception e) {
			log.trace(e);
		} finally {
			session.close();
		}
		
		return newUser;
	}
	
	
	
	
	
	
	
	

}
