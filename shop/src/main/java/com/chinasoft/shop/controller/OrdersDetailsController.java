package com.chinasoft.shop.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;








import com.chinasoft.shop.pojo.Address;
import com.chinasoft.shop.pojo.OrdersDetails;
import com.chinasoft.shop.service.AddressService;
import com.chinasoft.shop.service.GoodsService;
import com.chinasoft.shop.service.OrdersDetailsService;

@Controller
public class OrdersDetailsController {
	@Autowired
	private OrdersDetailsService ods;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("ordersDetails.add/{gname}/{num}")
	public String add(@PathVariable("gname") String gname,@PathVariable("num") int num,OrdersDetails od
			,Model model){
//		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/springmvc.xml");
//		OrdersDetails au = (OrdersDetails)ac.getBean("ordersDetails");
		od.setId(UUID.randomUUID().toString().replace("-", ""));
		od.setNums(num);
		od.setGid(goodsService.findIdByName(gname));
		od.setGname(gname);
		od.setGprice(goodsService.findPriceByName(gname));
		od.setSubtotal(od.getGprice()*num);
		ods.add(od);
		List<Address> listAdd = addressService.findAll();
		model.addAttribute("listAdd",listAdd);
		List<OrdersDetails> odlist = null;
		odlist.add(1, od);
		model.addAttribute("odlist", odlist);
		return "order_add";
	}
}
