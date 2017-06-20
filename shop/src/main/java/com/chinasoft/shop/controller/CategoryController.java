package com.chinasoft.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chinasoft.shop.pojo.Category;
import com.chinasoft.shop.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService cgService;
	
	@RequestMapping("category.findAll")
	public ModelAndView findAll(){
		ModelAndView mav = new ModelAndView();
		List<Category> categories = cgService.findAll();
		mav.setViewName("admin/category_list");
		mav.addObject("categories", categories);
		return mav;
	}
	@RequestMapping("category.add")
	public String add(Category category){
		cgService.add(category);
		return "redirect:category.findAll";
	}
	@RequestMapping("category.check")
	public void check(@RequestParam String name,HttpServletResponse resp) throws IOException{
		Category category = cgService.check(name);
		if (category == null) {
			resp.getWriter().print("ok");
		} else {
			resp.getWriter().print("该分类已存在");
		}
	}
	//ajax使用
	@RequestMapping("category.findById/{id}")
	public String findById(@PathVariable("id") String id,Model model) {
		Category category = cgService.findById(id);
		model.addAttribute("category", category);
		return "admin/category_edit";
	}	
	
	@RequestMapping("category.edit/{id}")
	public String edit(@PathVariable("id") String id,@RequestParam String name,Model model) {
		Category category = cgService.findById(id);
		category.setName(name);
		cgService.update(category);
		model.addAttribute("editmsg", "修改成功");
		Category category1 = cgService.findById(id);
		model.addAttribute("category", category1);
		return "admin/category_edit";
	}	
	
	//
	@RequestMapping("category.findAll2")
	@ResponseBody
	public List<Category> findAll2(HttpServletResponse resp,HttpServletRequest request) throws IOException{
		List<Category> categories = cgService.findAll();
		return categories;
	}
}
