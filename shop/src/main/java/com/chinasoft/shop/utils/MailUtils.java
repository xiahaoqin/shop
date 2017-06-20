package com.chinasoft.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/*
 * 发送邮件：
 * 1.获取session对象
 * 2.获取邮件对象（mimeMessage），并设置相应的属性
 * 3.发送邮件：transPort.send（msg）
 */
public class MailUtils {
	public static void sendMail(String to,String token ) {
		System.out.println("1111111111111111");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");//配置发件服务器
		props.put("mail.smtp.auth", "true");//设置该邮箱需要验证
		Authenticator authenticator = new Authenticator() {
			//验证用户名、密码
			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("767636536", "iwpmczhopwjcbcig");
				return new PasswordAuthentication("18180576653", "xxdmh543627");
			}
		};
		
		//获取Session对象
		Session session = Session.getInstance(props, authenticator);
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("18180576653@163.com"));
			msg.setRecipients(RecipientType.TO,to);
			msg.setSubject("激活邮件--中软国际");
//			msg.setContent("<a href=\"http://localhost:8080/askdemo/UserServlet?opr=activate&token="+token+"\">点击激活</a>","text/html;charset=utf-8");
			msg.setContent("<a href=\"http://172.4.14.34/shop/activate/"+token+"\">点击激活:http://172.4.14.34/shop/activate/"+token+"</a>","text/html;charset=utf-8");
//			//发送邮件
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
