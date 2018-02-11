package com.revature.services;

import com.revature.beans.UserAccount;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;

public class UserServiceImp implements UserService {
	
	private static UserDAO uDAO = new UserDAOImp();
	public UserAccount getUser(String username, String password) {
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(password);
		user = uDAO.getUser(user);
		return user;
	}

}
