package com.revature.dao;

import java.util.Set;

import com.revature.beans.Game;
import com.revature.beans.Message;

public interface MessageDAO {

	boolean saveMessage(Message message);
	Message getMessageByMessageId(Message message);
	Message getLatestMessage();
	Set<Message> getGameMessages(Game game);

}
