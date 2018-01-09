package com.FuneralManage.Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.FuneralManage.Service.FilePhotoService;
import com.opensymphony.xwork2.ActionSupport;

public class FilePhotoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deadId;
	private String name;
	private String returnString;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}

	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	

	FilePhotoService fps = new FilePhotoService();
	public String cremationPhoto() {
		
		int count=0;
		count = fps.cremationPic(deadId, name);
		if(count>0){
			returnString="火化证拍照成功并保存";
		}else {
			returnString="火化证保存失败";
		}
		
		System.out.println(returnString);
		return "cremationPhoto";
				
	}
	public String TaxPhoto() {
		
		int count=0;
		count = fps.taxPic(deadId, name);
		if(count>0){
			returnString="税票拍照成功并保存";
		}else {
			returnString="税票保存失败";
		}
		System.out.println(returnString);
		return "taxPhoto";
				
	}
	public String benefitPhoto() {
		
		int count=0;
		count = fps.benefitPic(deadId, name);
		if(count>0){
			returnString="惠民补助拍照成功并保存";
		}else {
			returnString="惠民补助保存失败";
		}
		System.out.println(returnString);
		return "benefitPhoto";
				
	}
	

}
