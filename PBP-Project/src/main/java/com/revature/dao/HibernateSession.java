package com.revature.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

public interface HibernateSession {
	public void setSession(Session session);
}
