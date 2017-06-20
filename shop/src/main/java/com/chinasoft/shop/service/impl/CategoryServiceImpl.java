package com.chinasoft.shop.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.shop.mapper.CategoryMapper;
import com.chinasoft.shop.pojo.Category;
import com.chinasoft.shop.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryMapper cgMapper;
	
	//查找所有分类
	public List<Category> findAll() {
		List<Category> cglist = cgMapper.findAll();
		return cglist;
	}

	//添加分类
	public void add(Category category) {
		category.setId(UUID.randomUUID().toString().replace("-", ""));
		cgMapper.add(category);
	}

	//根据名字查询分类
	public Category check(String name) {
		Category category = cgMapper.findByName(name);
		return category;
	}

	//查找单个分类
	public Category findById(String id) {
		Category category = cgMapper.findById(id);
		return category;
	}

	//更新
	public void update(Category category) {
		cgMapper.update(category);
		
	}

	//通过名字查找id
	public String findIdByName(String name) {
		return cgMapper.findIdByName(name);
	}


}
