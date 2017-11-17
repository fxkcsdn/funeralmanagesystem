/*
 * 生成带logo的二维码
 */
package com.FuneralManage.Utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.io.File;

import javax.imageio.ImageIO;

/*
 * 将字符串生成图像
 */
public class String2Image
{
	public static void main(String[] args) throws Exception
	{
		// 调用方法生成 d://a.png
		createStrImage("15", new Font("宋体", Font.BOLD, 35), new File(
				"d://aa.png"));

		//
		System.out.println("成功生成编号数字图像！");

		//createQRCode("黄302311196912241235", "d://aaa.png", "d://aa.png");// 302311196912241235
	}

	// 根据str,font的样式以及输出文件目录
	public static void createStrImage(String str, Font font, File outFile)
			throws Exception
	{

		// 获取font的样式应用在str上的整个矩形
		Rectangle2D r = font.getStringBounds(str, new FontRenderContext(
				AffineTransform.getScaleInstance(1, 1), false, false));
		// 获取单个字符的高度
		int unitHeight = (int) Math.floor(r.getHeight());
		// 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
		int width = (int) Math.round(r.getWidth()) + 1;
		// 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
		int height = unitHeight+1;

		// 创建图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		// Graphics g = image.getGraphics();
		Graphics g = image.createGraphics();
		g.setColor(Color.yellow);
		g.fillRect(0, 0, width, height);// 先用白色填充整张图片,也就是背景
		g.setColor(Color.black);// 再换成黑色
		g.setFont(font);// 设置画笔字体
		g.drawString(str, 0, font.getSize());// 画出字符串
		g.dispose();

		// 创建图像文件
		ImageIO.write(image, "png", outFile);
	}
}