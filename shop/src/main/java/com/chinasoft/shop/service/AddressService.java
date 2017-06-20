package com.chinasoft.shop.service;

import java.util.List;

import com.chinasoft.shop.pojo.Address;

public interface AddressService {

	void addUser(Address address, String uid);

	List<Address> findAll();

	Address findOne(String id);

	void update(String id, Address address);

	void delete(String id);

	void setsta(String id);
	
}
