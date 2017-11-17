package com.FuneralManage.Utility;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceFactory {

	public static DataSource dataSource = null;

	static {
		// 数据源只能被创建一次。
		dataSource = new ComboPooledDataSource("FuneralManageSystem");
	}

	public static DataSource createDataSource() {
		return dataSource;
	}
}
