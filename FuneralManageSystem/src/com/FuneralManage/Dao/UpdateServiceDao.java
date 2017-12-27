package com.FuneralManage.Dao;

import javax.sql.DataSource;

public class UpdateServiceDao extends BaseDAO {

	public UpdateServiceDao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	public String getService(String deadId) {
		
		String sql="SELECT remainsin.*,deadchosenurn.*,rentcoffin.`beRentCost`,rentcoffin.`realRentCost`,rentcoffin.`carBeCost` ,rentcoffin.`carRealCost` , deadserviceitem.*,cremationserviceitem.`itemName` FROM remainsin LEFT JOIN deadchosenurn ON remainsin.`deadID`=deadchosenurn.`deadId` LEFT JOIN deadserviceitem ON remainsin.`deadID`=deadserviceitem.`deadID`  LEFT JOIN rentcoffin ON remainsin.`deadID`=rentcoffin.`deadID` LEFT JOIN cremationserviceitem ON cremationserviceitem.`typeNo`=deadserviceitem.`CremationTypeNo` AND cremationserviceitem.`itemNo`=deadserviceitem.`CremationItemNo` WHERE remainsin.`deadID`=?";
		return this.getForJsonTran(sql,deadId);
		
	}
	public String getGoods(String deadId) {
		String sql="select * from deadfuneralgoods where deadId=?";
		return this.getForJsonTran(sql, deadId);
		
	}
	public String getRemainsCarryFee(String deadId) {
		String sql="select carBeCost,carRealCost from remainsCarry where deadId=?";
		return this.getForJsonTran(sql, deadId);
		
	}

}
