package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryBenefitPageService extends BaseService{
	private int sumPage;
	public int getsumPage() {
		return sumPage;
	}
	public void setsumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int QueryBenefitPageDao(String startTime,String endTime) throws SQLException{
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
						
			
			String startTime1=startTime+" 00:00:00";
			String endTime1=endTime+" 23:59:59";			
									
			String sql="select count(*) from remainsin where subsidyMoney>0 and inTime between ? and ? ";
			ResultSet rs=null;
			PreparedStatement ps=null;
			try{
							
				ps=conn.prepareStatement(sql);
				ps.setString(1, startTime1);
				ps.setString(2, endTime1);
				
				rs=ps.executeQuery();
			
				rs.last();
				if(rs.getRow()==1)
				{
					sumPage=rs.getInt(1);
					
					
				}
				else
				{
					String sumPage="获取页数信息失败！";
				}	            
	            
			}
			catch(SQLException e){
				e.printStackTrace();
		
		}finally{
			rs.close();
			ps.close();
			conn.close();
		}
			}
		return sumPage;
	}

}
