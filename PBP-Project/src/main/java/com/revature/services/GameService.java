package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import com.revature.beans.Game;
import com.revature.beans.Message;

public interface GameService {

	List<Message> getNewMessages(Game game, Timestamp timestamp);
	boolean saveMessage(Message message);
}
