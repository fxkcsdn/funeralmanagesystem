package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import net.sf.json.JSONObject;
import java.util.UUID;

import org.apache.struts2.views.jsp.ElseTag;

public class ExchangeDataService 
{
	public String getUUID()    															//生成32位随机字符ID，即UUID，用于火化内码、出车内码
	{
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
		return uuid;
	}
	
	public ArrayList<String> getDeadId(String date)    								    //获取当天的身份证号
	{
		String datePara = date+"%";
		ArrayList<String> deadList = new ArrayList<String>();
		Connection conn = DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "select * from remainsin where uploadStatus=? and inTime like ? and deadResidence !=?";
			ResultSet rs = null;
			try {
				PreparedStatement pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, "0");
				pStatement.setString(2, datePara);
				pStatement.setString(3, "其它");
				rs=pStatement.executeQuery();
				while(rs.next())
				{
					deadList.add(rs.getString("deadID"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(rs!=null)
				{
					try {
						rs.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					rs=null;  
				}
				if(conn!=null)
				{
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn=null;
				}
			}
		}
		return deadList;
	}
	public JSONObject  queryData(String deadid)        											//针对每一个身份证，获取交互需要的信息
	{
		JSONObject jsonObject=new JSONObject(); 												//每一个对象都对应一个身份的全部信息
		String standardCode = null;
		String innerCode = null;
		String sex = null;
		String birth = null;
		String deathCause = null;
		String deathDisease = null;
		String cremation_start_time = null;
		String cremation_end_time = null;
		String carry_start_time = null;
		String carry_end_time = null;
		String ashesgo = null;
		String ashestime = null;
		String contactdate = null;
		String relationship = null;
		String operator = null;
		if(deadid.length()==18){
			birth = deadid.substring(6,10)+"-"+deadid.substring(10,12)+"-"+deadid.substring(12,14);
		}
		else{
			birth = "19"+deadid.substring(6,8)+"-"+deadid.substring(8,10)+"-"+deadid.substring(10,12);
		}
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sqlString = "select * from remainsin where deadID =?";   									//查询remainsin表
			ResultSet resultSet = null;
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
				preparedStatement.setString(1, deadid);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					/*************************对一些数据进行处理******************************/
					standardCode = getDeptCode(resultSet.getString("deadResidence"));
					innerCode = getInnerCode(resultSet.getString("deadResidence"));
					deathCause = getDeathCause(resultSet.getString("deadReason"));
					cremation_start_time = getCremationTime(deadid,"inTime");
					if(cremation_start_time.length()==1)
					{
						cremation_start_time=addDateMinut(getInTime(deadid).substring(0,19), 30);
					}
					cremation_end_time = getCremationTime(deadid,"outTime");
					if(cremation_end_time.length()==1)
					{
						cremation_end_time=addDateMinut(cremation_start_time,20);
					}
					carry_start_time = getCarryTime(deadid, "startTime");
					carry_end_time = getCarryTime(deadid, "returnTime");
					contactdate = resultSet.getString("inTime").substring(0,10);
					ashesgo = getAshesGoCode(resultSet.getString("ashesDisposition"));
					ashestime = addDateMinut(cremation_end_time, 20);                                             //领灰时间取火化完成时间，向后推20min                 
					relationship = getRelation(resultSet.getString("operatorRelation"));
					operator = getOperator(deadid);
					if(resultSet.getString("pathogeny").length()<1)
					{
						deathDisease = "";
					}
					else{
						deathDisease = getDeathDisease(resultSet.getString("pathogeny"));
					}
					standardCode = getDeptCode(resultSet.getString("deadResidence"));
					if(resultSet.getString("deadSex").equals("男"))
					{
						sex="1";
					}
					else if(resultSet.getString("deadSex").equals("女")){
						sex="2";
					}
					else{
						sex="3";
					}
/**************************************************************1.11表所需的数据**************************************************************/
					jsonObject.put("DEAD_ID", getSysDeadID());                                       //逝者编号，上传的函数中需要自动生成
					jsonObject.put("NAME", resultSet.getString("deadName"));                         //逝者姓名
					jsonObject.put("SEX", sex);			                                             //性别
					jsonObject.put("ID_CARD", deadid);                                  			 //身份证号码
					jsonObject.put("BIRTHDAY",birth);                                    			 //出生日期
					jsonObject.put("DEPT_CODE", standardCode);                           			 //死者户籍地行政区划代码，12位标准码
					jsonObject.put("MORG_AREA", innerCode);                              			 //32位系统编码
					jsonObject.put("POPULACE", resultSet.getString("deadResidence"));                 //户口所在地
					jsonObject.put("DEATH_DATE", resultSet.getString("deadTime").substring(0,10));   //死亡时间
					jsonObject.put("DEATH_CAUSE", deathCause);                 						 //死亡原因
					jsonObject.put("DEATH_DISEASE",deathDisease);               					 //死亡病种
					jsonObject.put("DEAD_AGE", resultSet.getString("deadAge"));						 //死亡年龄
					jsonObject.put("DEAD_PLACE", resultSet.getString("address"));                    //去世地点
/**************************************************************1.12表所需的数据***************************************************************/
					jsonObject.put("CNAME",resultSet.getString("dealerName"));                       //承办人姓名
					jsonObject.put("CID_CARD",resultSet.getString("dealerID"));                      //承办人身份证
					jsonObject.put("CRELATION",relationship);            							 //与逝者关系
					jsonObject.put("CADDRESS",resultSet.getString("dealerAddress"));                 //承办人住址
																									 //承办人证件类型(见1.11)
																									 //逝者编码(见1.11)
/**************************************************************1.13表所需的数据***************************************************************/
					jsonObject.put("APPLY_ID",getApplyID());										 //业务编号
					jsonObject.put("RECEIVE_PEOPLE","X");                                            //接收人
					jsonObject.put("RECEIVE_TIME",getInTime(deadid).substring(0,19));                //接收时间
					jsonObject.put("CONTRACT_PEOPLE",operator);                                      //受理人
					jsonObject.put("CONTRACT_DATE",contactdate);                                     //受理日期
					jsonObject.put("TAKEASHES_TIME",ashestime.substring(0,19));           			 //领灰时间
					jsonObject.put("FINISH_TIME",ashestime.substring(0,19));                         //完成时间
					jsonObject.put("ASHES_GO",ashesgo);                                              //骨灰去向
																									 //逝者编码(见1.11)
/**************************************************************1.14表所需的数据****************************************************************/
					jsonObject.put("RECORD_ID1", getUUID());										 //出车内码
					if(carry_start_time.equals("X")) 												 //出发时间
					{
						jsonObject.put("START_TIME", "X");	
					}
					else{
						jsonObject.put("START_TIME", carry_start_time.substring(0,19));	
					}	
					if(carry_end_time.equals("X")) 												     //返回时间
					{
						jsonObject.put("END_TIME", "X");	                                         
					}
					else{
						jsonObject.put("END_TIME", carry_end_time.substring(0,19));	
					}					 
					 										                                         //逝者编码(见1.11)
																									 //业务编号(见1.12)
/**************************************************************1.18表所需的数据*****************************************************************/
					jsonObject.put("RECORD_ID2", getUUID()); 										 //火化内码
					jsonObject.put("START_TIME2", cremation_start_time.substring(0,19)); 			 //火化开始时间
					jsonObject.put("END_TIME2", cremation_end_time.substring(0,19)); 				 //火化结束时间
																									 //逝者编码(见1.11)
																									 //业务编号(见1.12)
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
					try {
						resultSet.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					resultSet=null;
					connection=null;
			}
		}
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	public String getDeptCode(String deadResidence)    //获取12位标准码
	{
		String DEPT_CODE = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT deadResidence FROM residencearea WHERE fullName = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, deadResidence);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					DEPT_CODE = resultSet.getString("deadResidence");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return DEPT_CODE;
	}
	public String getInnerCode(String deadResidence)    //获取32位标准码
	{
		String innerCode = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT innerCode FROM residencearea WHERE fullName = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, deadResidence);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					innerCode = resultSet.getString("innerCode");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return innerCode;
	}
	public String getDeathCause(String deadReason)    //获取死亡原因
	{
		String deathCause = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT memo FROM deadreason WHERE reasonName = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, deadReason);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					deathCause = resultSet.getString("memo");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return deathCause;
	}
	public String getOperator(String deadid){
		String operator = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT directorName FROM remainsin WHERE deadID = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, deadid);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					operator = resultSet.getString("directorName");
				}
				if(operator.length()<1)
				{
					operator = "X";
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return operator;
	}
	public String getDeathDisease(String pathogeny)    //获取病因
	{
		String deathCause = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT diseaseCode FROM disease WHERE diseaseName = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, pathogeny);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					deathCause = resultSet.getString("diseaseCode");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return deathCause;
	}
	public String getInTime(String deadid){
		String code = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT inTime FROM remainsin WHERE deadID =  ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, deadid);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					code = resultSet.getString("inTime");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return code;
	}
	public String getAshesGoCode(String des) {                                  //获取骨灰去向编号
		String code = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT codeValue FROM ashesdisposition WHERE description = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, des);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					code = resultSet.getString("codeValue");
				}
				if(code.length()<1)
				{
					code = "X";
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return code;
	}
	public String getRelation(String name){                                 //获取承办人与逝者关系代码
		String code = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null)
		{
			String sql = "SELECT relationshipCode FROM relationship WHERE relationshipName = ?";
			ResultSet resultSet = null;
			try{
				PreparedStatement pStatement = connection.prepareStatement(sql);
				pStatement.setString(1, name);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()){
					code = resultSet.getString("relationshipCode");
				}
				if(code.length()<1)
				{
					code = "X";
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				try {
					resultSet.close();
					connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				resultSet=null;
				connection=null;
			}
		}
		return code;
	}
	public String getSysDeadID(){                                     //获取DEAD_ID编号
		String countID = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String queryYear = df.format(new Date());
		String queryParam = "SZ320981-"+queryYear+"-%";
		NumberFormat format = new DecimalFormat("000000");  //数值模板，1---->000001,...
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.0.210:1521:orcl","orcl_yc","1qaz-pl,");
			if(conn!=null)
			{
				//String sqlString="select * from dic_city where p_id=?";
				String sqlString="select count(DEAD_ID) as result  from FIS_DEAD_INFO where DEAD_ID like?";
				ResultSet resultSet = null;
				try {
					PreparedStatement pStatement = conn.prepareStatement(sqlString);
					//pStatement.setString(1, "SZ3209032016%"); //盐都
					pStatement.setString(1, queryParam); //东台 
					resultSet=pStatement.executeQuery();
					resultSet.next();
					String countResult = resultSet.getString("result");
					countID = "SZ320981-"+queryYear+"-"+format.format((Integer.parseInt(countResult)+1));
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				finally{
					try {
						resultSet.close();
						conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					resultSet=null;
					conn=null;
				}
			}
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		return countID;
 }
	public String getApplyID()								//获取业务编号
	{
		String countID = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String queryYear = df.format(new Date());
		String queryParam = "FZ320981-"+queryYear+"-%";
		NumberFormat format = new DecimalFormat("000000");  //数值模板，1---->000001,...
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.0.210:1521:orcl","orcl_yc","1qaz-pl,");
			if(conn!=null)
			{
				//String sqlString="select * from dic_city where p_id=?";
				String sqlString="select count(APPLY_ID) as result  from FIS_APPLY_INFO where APPLY_ID like? ";
				ResultSet resultSet = null;
				try {
					PreparedStatement pStatement = conn.prepareStatement(sqlString);  //加参数为了能够使用resultset.last()去除最大的已有编号
					//pStatement.setString(1, "SZ3209032016%"); //盐都
					pStatement.setString(1, queryParam); //东台 
					resultSet=pStatement.executeQuery();
					resultSet.next();
					String countResult = resultSet.getString("result");
					countID = "FZ320981-"+queryYear+"-"+format.format((Integer.parseInt(countResult)+1));
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				finally{
					resultSet.close();
					conn.close();
				}
			}	
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		return countID;
	}
	public String getCremationTime(String deadid,String timeType)      //火化开始时间、结束时间
	{
		String result = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null){
			String sql = "SELECT * FROM deadserviceitem WHERE CremationTypeNo = ? AND  deadID = ?";
			ResultSet rs = null;
			try {
				PreparedStatement ps = connection.prepareStatement(sql); 
				ps.setString(1, "03");
				ps.setString(2, deadid);
				rs = ps.executeQuery();
				while(rs.next()){
					result = rs.getString(timeType);
				}
				if(result==""||result==null){               				    		//若不存在火化记录，则使用"X"标记
					result="X";
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				rs=null;
				connection=null;
			}
		}
		return result;
	}
	public String getCarryTime(String deadid,String timeType)          			//接运开始时间、结束时间
	{
		String result = null;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null){
			String sql = "SELECT * FROM remainscarry WHERE deadID = ?";
			ResultSet rs = null;
			try {
				PreparedStatement ps = connection.prepareStatement(sql); 
				ps.setString(1, deadid);
				rs = ps.executeQuery();
				while(rs.next()){
					result = rs.getString(timeType);
				}
				if(result==""||result==null){               				    		//若不存在接运记录，则使用X提交
					result="X";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					rs.close();
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				rs=null;
				connection=null;
			}
		}
		return result;
	}
	public static String addDateMinut(String day, int x)                                   //向前向后推时间
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = null;   
	        try {   
	            date = format.parse(day);   
	        } catch (Exception ex) {   
	            ex.printStackTrace();   
	        } 
	        if (date == null)   
	            return "";   
//	        System.out.println("front:" + format.format(date)); //显示输入的日期  
	        Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.MINUTE, x);// 24小时制   
	        date = cal.getTime();   
//	        System.out.println("after:" + format.format(date));  //显示更新后的日期 
	        cal = null;   
	        return format.format(date); 
	}
	public boolean linkAndUpload(JSONObject jsonObject)									   //逐条上传逝者信息
	{
		boolean result = true;                                                         	   //默认上传成功
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.0.210:1521:orcl","orcl_yc","1qaz-pl,");
			if (conn!=null) {
				PreparedStatement ps = null;
				try {
					conn.setAutoCommit(false);                                               //取消自动提交
					String sql1_11 = "INSERT INTO FIS_DEAD_INFO(DEAD_ID,NAME,SEX,CARD_TYPE,ID_CARD,BIRTHDAY,DEPT_CODE,MORG_AREA,POPULACE,DEATH_DATE,DEATH_PLACE,DEATH_CAUSE,DEATH_DISEASE,NATION,FOLK,CONTRACT_ORGAN,IF_NAMELESS,AGE,CREMATION_TIME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					String sql1_12 = "INSERT INTO FIS_PERSONAL_CUSTOM(DEAD_ID,NAME,CARD_TYPE,ID_CARD,RELATION,MOBIL_PHONE,FIX_PHONE,ADDRESS,POST_CODE,WORK_NAME,CUSTOM_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					String sql1_13 = "INSERT INTO FIS_APPLY_INFO(APPLY_ID,APPLY_TYPE,DEAD_ID,DELIVERY_ORGAN,DELIVERY_PEOPLE,RECEIVE_PEOPLE,RECEIVE_TIME,CONTRACT_ORGAN,CONTRACT_ORGAN_NAME,CONTRACT_PEOPLE,CONTRACT_DATE,IF_FINISH,IF_TAKEASHES,TAKEASHES_TIME,FINISH_TIME,ASHES_GO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					String sql1_14 = "INSERT INTO FIS_SERVICE_DRIVE(RECORD_ID,APPLY_ID,DEAD_ID,START_TIME,END_TIME)VALUES(?,?,?,?,?)";
					String sql1_18 = "INSERT INTO FIS_SERVICE_CREMATION(RECORD_ID,APPLY_ID,DEAD_ID,START_TIME,END_TIME)VALUES(?,?,?,?,?)";
					/*插入表1_11*/
					ps = conn.prepareStatement(sql1_11);
					ps.setString(1, jsonObject.getString("DEAD_ID"));
					ps.setString(2, jsonObject.getString("NAME"));
					ps.setString(3, jsonObject.getString("SEX"));
					ps.setString(4, "0");                                        //证件类型，0表示身份证
					ps.setString(5, jsonObject.getString("ID_CARD"));
					ps.setString(6, jsonObject.getString("BIRTHDAY"));
					ps.setString(7, jsonObject.getString("DEPT_CODE"));
					ps.setString(8, jsonObject.getString("MORG_AREA"));
					ps.setString(9, jsonObject.getString("POPULACE"));
					ps.setString(10, jsonObject.getString("DEATH_DATE"));
					ps.setString(11, jsonObject.getString("DEAD_PLACE"));                                     	 //去世地点
					ps.setString(12, jsonObject.getString("DEATH_CAUSE"));
					if(jsonObject.getString("DEATH_DISEASE").length()<1){
						ps.setString(13, " ");
					}else {
						ps.setString(13, jsonObject.getString("DEATH_DISEASE"));
					}
					ps.setString(14, "156");  //国籍
					ps.setString(15, "01");										 //民族
					ps.setString(16, "BZ320981-01");                             //办理单位
					ps.setString(17, "0");                                       //0,逝者类型,正常
					ps.setString(18, jsonObject.getString("DEAD_AGE"));          //死者年龄
					ps.setString(19, jsonObject.getString("START_TIME2"));       //火化时间
					ps.execute();
					/*插入表1_12*/
					ps = conn.prepareStatement(sql1_12);
					ps.setString(1, jsonObject.getString("DEAD_ID"));
					ps.setString(2, jsonObject.getString("CNAME"));
					ps.setString(3, "0");										//证件类型，0表示身份证
					ps.setString(4, jsonObject.getString("CID_CARD"));
					ps.setString(5, jsonObject.getString("CRELATION"));
					ps.setString(6, "X");                                        //承办人手机
					ps.setString(7, "X");                                        //承办人电话
					ps.setString(8, jsonObject.getString("CADDRESS"));
					ps.setString(9, "X");                                        //承办人邮编
					ps.setString(10, "X");										 //承办人工作单位CUSTOM_ID
					ps.setString(11, getUUID());
					ps.execute();
					/*插入表1_13*/
					ps = conn.prepareStatement(sql1_13);
					ps.setString(1, jsonObject.getString("APPLY_ID"));
					ps.setString(2,"B");                                          //业务类型
					ps.setString(3, jsonObject.getString("DEAD_ID"));
					ps.setString(4, "X");										  //交付单位
					ps.setString(5, jsonObject.getString("CNAME"));               //交付人(家属)
					ps.setString(6, jsonObject.getString("CONTRACT_PEOPLE"));     //接收人
					ps.setString(7, jsonObject.getString("RECEIVE_TIME"));        //接收时间
					ps.setString(8, "BZ320981-01");  							  //殡葬服务单位
					ps.setString(9, "东台市殡仪馆");                               //殡葬服务单位名称
					ps.setString(10, jsonObject.getString("CONTRACT_PEOPLE"));    //受理人员
					ps.setString(11, jsonObject.getString("CONTRACT_DATE"));      //受理日期
					ps.setString(12, "1");					  					  //是否完成
					ps.setString(13, "1");                                        //是否领灰
					ps.setString(14, jsonObject.getString("TAKEASHES_TIME"));     //领灰时间
					ps.setString(15, jsonObject.getString("FINISH_TIME"));        //完成时间
					ps.setString(16, jsonObject.getString("ASHES_GO"));           //骨灰去向
					ps.execute(); 
					/*插入表1_14*/
					ps = conn.prepareStatement(sql1_14);
					ps.setString(1, jsonObject.getString("RECORD_ID1"));          //出车内码
					ps.setString(2, jsonObject.getString("APPLY_ID"));            //业务编号
					ps.setString(3, jsonObject.getString("DEAD_ID"));
					ps.setString(4, jsonObject.getString("START_TIME"));          //开始时间
					ps.setString(5, jsonObject.getString("END_TIME"));            //结束时间
					ps.execute();
					/*插入表1_18*/
					ps = conn.prepareStatement(sql1_18);
					ps.setString(1, jsonObject.getString("RECORD_ID2"));          //火化内码
					ps.setString(2, jsonObject.getString("APPLY_ID"));            //业务编号
					ps.setString(3, jsonObject.getString("DEAD_ID"));
					ps.setString(4, jsonObject.getString("START_TIME2"));         //开始时间
					ps.setString(5, jsonObject.getString("END_TIME2"));           //结束时间
					ps.execute();
					conn.commit();                                                //提交事务
				}catch (SQLException e) {
						result = false;
						try {
							conn.rollback();                                      //事务回滚
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						e.printStackTrace();
					}
				finally {
					try {
						ps.close();
						conn.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					ps=null;
					conn=null;
				}
			}
		}
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean changeUploadStatus(String deadid){                             //改变本地表中的上传状态
		boolean result = false;
		Connection connection = DBDao.openDateBase("dongtai");
		if(connection!=null){
			String sql = "UPDATE remainsin SET uploadStatus =? WHERE deadID = ?";
			int rs = 0;
			try {
				PreparedStatement ps = connection.prepareStatement(sql); 
				ps.setString(1, "1");
				ps.setString(2, deadid);
				rs = ps.executeUpdate();
				if(rs>0){
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				connection=null;
			}
		}
		return result;
	}
	
	
	/**
	 *清空、处理远程服务器数据 ，测试用
	 */
	
	
	public void deleteData(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.0.210:1521:orcl","orcl_yc","1qaz-pl,");
			if(conn!=null){
				String sql_1 = "delete from FIS_DEAD_INFO where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement1 = conn.prepareStatement(sql_1);
				int resultSet1 = pStatement1.executeUpdate();
				String sql_2 = "delete from FIS_PERSONAL_CUSTOM where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement2 = conn.prepareStatement(sql_2);
				int resultSet2 = pStatement2.executeUpdate();
				String sql_3 = "delete from FIS_APPLY_INFO where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement3 = conn.prepareStatement(sql_3);
				int resultSet3 = pStatement3.executeUpdate();
				String sql_4 = "delete from FIS_SERVICE_DRIVE where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement4 = conn.prepareStatement(sql_4);
				int resultSet4 = pStatement4.executeUpdate();
				String sql_5= "delete from FIS_SERVICE_CREMATION where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement5 = conn.prepareStatement(sql_5);
				int resultSet5 = pStatement5.executeUpdate();
				System.out.println(resultSet1);
				System.out.println(resultSet2);
				System.out.println(resultSet3);
				System.out.println(resultSet4);
				System.out.println(resultSet5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取数据上传的结果，测试用
	 */
	
	public void testData(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.40.0.210:1521:orcl","orcl_yc","1qaz-pl,");
			if(conn!=null){
				String sql_1 = "select * from FIS_DEAD_INFO where DEAD_ID like 'SZ320981-%'";
				PreparedStatement pStatement1 = conn.prepareStatement(sql_1);
				ResultSet resultSet1 = pStatement1.executeQuery();
				while(resultSet1.next()){
					System.out.println(resultSet1.getString("DEAD_ID"));
					System.out.println(resultSet1.getString("BIRTHDAY"));
					System.out.println(resultSet1.getString("AGE"));
					System.out.println(resultSet1.getString("DEATH_CAUSE"));
					System.out.println(resultSet1.getString("DEATH_DISEASE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
