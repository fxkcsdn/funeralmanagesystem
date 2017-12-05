package com.FuneralManage.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateTypeConverter extends DefaultTypeConverter {  
    @Override  
    public Object convertValue(Map<String, Object> context, Object value, Class toType) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        try {   
            if(toType == Date.class){//当字符串向Date类型转换时  
                String[] params = (String[]) value;// request.getParameterValues()   //2017-12-05 12:16
                return dateFormat.parse(params[0]);  
            }
        } catch (Exception e) {
        	System.out.println("日期转化异常！");
        }  
        return null;  
    }  
}  
