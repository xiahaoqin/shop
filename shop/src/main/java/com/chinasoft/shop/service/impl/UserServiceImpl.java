package com.chinasoft.shop.service.impl;

import java.util.Date;
import java.util.UUID;




import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.shop.mapper.UserMapper;
import com.chinasoft.shop.pojo.User;
import com.chinasoft.shop.service.UserException;
import com.chinasoft.shop.service.UserService;
import com.chinasoft.shop.utils.MailUtils;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	//添加用户
	public void addUser(User user) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setAvatar("default.png");
		user.setBalance(0.0);
		user.setTotal(0.0);
		user.setStates("0");
		user.setToken(user.getId());
		Date regTime = new Date();
		user.setRegTime(regTime);
		userMapper.addUser(user);
		
		//发送邮件
		MailUtils.sendMail(user.getEmail(), user.getToken());
	}

	//判断验证码
	public boolean checkveri(String veri,String code) {
		if(code.equals(veri)){
			return true;
		}else{
			return false;
		}
	}

	//改变用户激活状态
	public void activate(String token) throws UserException{
			User user = userMapper.findById(token);
			long time = new Date().getTime()-user.getRegTime().getTime();
			if(time/1000/60>10){
				userMapper.delete(user.getId());
				throw new UserException("激活码已过期");
			}
			//把user对象的状态改为1
			user.setStates("1");
			//更新user信息
			userMapper.update(user);
	}

	//登录
	public void login(User user) throws UserException {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		User user1 = findUserByName(user.getName());
		if(!user.getPassword().equals(user1.getPassword())){
			throw new UserException("密码错误,请重新输入");
		}
		if(user1.getStates().equals("0")){
			throw new UserException("该用户未激活");
		}
	}
	
	//根据名字查找用户
	public User findUserByName(String name){
		User user = userMapper.findUserByName(name);
		return user;
	}

	@Override
	public User findById(String id) {
		return userMapper.findById(id);
	}
}
