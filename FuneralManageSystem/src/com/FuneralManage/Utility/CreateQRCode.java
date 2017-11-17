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
 * ���ɶ�ά�룬��logo ͼƬ�Ŷ�ά���м�
 */
public class CreateQRCode
{
	public static void main(String[] args) throws Exception
	{
		// ���÷������� logo , d://a.png
		String2Image.createStrImage("08", new Font("����", Font.BOLD, 35), new File(
				"d://aa.png"));

		//
		System.out.println("�ɹ������ַ���ͼ��logo��");

		//
		create("302311196912241235", "d://bbb.png", "d://aa.png");// 302311196912241235
		
		System.out.println("�ɹ����ɶ�ά��with logo��");
	}
	
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * 
	 * @param content
	 *            ��ά��ͼƬ������
	 * @param imgPath
	 *            ���ɶ�ά��ͼƬ������·��
	 * @param ccbpath
	 *            ��ά��ͼƬ�м��logo·��
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

			// �趨ͼ����ɫ > BLACK
			gs.setColor(Color.BLACK);
			// ����ƫ���� �����ÿ��ܵ��½�������
			int pixoff = 2;
			// ������� > ��ά��
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

			// ��logo
			Image logoImg = ImageIO.read(new File(ccbPath));
			// ʵ����һ��Image����
			gs.drawImage(logoImg, 110, 110, null);
			gs.dispose();

			bufImg.flush();

			// ʵ����һ��Image����
			/*
			 * gs.drawImage(img, 55, 55, null); gs.dispose(); bufImg.flush();
			 */

			// ���ɶ�ά��QRCodeͼƬ
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