package com.revature.dao;

import com.revature.beans.UserAccount;

public interface UserDAO {
	boolean createUser(UserAccount user);
	
	UserAccount getUser(UserAccount user);
	boolean isEmailAvailable(String email);
	boolean isUsernameAvailable(String email);
	
	
	
	boolean deleteUser(int userId);

}
