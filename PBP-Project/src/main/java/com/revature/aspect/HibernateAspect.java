package com.revature.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dao.HibernateSession;
import com.revature.game.util.HibernateUtil;

@Component
@Aspect
public class HibernateAspect {
	@Autowired
	private HibernateUtil hu;
	
	@Around("Daos()")
	public Object manageSession(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		Session session = hu.getSession();
		Transaction tx = session.beginTransaction();
		HibernateSession hs = (HibernateSession) pjp.getThis();
		hs.setSession(session);
		try {
			obj = pjp.proceed();
		} catch(Throwable e) {
			tx.rollback();
			if (obj instanceof Boolean)
			    obj = false;
			throw e;
		}
		
		tx.commit();
		session.close();
		hs.setSession(null);
		return obj;
	}
	
	@Pointcut("execution(* com.revature.dao..*(..)) "
			+ "&& !execution(* com.revature.dao..setSession(..))")
	public void Daos() {}
}