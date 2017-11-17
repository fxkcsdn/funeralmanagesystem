package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EditBindingService extends BaseService{
	public JSONArray getResult(String time)
	{
		JSONArray jsonArray = new JSONArray();
		String datePara = time+"%";
		String carryNumber = null;
		String deadID = null;
		String contactMobile = null;
		String contactName = null;
		String carNumber = null;
		String startTime = null;
		String returnTime = null;
		String address = null;
		String bInternalCar = null;
		String carBeCost = null;
		String carRealCost = null;
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn!=null) {
			String sql="SELECT * FROM remainscarry WHERE startTime LIKE ? AND deadID IS NOT NULL";
			ResultSet rs=null;
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					JSONObject jsonObject=new JSONObject();
					carryNumber = rs.getString("carryNumber");
					deadID = rs.getString("deadID");
					contactMobile = rs.getString("contactMobile");
					contactName = rs.getString("contactName");
					carNumber = rs.getString("carNumber");
					startTime = rs.getString("startTime");
					returnTime = rs.getString("returnTime");
					address = rs.getString("address");
					carBeCost = rs.getString("carBeCost");
					carRealCost = rs.getString("carRealCost");
					bInternalCar = rs.getString("bInternalCar");
					jsonObject.put("carryNumber", carryNumber);
					jsonObject.put("deadID", deadID);
					jsonObject.put("contactMobile", contactMobile);
					jsonObject.put("contactName", contactName);
					jsonObject.put("carNumber", carNumber);
					jsonObject.put("startTime", startTime);
					jsonObject.put("returnTime", returnTime);
					jsonObject.put("address", address);
					jsonObject.put("bInternalCar", bInternalCar);
					jsonObject.put("carBeCost", carBeCost);
					jsonObject.put("carRealCost", carRealCost);
					jsonArray.add(jsonObject);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					rs.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					rs=null;
					conn=null;
				}
			}
		}
		return jsonArray;
	}
}
