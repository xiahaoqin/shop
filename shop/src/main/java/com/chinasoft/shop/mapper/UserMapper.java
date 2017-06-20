package com.chinasoft.shop.mapper;

import com.chinasoft.shop.pojo.User;

public interface UserMapper {

	void addUser(User user);

	User findById(String token);

	void update(User user);

	void delete(String id);

	User findUserByName(String name);
}