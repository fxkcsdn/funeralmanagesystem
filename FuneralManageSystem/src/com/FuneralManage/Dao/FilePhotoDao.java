package com.FuneralManage.Dao;

import javax.sql.DataSource;

public class FilePhotoDao extends BaseDAO {

	public FilePhotoDao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
		
		
	}
	public int cremationPic(String deadId, String base64) {
		
		String sql="update remainsIn set cremationPhoto=? where deadId=?";
		
		return this.saveOrUpdateOrDelete(sql, base64, deadId);
				
	}
	public int taxPic(String deadId, String base64) {
		
		String sql="update remainsIn set taxPhoto=? where deadId=?";
		
		return this.saveOrUpdateOrDelete(sql, base64, deadId);
				
	}
	public int benefitPic(String deadId, String base64) {
		
		String sql="update remainsIn set benefitPhoto=? where deadId=?";
		
		return this.saveOrUpdateOrDelete(sql, base64, deadId);
				
	}

}
