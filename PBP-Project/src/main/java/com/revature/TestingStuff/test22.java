package com.revature.TestingStuff;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.revature.beans.Game;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.services.GameService;
import com.revature.services.GameServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.util.HibernateUtil;

public class test22 {
	
	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Test.class);
	private static UserDAO uDAO = new UserDAOImp();
	private static UserService us = new UserServiceImp();
	private static GameService gs = new GameServiceImp();
	
	public static void main(String[] args) {
		
		long t = Long.parseLong("1518636785266");
		Date date = new Date(t);
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		//log.trace(format.format(date));
		//Timestamp timestamp = Timestamp.valueOf(format.format(date));
		Timestamp timestamp= new Timestamp(t);
		Game game = new Game();
		game.setId(1);
		log.trace(gs.getNewMessages(game, timestamp));
		log.trace(timestamp);

		
		
	}

}
