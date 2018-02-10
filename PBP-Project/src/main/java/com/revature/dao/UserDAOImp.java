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
	public boolean login(String username, String password) {
		
		Session session = hu.getSession();

		try {
			Criteria criteria = session.createCriteria(UserAccount.class);
			criteria.add(Restrictions.eq("username", username));
			UserAccount user = (UserAccount) criteria.uniqueResult();
			
			if(password.equals(user.getPassword()))
				return true;
			
		} catch (Exception e) {
			log.trace(e);
			return false;
		} finally {
			session.close();
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(int UserId) {
		
		return false;
	}

}
