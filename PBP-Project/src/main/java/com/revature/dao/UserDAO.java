package com.revature.dao;

import com.revature.beans.UserAccount;

public interface UserDAO {
	boolean createUser(UserAccount user);
	boolean login(String username, String password);
	UserAccount getUser(UserAccount user);
	boolean deleteUser(int userId);

}
