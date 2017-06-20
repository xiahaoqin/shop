package com.chinasoft.shop.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinasoft.shop.pojo.Address;
import com.chinasoft.shop.pojo.User;
import com.chinasoft.shop.service.AddressService;

@Controller
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	// 添加地址
		@RequestMapping(value = "addAddress", method = RequestMethod.POST)
		public String add(Address address,HttpSession session,Model model) {
			User sessUser = (User)session.getAttribute("user");
			addressService.addUser(address,sessUser.getId());
			model.addAttribute("subAdd", "addAddress");//动态控制
			return "redirect:address.findAll";
		}
		// 查找所有
		@RequestMapping("address.findAll")
		public String findAll(Model model) {
				List<Address> listAdd = addressService.findAll();
				model.addAttribute("listAdd",listAdd);
				model.addAttribute("subAdd", "addAddress");//动态控制提交/更新
				return "usercenter/address_list";
		}
		// 查找单个地址
		@RequestMapping("address.findOne/{id}")
		public String findOne(@PathVariable("id") String id,Model model) {
				Address address = addressService.findOne(id);
				List<Address> listAdd = addressService.findAll();
				model.addAttribute("address",address);
				model.addAttribute("listAdd",listAdd);
				model.addAttribute("subAdd", "address.edit/"+id);//动态控制
				return "usercenter/address_list";
			}	
		// 修改
		@RequestMapping("address.edit/{id}")
		public String edit(@PathVariable("id") String id,Address address,Model model) {
				addressService.update(id,address);
				List<Address> listAdd = addressService.findAll();
				model.addAttribute("listAdd",listAdd);
				return "usercenter/address_list";
			}
		// 删除
		@RequestMapping("address.delete/{id}")
		public String delete(@PathVariable("id") String id,Model model) {
				addressService.delete(id);
				List<Address> listAdd = addressService.findAll();
				model.addAttribute("listAdd",listAdd);
				model.addAttribute("subAdd", "addAddress");//动态控制
				return "usercenter/address_list";
			}
		// 取消默认
		@RequestMapping("address.setsta/{id}")
		public String setsta(@PathVariable("id") String id,Model model) {
				addressService.setsta(id);
				List<Address> listAdd = addressService.findAll();
				model.addAttribute("listAdd",listAdd);
				return "usercenter/address_list";
			}		
}
