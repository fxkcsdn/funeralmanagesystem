package com.FuneralManage.Filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	protected String encoding = null;  
    protected FilterConfig filterConfig = null;  
    protected boolean ignore = true; 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		 this.encoding = null;  
	     this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (ignore || (request.getCharacterEncoding() == null)) {  
            String encoding = selectEncoding(request);  
            if (encoding != null)  
                request.setCharacterEncoding(encoding);  
        }  
  
        //process get parameters  
        HttpServletRequest httpRequest = (HttpServletRequest)request;  
        Map paramMap = request.getParameterMap();  
        String[] queryStringArray = {""};  
        if (httpRequest.getQueryString()!=null) {  
            queryStringArray = httpRequest.getQueryString().split("&");  
        }  
        for (int i = 0; i < queryStringArray.length; i++) {  
            queryStringArray[i] = queryStringArray[i].replaceAll("(.*)=(.*)", "$1");  
        }  
        Set<String> keySet = paramMap.keySet();  
        for(String key : keySet){  
            //check where param from  
            boolean isFromGet = false;  
            for(String paramFromGet:queryStringArray){  
                if(key.equals(paramFromGet))  
                {  
                    isFromGet = true;  
                }  
            }  
            if (!isFromGet) {  
                continue;  
            }  
            String[] paramArray = (String[])paramMap.get(key);  
            for(int i=0;i<paramArray.length;i++){  
                    paramArray[i] = new String(paramArray[i].getBytes("iso-8859-1"),encoding);  
            }  
        }  
        chain.doFilter(request, response);  
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 this.filterConfig = filterConfig;  
	        this.encoding = filterConfig.getInitParameter("charset");  
	        String value = filterConfig.getInitParameter("ignore");  
	        if (value == null)  
	            this.ignore = true;  
	        else if (value.equalsIgnoreCase("true"))  
	            this.ignore = true;  
	        else if (value.equalsIgnoreCase("yes"))  
	            this.ignore = true;  
	        else  
	            this.ignore = false;  
	  
	}
	 protected String selectEncoding(ServletRequest request) {  
		  
	        return (this.encoding);  
	  
	    } 

}
