package com.chinasoft.shop.mapper;

import java.util.List;

import com.chinasoft.shop.pojo.Category;

public interface CategoryMapper {

	List<Category> findAll();

	void add(Category category);

	Category findByName(String name);

	Category findById(String id);

	void update(Category category);

	String findIdByName(String name);
}