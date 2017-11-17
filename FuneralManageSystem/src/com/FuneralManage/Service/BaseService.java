package com.FuneralManage.Service;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.FuneralManage.Utility.*;

public class BaseService {
	protected final DataSource dataSource = DataSourceFactory.createDataSource();

	public BaseService(){
		HttpSession session;
	}


}
