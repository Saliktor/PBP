package com.revature.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

	private static  ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

	public static ApplicationContext getAc() {
		return ac;
	}

}
