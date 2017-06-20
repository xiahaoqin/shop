package com.chinasoft.shop.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.shop.mapper.GoodsMapper;
import com.chinasoft.shop.pojo.Goods;
import com.chinasoft.shop.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsMapper goodsMapper;
	@Override
	public void add(Goods goods) {
		goodsMapper.add(goods);
	}
	@Override
	public void edit(Goods goods,String id) {
		goods.setId(id);
		goodsMapper.edit(goods);
		
	}
	@Override
	public List<Goods> findAll() {
		List<Goods> listgoods = goodsMapper.findAll();
		return listgoods;
	}

	@Override
	public Goods check(String name) {
		Goods goods = goodsMapper.findByName(name);
		return goods;
	}

	@Override
	public Goods findById(String id) {
		Goods goods = goodsMapper.findById(id);
		return goods;
	}

	@Override
	public void delete(String id) {
		goodsMapper.delete(id);
	}

	@Override
	public List<Goods> findByCid(String cid) {
		List<Goods> goodslist = goodsMapper.findByCid(cid);
		return goodslist;
	}
	@Override
	public void searchGoods(){
	    List<Goods> goods = goodsMapper.findAll();
	    SolrClient client = new HttpSolrClient.Builder("http://172.4.14.34:8983/solr/xhq2").build();
	    for (Goods goods2 : goods) {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", goods2.getId());
		document.addField("salePrice", goods2.getSalePrice());
		document.addField("name", goods2.getName());
		document.addField("image", goods2.getImage());
		document.addField("description", goods2.getDescription());
		try {
		    client.add(document);
		} catch (SolrServerException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
		try {
		    client.commit();
		} catch (SolrServerException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	@Override
	public String findIdByName(String gname) {
		return goodsMapper.findIdByName(gname);
	}
	@Override
	public Double findPriceByName(String gname) {
		return goodsMapper.findPriceByName(gname);
	}

	
}
