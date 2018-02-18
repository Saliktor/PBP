package com.revature.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.beans.Player;
import com.revature.beans.UserAccount;

public interface UserService {

	public UserAccount createUser(String username, String password, String email);
	public UserAccount createAdmin(String username, String password, String email);
	
	public UserAccount getUser(String username, String password);
	public Player getPlayer(int id);
	
}
