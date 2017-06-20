package com.chinasoft.shop.mapper;

import java.util.List;

import com.chinasoft.shop.pojo.Address;

public interface AddressMapper {

	void addAdress(Address address);

	List<Address> findAll();

	Address findOne(String id);

	void update(Address address);

	void delete(String id);

	void setsta(String id);
}