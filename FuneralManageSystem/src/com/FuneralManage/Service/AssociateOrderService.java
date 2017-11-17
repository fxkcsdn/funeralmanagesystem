package com.FuneralManage.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AssociateOrderService extends BaseService
{
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String findOrderedInfo(String deadId)
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select deadId,estimatedDate,orderNumber,deadName,orderTime,factInTime,contactMobile,contactName from deadorderbasic where deadID='"+ deadId +"'";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				System.out.println(rs.toString());
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				if(rs.next()){
				jsonObject.put("deadId", rs.getString("deadId"));
				jsonObject.put("estimatedDate", rs.getString("estimatedDate"));
				jsonObject.put("orderNumber", rs.getString("orderNumber"));
				jsonObject.put("deadName", rs.getString("deadName"));
				jsonObject.put("orderTime", rs.getString("orderTime"));
				jsonObject.put("factInTime", rs.getString("factInTime"));
				jsonObject.put("contactMobile", rs.getString("contactMobile"));
				jsonObject.put("contactName", rs.getString("contactName"));
				jsonArray.add(jsonObject);
				returnString=jsonArray.toString();
				}
			}

			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		
	}
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;	
}


}

    