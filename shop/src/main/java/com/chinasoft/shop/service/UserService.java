package com.chinasoft.shop.service;

import com.chinasoft.shop.pojo.User;

public interface UserService {
	void addUser(User user);
	boolean checkveri(String veri,String code);
	void activate(String token) throws UserException;
	void login(User user) throws UserException;
	User findUserByName(String name);
	User findById(String id);
}
