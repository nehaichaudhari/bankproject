package com.repo;

import java.util.HashMap;

import com.pojo.User;

public class UserRepository {
	private static HashMap<Long, User> userMap = new HashMap<>();

	public static void addUser(long accNo, User user) {
		userMap.put(accNo, user);
	}

	public static User login(long accNo, String password) {
		if (userMap.containsKey(accNo)) {
			if (userMap.get(accNo).getPanCard().equals(password)) {
				return userMap.get(accNo);
			}
		}
		return null;
	}

}
