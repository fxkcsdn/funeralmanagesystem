package com.FuneralManage.Service;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class GenerateRemainNumberService extends BaseService
{
	public String returnString;
	public String decideRemainNumber()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		String remainsNumber;
		remainsNumber=null;
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			System.out.println("decideRemainNumber中连接数据库成功");
			String sql="SELECT * FROM remainsin where inTime = ?";
			//从数据库表格中选择出与界面上输入的进馆时间相同的一天中已经登记的遗体的编号的最大值
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, date);
				rs=ps.executeQuery();
				int count=0;
//				rs.last();
//				if(rs.getRow()==1){
//					deadNumber=rs.getInt(1)+1;
//				}else{
//					deadNumber=1;
//				}
				while(rs.next())
				{
					result=rs.getString("remainsNumber");
					++count;
				}
				if(result !="")
				{
					result=count+1+"";
				}
				else
				{
					result ="1";
				}
				returnString = remainsNumber+"";
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("获取最大遗体编号失败");
			}
		}
		remainsNumber=result;
		System.out.println(remainsNumber);
		return remainsNumber;
	}
}
