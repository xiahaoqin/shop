package com.chinasoft.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasoft.shop.service.OrdersService;

@Controller
public class OrderController {
	@Autowired
	private OrdersService os;
	@RequestMapping("orders.add/{gname}/{num}")
	public String add(){
		return null;
		
	}
}
