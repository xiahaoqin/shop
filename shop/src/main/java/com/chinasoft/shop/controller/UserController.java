package com.chinasoft.shop.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chinasoft.shop.pojo.Category;
import com.chinasoft.shop.pojo.Goods;
import com.chinasoft.shop.pojo.User;
import com.chinasoft.shop.service.CategoryService;
import com.chinasoft.shop.service.GoodsService;
import com.chinasoft.shop.service.UserException;
import com.chinasoft.shop.service.UserService;
import com.chinasoft.shop.utils.VeriUtil;

@Controller
public class UserController {
	@Autowired
	private VeriUtil vu;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService cgService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("index")
	public String login(HttpServletRequest request,Model model) {
		//商品分类
		List<Category> categories = cgService.findAll();
		ServletContext application = request.getServletContext();
		application.setAttribute("categories2", categories);
		
		List<Goods> goodsList = goodsService.findAll();
		model.addAttribute("goodsList", goodsList);
		
		return "index";
	}

	@RequestMapping("re")
	public String login1() {
		return "register";
	}

	// 跳转用controller
	@RequestMapping("{jump}.action")
	public String action(@PathVariable("jump") String jump) {
		return jump;
	}
	@RequestMapping("{jump}/{jump1}.action")
	public String action2(@PathVariable("jump") String jump,@PathVariable("jump1") String jump1) {
		return jump+"/"+jump1;
	}
	//登录
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		try {
			userService.login(user);
			// 如果登录成功把user放进session
			User user1 = userService.findUserByName(user.getName());
			session.setAttribute("user", user1);
			mav.setViewName("redirect:index");
			return mav;
		} catch (UserException e) {
			mav.setViewName("login");
			mav.addObject("msg", e.getMessage());
			return mav;
		}
	}

	// 注册
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView register(User user, String veri, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String code = (String) session.getAttribute("code");
		boolean b = userService.checkveri(veri, code);
		if (b == true) {
			userService.addUser(user);
		} else {
			mav.setViewName("register");
			mav.addObject("msg", "验证码输入错误");
			return mav;
		}
		mav.setViewName("activate");
		return mav;
	}

	// 激活
	@RequestMapping("activate/{token}")
	public ModelAndView activate(@PathVariable("token") String token) {
		ModelAndView mav = new ModelAndView();
		try {
			userService.activate(token);
			mav.setViewName("login");
			mav.addObject("msg", "激活成功，请登录");
			return mav;
		} catch (UserException e) {
			mav.setViewName("register");
			mav.addObject("msg", e.getMessage());
			return mav;
		}

	}

	// 获取验证码
	@RequestMapping("veri/{time}")
	public void Veri(HttpServletResponse resp, HttpSession session)
			throws IOException {
		BufferedImage image = vu.getImage();
		ImageIO.write(image, "JPG", resp.getOutputStream());
		String code = vu.getVeriCode();
		// req.getSession().setAttribute("code", code);
		session.setAttribute("code", code);
	}

	// 检查用户名是否存在
	@RequestMapping(value = "checkname", method = RequestMethod.POST)
	public void checkname(@RequestParam String name, HttpServletResponse resp)
			throws IOException {
		User user = userService.findUserByName(name);
		if (user == null) {
			resp.getWriter().print("用户名不存在");
		} else {
			resp.getWriter().print("ok");
		}
	}

	//关闭session
	@RequestMapping("endsession")
	public String endsession(HttpServletRequest request){
		request.getSession().invalidate();
		return "index";
	}
}
