package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.beans.Game;
import com.revature.beans.Message;

public interface MessageDAO {

	boolean saveMessage(Message message);
	Message getMessageByMessageId(Message message);
	Message getLatestMessage();
	List<Message> getGameMessages(Game game);
	List<Message> getNewMessages(Game game, Timestamp timestamp);
}
