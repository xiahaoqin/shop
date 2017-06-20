package com.chinasoft.shop.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chinasoft.shop.pojo.Category;
import com.chinasoft.shop.pojo.Goods;
import com.chinasoft.shop.service.CategoryService;
import com.chinasoft.shop.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	
	// 添加商品
	@RequestMapping(value = "goods.add", method = RequestMethod.POST)
	public String add(@RequestParam("thumbnail")MultipartFile file,Goods goods,
			HttpServletRequest request,Model model,HttpSession session) {
	 if (!file.isEmpty()) {  
		ServletContext context = session.getServletContext();
		String realPath = context.getRealPath("/images/goods");
		
		String fileName = getFileName(file);
		// 文件保存路径  
        String filePath = realPath+fileName;
        goods.setImage("/shop/images/goods"+fileName);
        try {
        // 转存文件  
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	 }
        goods.setId(UUID.randomUUID().toString().replace("-", ""));
        String cid = categoryService.findIdByName(goods.getCategoryName());
        goods.setCid(cid);
		goodsService.add(goods);
		return "redirect:goods.findAll";
	}
	// 编辑商品
		@RequestMapping(value = "goods.edit/{id}", method = RequestMethod.POST)
		public String edit(@RequestParam("thumbnail")MultipartFile file,Goods goods,
				HttpServletRequest request,Model model,HttpSession session,@PathVariable("id") String id) {
		 if (!file.isEmpty()) {  
			ServletContext context = session.getServletContext();
			String realPath = context.getRealPath("/images/goods");
			
			String fileName = getFileName(file);
			// 文件保存路径  
	        String filePath = realPath+fileName;
	        goods.setImage("/shop/images/goods"+fileName);
	        try {
	        // 转存文件  
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		 }
	        String cid = categoryService.findIdByName(goods.getCategoryName());
	        goods.setCid(cid);
			goodsService.edit(goods,id);
			model.addAttribute("msg", "修改成功");
			return "admin/goods_add";
		}
	// 查找所有商品
	@RequestMapping("goods.findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Goods> goodslist = goodsService.findAll();
		List<Category> categories = categoryService.findAll();
		mav.setViewName("admin/goods_list");
		mav.addObject("goodslist", goodslist);
		mav.addObject("categories", categories);
		return mav;
	}
	// 查找所有商品分类
	@RequestMapping("goods.findcate")
	public ModelAndView findcate() {
		ModelAndView mav = new ModelAndView();
		List<Category> categories = categoryService.findAll();
		mav.setViewName("admin/goods_add");
		mav.addObject("categories", categories);
		mav.addObject("goodsSub", "goods.add");//动态控制增加、编辑
		return mav;
	}	
	
	public static String getFileName(MultipartFile file){
		String fileName = UUID.randomUUID().toString().replace("-", "");
		//获取上传文件的名称
		String oriFileName = file.getOriginalFilename();
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		return "/"+fileName+suffix;
	}
	
	@RequestMapping("goods.check")
	public void check(@RequestParam String name,HttpServletResponse resp) throws IOException{
		Goods goods = goodsService.check(name);
		if (goods == null) {
			resp.getWriter().print("ok");
		} else {
			resp.getWriter().print("该商品已经存在");
		}
	}
	//编辑
	@RequestMapping("goods.findById/{id}")
	public ModelAndView findById(@PathVariable("id")String id){
		ModelAndView mav = new ModelAndView();
		Goods goods = goodsService.findById(id);
		List<Category> categories = categoryService.findAll();
		mav.setViewName("admin/goods_add");
		mav.addObject("goods", goods);
		mav.addObject("categories", categories);
		mav.addObject("goodsSub", "goods.edit/"+id);//动态控制编辑、增加
		return mav;
	}
	//详细信息
	@RequestMapping("goods.findOne/{id}")
	public ModelAndView findOne(@PathVariable("id")String id){
		ModelAndView mav = new ModelAndView();
		Goods goods = goodsService.findById(id);
		mav.setViewName("goods_view");
		mav.addObject("goods", goods);
		return mav;
	}
	//删除
	@RequestMapping("goods.delete/{id}")
	public String delete(@PathVariable("id")String id){
		goodsService.delete(id);
		return "redirect:goods.findAll";
	}
	//
	@RequestMapping("goods.findByCid/{cid}/{name}")
	public ModelAndView findByCid(@PathVariable("cid")String cid,@PathVariable("name")String name){
		ModelAndView mav = new ModelAndView();
		List<Goods> goodslist = goodsService.findByCid(cid);
		mav.setViewName("goods_list");
		mav.addObject("goodslist", goodslist);
		mav.addObject("name", name);
		return mav;
	}
	
	
	
	 @RequestMapping("add123")
	    public void addSearch(){
		goodsService.searchGoods();
	  }
	 
	 @RequestMapping("query123")
	    public String query(String msg,Model model){
		if(msg==null || msg.equals("")){
		    msg = "*:*";
		}
		 SolrClient client = new HttpSolrClient.Builder("http://172.4.14.34:8983/solr/xhq2").build();
		 SolrQuery query = new SolrQuery();
		 query.set("q", msg);
		 query.set("start", 0);
		 query.set("rows",100);
		 QueryResponse response = null;
		try {
		    response = client.query(query);
		} catch (SolrServerException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		 SolrDocumentList list = response.getResults();
		 model.addAttribute("goodslist", list);
		 return "admin/goods_list";
	    }
}
