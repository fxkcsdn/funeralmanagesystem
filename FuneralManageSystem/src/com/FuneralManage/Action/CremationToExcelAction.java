package com.FuneralManage.Action;

import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





import com.FuneralManage.Service.CremationToExcelService;
import com.opensymphony.xwork2.ActionSupport;

public class CremationToExcelAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String returnString;
	private String result ;
	private String deadId ;
	private String allDeadId;
	
	public String getAllDeadId() {
		return allDeadId;
	}

	public void setAllDeadId(String allDeadId) {
		this.allDeadId = allDeadId;
	}

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	CremationToExcelService CremationToExcelDao=new CremationToExcelService();
	
	public String CremationToExcel() throws SQLException{
		    				
		 org.json.JSONArray returnString1 = CremationToExcelDao.cremationToExcelDao(startTime, endTime);
		 	  
 		 returnString = returnString1.toString();
 		 
 				 
		 return "CremationToExcel";
	}
	
	public String ExcelById() throws Exception{
		
		if(allDeadId.length()>1){
			String funeralGoodsStr="["+allDeadId+"]";
			
			JSONArray jsonArrayIdArray=JSONArray.fromObject(funeralGoodsStr);
			
			if(jsonArrayIdArray.size()>0){
				for(int i=0;i<jsonArrayIdArray.size();i++){
					JSONObject personalId = jsonArrayIdArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					deadId = personalId.getString("deadId");
					 CremationToExcelDao.ExcelById(deadId);		
					returnString = "生成逝者excel成功";
				}
			}
		}
		
		
		
		return "excelById";
	}
}
