package com.revature.services;

import com.revature.beans.UserAccount;

public interface UserService {
	public UserAccount createUser(String username, String password, String email);
	public UserAccount createAdmin(String username, String password, String email);
	
	public UserAccount getUser(String username, String password);

}
