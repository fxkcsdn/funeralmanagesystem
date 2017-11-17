package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class AddFuneralGoodsService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String addFuneralGoodsDao(String deadId, String goodsName, int goodsBeCost, int goodsRealCost){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadfuneralgoods(deadID,goodsName,goodsBeCost,goodsRealCost)values(?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, goodsName);
				ps.setInt(3, goodsBeCost);
				ps.setInt(4, goodsRealCost);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项商品！";
				}
				else
				{
					returnString="添加商品失败!";
				}
				
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="数据库操作失败!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}
	
}
