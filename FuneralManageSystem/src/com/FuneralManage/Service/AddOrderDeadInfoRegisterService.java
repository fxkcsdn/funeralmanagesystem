package com.FuneralManage.Service;

import com.FuneralManage.entity.DeadInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class AddOrderDeadInfoRegisterService extends BaseService
{
	public String returnString;
	public String orderDeadInfoRegister(DeadInfo deadInfo)
	{
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null)
		{
			System.out.print("deadInfoRegister中数据库连接成功");
			String sql="insert into remainsIn(deadID,deadName,deadSex,memberMobile,remainsNumber,deadType,deadAge,inTime,address,proofUnit,memo,deadReason)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			try
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadInfo.deadId);
				ps.setString(2, deadInfo.deadName);
				ps.setString(3, deadInfo.deadSex);
				ps.setString(4, deadInfo.memberMobile);
				ps.setInt(5, deadInfo.deadNumber);
				ps.setString(6, deadInfo.deadKind);
				ps.setInt(7, deadInfo.deadAge);
				ps.setString(8, deadInfo.inTime);
				ps.setString(9, deadInfo.deadAddress);
				ps.setString(10, deadInfo.deadProveUnit);
				ps.setString(11, deadInfo.deadExtraInfo);
				ps.setString(12, deadInfo.deadReason);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "行数据！";
				}
				else
				{
					returnString="提交数据失败!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				returnString="数据库操作失败1!";
				e.printStackTrace();
				return returnString;
			}
		}
		else
		{
			returnString="数据库操作失败2!";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
		}
	
}
