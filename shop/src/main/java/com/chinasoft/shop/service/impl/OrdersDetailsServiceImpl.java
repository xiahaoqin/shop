package com.chinasoft.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.shop.mapper.OrdersDetailsMapper;
import com.chinasoft.shop.pojo.OrdersDetails;
import com.chinasoft.shop.service.OrdersDetailsService;

public class OrdersDetailsServiceImpl implements OrdersDetailsService{
	@Autowired
	private OrdersDetailsMapper odm;
	@Override
	public void add(OrdersDetails od) {
		odm.add(od);
		
	}

}
