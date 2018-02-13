package com.revature.dao;

import com.revature.beans.Message;

public interface MessageDAO {

	boolean saveMessage(Message message);
	Message getMessageByMessageId(Message message);
	Message getLatestMessage();
}
