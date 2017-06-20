package com.chinasoft.shop.service;

import java.util.List;

import com.chinasoft.shop.pojo.Goods;

public interface GoodsService {

	void add(Goods goods);

	List<Goods> findAll();

	Goods check(String name);

	Goods findById(String id);

	void delete(String id);

	List<Goods> findByCid(String cid);

	void searchGoods();

	void edit(Goods goods, String id);

	String findIdByName(String gname);

	Double findPriceByName(String gname);
}
