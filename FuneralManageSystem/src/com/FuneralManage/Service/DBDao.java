package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDao {
	//打开数据库
	public static Connection openDateBase(String dateBaseName) {
		Connection conn=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://192.168.1.102:3306/"+dateBaseName;
			String url = "jdbc:mysql://localhost:3306/"+dateBaseName;
//			String username = "dongtai2";
//			String password = "dongtai2";
			String username = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, username,password);
		}
		catch (SQLException |ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
}
