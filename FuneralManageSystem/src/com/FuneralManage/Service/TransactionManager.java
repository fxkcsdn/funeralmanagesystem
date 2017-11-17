package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	private Connection conn = null;// Connnection对象
	
	/**
	 * 获取数据库连接
	 * @throws SQLException
	 */
	public void setConnection() throws SQLException
	{
		// 连接数据库
		conn = DBDao.openDateBase("dongtai");
	}
	
	/**
	 * 获取Connection对象
	 * @return Connection对象
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException
	{
		return conn;
	}
	
	/**
	 * 事务开始
	 * @throws SQLException
	 */
	public final void start() throws SQLException
	{
		setConnection();
		// 设置为手动提交
		conn.setAutoCommit(false);
	}
	
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public final void commit() throws SQLException
	{
		// 事务提交
		conn.commit();
	}
	
	/**
	 * 关闭事务
	 * @throws SQLException
	 */
	public final void close()
	{
		try
		{
			conn.setAutoCommit(true);
			conn.setReadOnly(false);
			// 关闭事务
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public final void rollback()
	{
		try
		{
			// 回滚事务
			conn.rollback();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
