package com.chinasoft.shop.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class VeriUtil {
	public static final int width = 80;
	public static final int height = 33;
	private String content = "qwertyuipasdfghjkzxcvbnm23456789QWERTYUIOPASDFGHJKLZXCVBNM";
	Random random = new Random();

	String code = "";
	String code2 = "";
	public BufferedImage getImage() {
		// 创建图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();// 获取画笔
		// g.setColor(Color.gray);//把画笔颜色设置为灰色
		// 把整个图片涂成灰色
		g.fillRect(0, 0, width, height);
		VeriUtil vu = new VeriUtil();
		for (int i = 0; i < 4; i++) {
			g.setColor(getRandomColor());
			g.setFont(new Font("宋体", Font.BOLD, 20));
			code = content.charAt(random.nextInt(content.length())) + "";
			g.drawString(code, 10 + i * 15, 30);
			code2 += code;
		}
		return image;
	}

	public String getVeriCode() {
		return code2;
	}


	private Color getRandomColor() {
		int red = random.nextInt(130);
		int green = random.nextInt(130);
		int blue = random.nextInt(130);
		return new Color(red, green, blue);
	}
}
