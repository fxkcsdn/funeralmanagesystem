package com.FuneralManage.Utility;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SingleThreadConnectionHolder {
	private static ThreadLocal<ConnectionHolder> localConnectionHolder = new ThreadLocal<ConnectionHolder>();
			//确保一个线程只有一个Connection;
	public static Connection getConnection(DataSource dataSource) throws SQLException {
		return getConnectionHolder().getConnection(dataSource);
	}

	public static void removeConnection(DataSource dataSource) {
		getConnectionHolder().removeConnection(dataSource);
	}

	private static ConnectionHolder getConnectionHolder() {
		ConnectionHolder connectionHolder = localConnectionHolder.get();
		if (connectionHolder == null) {
			connectionHolder = new ConnectionHolder();
			localConnectionHolder.set(connectionHolder);
		}
		return connectionHolder;
	}

}

