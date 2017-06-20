package com.chinasoft.shop.mapper;

import java.util.List;

import com.chinasoft.shop.pojo.Goods;

public interface GoodsMapper {

	List<Goods> findAll();

	void add(Goods goods);

	Goods findByName(String name);

	Goods findById(String id);

	void delete(String id);

	List<Goods> findByCid(String cid);

	void edit(Goods goods);

	String findIdByName(String name);

	Double findPriceByName(String name);
}