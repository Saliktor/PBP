package com.revature.services;

import com.revature.beans.UserAccount;

public interface UserService {

	UserAccount getUser(String username, String password);
}
