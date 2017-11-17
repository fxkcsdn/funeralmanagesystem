package com.FuneralManage.Utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

/*
 * 生成二维码，将logo 图片放二维码中间
 */
public class CreateQRCode
{
	public static void main(String[] args) throws Exception
	{
		// 调用方法生成 logo , d://a.png
		String2Image.createStrImage("08", new Font("楷体", Font.BOLD, 35), new File(
				"d://aa.png"));

		//
		System.out.println("成功生成字符串图像logo！");

		//
		create("302311196912241235", "d://bbb.png", "d://aa.png");// 302311196912241235
		
		System.out.println("成功生成二维码with logo！");
	}
	
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            二维码图片的内容
	 * @param imgPath
	 *            生成二维码图片完整的路径
	 * @param ccbpath
	 *            二维码图片中间的logo路径
	 */
	public static int create(String content, String imgPath,
			String ccbPath)
	{
		try
		{
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(17);

			// System.out.println(content);67 + 12 * (size - 1)
			byte[] contentBytes = content.getBytes("utf-8");
			BufferedImage bufImg = new BufferedImage(259, 259,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.white);
			gs.clearRect(0, 0, 270, 270);

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120)
			{
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++)
				{
					for (int j = 0; j < codeOut.length; j++)
					{
						if (codeOut[j][i])
						{
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else
			{
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
				return -1;
			}

			// 读logo
			Image logoImg = ImageIO.read(new File(ccbPath));
			// 实例化一个Image对象。
			gs.drawImage(logoImg, 110, 110, null);
			gs.dispose();

			bufImg.flush();

			// 实例化一个Image对象。
			/*
			 * gs.drawImage(img, 55, 55, null); gs.dispose(); bufImg.flush();
			 */

			// 生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);
		} catch (Exception e)
		{
			e.printStackTrace();
			return -100;
		}
		return 0;
	}
}