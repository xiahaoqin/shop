package com.chinasoft.shop.service.impl;

import java.util.List;
import java.util.UUID;




import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.shop.mapper.AddressMapper;
import com.chinasoft.shop.pojo.Address;
import com.chinasoft.shop.service.AddressService;

public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressMapper addMapper;
	//添加地址
	public void addUser(Address address,String uid) {
		address.setId(UUID.randomUUID().toString().replace("-", ""));
		address.setUid(uid);
		String state = address.getState();
		if(state!=null){
			address.setState("1");
		}else{
			address.setState("0");
		}
		
		addMapper.addAdress(address);
	}
	//查找所有
	public List<Address> findAll() {
		return addMapper.findAll();
	}
	//查找单个
	public Address findOne(String id) {
		return addMapper.findOne(id);
	}
	//更新
	public void update(String id,Address address) {
		address.setId(id);
		String state = address.getState();
		if(state!=null){
			address.setState("1");
		}else{
			address.setState("0");
		}
		addMapper.update(address);
	}
	//删除
	public void delete(String id) {
		addMapper.delete(id);
	}
	//取消默认
	public void setsta(String id) {
		addMapper.setsta(id);
	}

}
