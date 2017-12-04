package com.FuneralManage.Exception;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
 

import com.opensymphony.xwork2.ActionSupport;
 
public class ErrorProcessor extends ActionSupport {
 
    private static final long serialVersionUID = 1L;
     
    private Exception exception;
    private String error;
    private String returnString;
    public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getError() {
        return error;
    }
     
    @JSON(serialize=false)
    public Exception getException() {
        return exception;
    }
 
    public void setException(Exception exception) {
        this.exception = exception;
    }
 
    @Override
    public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();  
		String contentType = "text/plain;charset=UTF-8";  
		error = exception.getMessage();
		response.setContentType(contentType);  
		response.setStatus(500);   
        return SUCCESS;
    }
}
