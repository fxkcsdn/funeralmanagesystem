package com.FuneralManage.Action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.FuneralManage.Service.GetQRCodeService;
import com.opensymphony.xwork2.ActionSupport;
import com.swetake.util.Qrcode;

public class PrintQRCodeAction extends ActionSupport{			
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String deadNumber;
	private String returnString;

	public String getDeadNumber() {
		return deadNumber;
	}

	public void setDeadNumber(String deadNumber) {
		this.deadNumber = deadNumber;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	GetQRCodeService getQRCodeDao=new GetQRCodeService();
	
	//获取遗体二维码打印界面的遗体信息
	public String getQRCodeInfo(){
		returnString=getQRCodeDao.getQRCodeInfoDao(deadId);
		return "getQRCodeInfo";
	}
	
	public String getQRCodeOrderInfo(){
		returnString=getQRCodeDao.getQRCodeOrderInfoDao(deadId);
		return "getQRCodeOrderInfo";
	}
	
	//生成二维码
	public void drawQRCode(){
		//System.out.println("aaadffdfd!");
			
		Font font=new Font("楷体", Font.BOLD, 35);
		Rectangle2D r = font.getStringBounds(deadNumber, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
		// 获取单个字符的高度
		int unitHeight = (int) Math.floor(r.getHeight());
		// 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
		int width = (int) Math.round(r.getWidth()) + 1;
		// 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
		int height = unitHeight+1;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.createGraphics();//此处image是存在内存中的一张图片
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);// 先用白色填充整张图片,也就是背景
		g.setColor(Color.black);// 再换成黑色
		g.setFont(font);// 设置画笔字体
		g.drawString(deadNumber, 0, font.getSize());// 画出字符串
		g.dispose();
//		ByteArrayOutputStream output = new ByteArrayOutputStream();
//		try{
//			ImageIO.write(image, "png", output);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//以上代码是生成遗体编号的图片流			
			
		try{
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(4);
				
			// System.out.println(content);67 + 12 * (size - 1)
			byte[] contentBytes = deadId.getBytes("utf-8");
			BufferedImage bufImg = new BufferedImage(170, 170, BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.white);
			gs.clearRect(0, 0, 170, 170);//供二维码显示区域的大小

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
							int  barCodeSize = 5;//barCodeSize控制二维码大小，即布满供二维码显示区域的二维码大小，如果太大超出区域既无法识别
							gs.fillRect(j * barCodeSize + pixoff, i * barCodeSize + pixoff, barCodeSize, barCodeSize); //barCodeSize控制二维码大小
							//gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else
			{
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
			}
			// 读logo
				
//			Image logoImg = ImageIO.read(new File(ccbPath));
			// 实例化一个Image对象。
//			gs.drawImage(logoImg, 110, 110, null);
			gs.drawImage(image, 60, 60, null);//60，60为显示中间图像在二维码中的位置
			
			gs.dispose();

			bufImg.flush();
			// 实例化一个Image对象。
			/*
			 * gs.drawImage(img, 55, 55, null); gs.dispose(); bufImg.flush();
			 */

//			生成二维码QRCode图片
//			File imgFile = new File("D:/aa.png");
//			ImageIO.write(bufImg, "png", imgFile);
				
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream out = response.getOutputStream();
			ImageIO.write(bufImg,"png",out);
			out.close();			//输出图片
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
}
