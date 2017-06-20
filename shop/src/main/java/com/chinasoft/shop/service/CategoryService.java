package com.chinasoft.shop.service;

import java.util.List;

import com.chinasoft.shop.pojo.Category;

public interface CategoryService {

	List<Category> findAll();

	void add(Category category);

	Category check(String name);

	Category findById(String id);

	void update(Category category);

	String findIdByName(String categoryName);

}
