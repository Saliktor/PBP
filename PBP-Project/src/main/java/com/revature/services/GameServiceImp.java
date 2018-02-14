package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.beans.Game;
import com.revature.beans.Message;
import com.revature.dao.MessageDAO;
import com.revature.dao.MessageDAOImp;

@Component
public class GameServiceImp implements GameService {

	private static MessageDAO mDAO = new MessageDAOImp();
	@Override
	public List<Message> getNewMessages(Game game, Timestamp timestamp) {
		return mDAO.getNewMessages(game, timestamp);
	}

	@Override
	public boolean saveMessage(Message message) {
		if (mDAO.saveMessage(message))
			return true;
		
		return false;
	}

}
