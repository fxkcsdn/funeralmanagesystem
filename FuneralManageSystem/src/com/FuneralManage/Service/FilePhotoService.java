package com.FuneralManage.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.FuneralManage.Dao.FilePhotoDao;
import org.apache.commons.codec.binary.Base64;

public class FilePhotoService extends BaseService{
	
	private  int  message;
			
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	FilePhotoDao fpd = new FilePhotoDao(dataSource);
	public  int cremationPic(String deadId,String name) {
				
		String base64 = getImgStr(name);		
		message = fpd.cremationPic(deadId,base64);
		return message;
				
	}
	public  int taxPic(String deadId,String name) {
				
		String base64 = getImgStr(name);		
		message = fpd.taxPic(deadId,base64);
		return message;		
		
	}
	public  int benefitPic(String deadId,String name) {
		
		String base64 = getImgStr(name);		
		message = fpd.benefitPic(deadId, base64);
		return message;		
		
	}
	
	/**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgStr(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
  
        
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }



}
