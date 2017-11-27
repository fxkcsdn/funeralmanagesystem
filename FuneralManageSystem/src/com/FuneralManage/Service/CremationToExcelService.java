package com.FuneralManage.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FuneralManage.Utility.CopyFile;
import com.FuneralManage.Utility.change;



public class CremationToExcelService extends BaseService{

	private JSONArray returnString;
	private  String itemName;
	private String message;
	private String memo;
	private int totalBeCost;
	private int totalRealCost;
	private int totalCost;
	 Cell cell = null;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONArray getReturnString() {
		return returnString;
	}

	public void setReturnString(JSONArray returnString) {
		this.returnString = returnString;
	}

	public JSONArray cremationToExcelDao(String startTime, String endTime)
			throws SQLException{
		Connection conn = DBDao.openDateBase("dongtai");
		
		if (conn != null) {
			System.out.println("done");
			
			String startTime1 = startTime + " 00:00:00";
			String endTime1 = endTime + " 23:59:59";

			String sql = "select deadName,deadId,address,deadSex,deadAge,deadTime,deadReason,inTime,invoiceNo,memberMobile,remainsNumber,subsidyMoney from remainsin where  inTime between ? and ? ORDER BY DATE_FORMAT (inTime,'%Y-%m-%d') ASC,remainsNumber ASC,remainsOrderNumber ASC";
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, startTime1);
				ps.setString(2, endTime1);
				rs = ps.executeQuery();
					
				JSONArray array = new JSONArray();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				while (rs.next()) {

					JSONObject jsonObj = new JSONObject();

					// 遍历每一列
					for (int i = 1; i <= columnCount; i++) {
						String columnName = metaData.getColumnLabel(i);
						String value = rs.getString(columnName);
						try {
							jsonObj.put(columnName, value);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					array.put(jsonObj);
				}
				returnString = array;

			} catch (SQLException e) {
				e.printStackTrace();
				// returnString="数据库操作失败！";

			} finally {
				rs.close();
				ps.close();
				conn.close();
			}
		}
		return returnString;
	}


	@SuppressWarnings("null")
	public String ExcelById(String deadId )throws SQLException, Exception {
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			
			totalBeCost=0;
			totalRealCost=0;
			totalCost=0;		
			PreparedStatement ps1 =null;
			PreparedStatement ps2 =null;
			PreparedStatement ps3 =null;
			PreparedStatement ps4 =null;
			ResultSet rs1=null;
			ResultSet rs2=null;
			ResultSet rs3=null;
			ResultSet rs4=null;
			String fileName= deadId+".xls";
			
//			 File file2 = new File("D:/逝者详单信息Excel表");
//		        if (file2.mkdir()) {
//		            System.out.println("单文件夹 逝者详单信息Excel表 创建成功！创建后的文件目录为：" + file2.getPath() + ",上级文件为:" + file2.getParent());
//		        }
//		        CopyFile.copyFile("C:/Users/zy/Desktop/模版.xls","D:/逝者详单信息Excel表/"+fileName);
				// 表格要导出的目录
					String outPath = "D:\\逝者详单信息Excel表\\"+fileName;
					FileOutputStream is = new FileOutputStream(outPath);
					// 模板文件路径
					File sourceXlsFile = new File("D:\\逝者详单信息Excel表\\模版.xls");
					 // 读取模板文件
		        	Workbook rwb = Workbook.getWorkbook(sourceXlsFile);//原xls文件 
		        	// 根据模板文件创建可写的文件，注意这里是createWorkbook(),创建而不是获取
		            WritableWorkbook wwb = Workbook.createWorkbook(is, rwb);//临时xls文件
		         // 注意这里是getSheet(), 通过索引，获取模板文件中的sheet页第一页
		            WritableSheet sheet = wwb.getSheet(0);//工作表  

					Label label = null;
					
					String sql1="select * from remainsin where deadID=?";
					String sql2="select urnName,urnBeCost,urnRealCost from deadchosenurn where deadID=?";
					String sql3="select goodsName,goodsBeCost,goodsRealCost from deadfuneralgoods where deadID=?";
					String sql4="select cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost from deadserviceitem where deadID=?";
					try {
//				
					ps1 = conn.prepareStatement(sql1);
					ps1.setString(1, deadId);
					ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, deadId);
					ps3 = conn.prepareStatement(sql3);
					ps3.setString(1, deadId);
					ps4 = conn.prepareStatement(sql4);
					ps4.setString(1, deadId);
					rs1 = ps1.executeQuery();
					rs2 = ps2.executeQuery();
					rs3 = ps3.executeQuery();
					rs4 = ps4.executeQuery();
				    
					Calendar a=Calendar.getInstance();
					
					int year = a.get(Calendar.YEAR);
					Date date=new Date();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=format.format(date);
					
				while (rs1.next()) {
					String deadYear1=rs1.getString("deadID").substring(6, 10);
					int deadYear=Integer.parseInt(deadYear1);
					String dealerYear1=rs1.getString("dealerId").substring(6, 10);
					int dealerYear=Integer.parseInt(dealerYear1);
					
					String age = year-deadYear+"";
					String dealerage = year-dealerYear+"";
					String deadName = rs1.getString("deadName");
					
					label = new Label(3,0, rs1.getString("inTime"));
					sheet.addCell(label);
					label = new Label(1,2, deadName);
					sheet.addCell(label);
					
					label = new Label(2,2, rs1.getString("deadSex"));
					sheet.addCell(label);
					
					label = new Label(3,2, rs1.getString("deadId"));
					sheet.addCell(label);
					
					label = new Label(4,2, rs1.getString("address"));
					sheet.addCell(label);
					
					label = new Label(5,2, rs1.getString("deadId").substring(6, 14));
					sheet.addCell(label);
										
					label = new Label(6,2, age);
					sheet.addCell(label);
					
					label = new Label(1,3, rs1.getString("dealerName"));
					sheet.addCell(label);

					label = new Label(2,3, rs1.getString("deadSex"));
					sheet.addCell(label);
					
					label = new Label(3,3, rs1.getString("dealerId"));
					sheet.addCell(label);
					
					label = new Label(4,3, rs1.getString("dealerAddress"));
					sheet.addCell(label);
					if(rs1.getString("dealerId")!=""){
						label = new Label(5,3, rs1.getString("dealerId").substring(6, 14));
						sheet.addCell(label);
					}

					label = new Label(6,3, dealerage);
					sheet.addCell(label);
					label = new Label(1,5, rs1.getString("deadTime"));
					sheet.addCell(label);
					label = new Label(3,5, rs1.getString("deadType"));
					sheet.addCell(label);
					label = new Label(5,5, rs1.getString("deadReason"));
					sheet.addCell(label);
					label = new Label(3,6, rs1.getString("deadResidence"));
					sheet.addCell(label);
					label = new Label(5,6, rs1.getString("ashesDisposition"));
					sheet.addCell(label);
					label = new Label(2,7, rs1.getString("operatorRelation"));
					sheet.addCell(label);
					label = new Label(5,7, rs1.getString("memberMobile"));
					sheet.addCell(label);
					label = new Label(1,8, rs1.getString("invoiceNo"));
					sheet.addCell(label);
					label = new Label(5,8, rs1.getString("proofUnit"));
					sheet.addCell(label);
					label = new Label(2,9, rs1.getString("subsidyNo"));
					sheet.addCell(label);
					label = new Label(5,9, rs1.getString("subsidyMoney"));
					sheet.addCell(label);
					label = new Label(2,10, rs1.getString("memo"));
					sheet.addCell(label);
					label = new Label(5,10, rs1.getString("benefitTime"));
					sheet.addCell(label);
					label = new Label(2,11, rs1.getString("directorName"));
					sheet.addCell(label);
					label = new Label(1, 16, rs1.getString("deadName"));
					sheet.addCell(label);
					label = new Label(3, 16, rs1.getString("deadSex"));
					sheet.addCell(label);
					label = new Label(5, 16, rs1.getString("deadAge"));
					sheet.addCell(label);
					label = new Label(7, 16, rs1.getString("address"));
					sheet.addCell(label);
					int no;
					if (rs1.getInt("remainsNumber")==0) {
						no=rs1.getInt("remainsOrderNumber");
					}else {
						no=rs1.getInt("remainsNumber");
					}
					label = new Label(1, 0, no+"");
					sheet.addCell(label);
					label = new Label(3, 14, no+"");
					sheet.addCell(label);
					label = new Label(9, 15, time);
					sheet.addCell(label);
					
				}
								
				while (rs2.next()) {
					
					String urnName = rs2.getString("urnName");
					int urnBeCost = rs2.getInt("urnBeCost");	
					int urnRealCost = rs2.getInt("urnRealCost");
					int temp2 = urnBeCost-urnRealCost;
					label = new Label(9, 23, urnName);
					sheet.addCell(label);
					label = new Label(10, 23, urnBeCost+"");
					sheet.addCell(label);
					if (temp2>0) {
						label = new Label(11, 23, "-"+temp2);
						sheet.addCell(label);
					}
					
					totalBeCost=totalBeCost+urnBeCost;
					totalRealCost=totalRealCost+temp2;
				}
				
			
				while (rs3.next()){
       			String value = rs3.getString("goodsName");
       			int  goodsBeCost=rs3.getInt("goodsBeCost");
       			int  goodsRealCost=rs3.getInt("goodsBeCost");
       			int  temp3 = goodsBeCost-goodsRealCost;
       				totalBeCost=totalBeCost+goodsBeCost;
       				totalRealCost=totalRealCost+temp3;
        			for (int j = 18; j < 27; j++) {   
        				        
        				         for(int k=0;k<12;k++){
        				        	 Cell cell = sheet.getCell(k, j);
        				             if (cell.getContents()!="") {
        				            	 if (value.equals(cell.getContents().toString())) {
            				        		 label = new Label(k+2, j, rs3.getString("goodsBeCost"));
            				        		 sheet.addCell(label);
            				        		 if (temp3>0) {
            				        			 label = new Label(k+3, j, temp3+"");
                				        		 sheet.addCell(label);
               				        		 
											}
            				        		 break;
            				        	 }
									}
        				        	 
        				         }
        				                 
        				                 }   
        				             }
                                                   		            								

				while (rs4.next()) {
					String cremationTypeNo = rs4.getString("cremationTypeNo");		               
					String cremationItemNo = rs4.getString("cremationItemNo");
					int itemBeCost = rs4.getInt("itemBeCost");		               
					int itemRealCost = rs4.getInt("itemRealCost");
					int temp1=itemBeCost-itemRealCost;
						totalBeCost=totalBeCost+itemBeCost;
						totalRealCost=totalRealCost+temp1;
					
		            String itemName = getTypeNumber(cremationTypeNo,cremationItemNo);
		            
		            if (cremationTypeNo.equals("01")) {
		            	   label = new Label(1, 20, itemName);
			               sheet.addCell(label);
			               label = new Label(2, 20, itemBeCost+"");
			               sheet.addCell(label);
			               if (temp1>0) {
			            	   label = new Label(3, 20, "-"+temp1);
				               sheet.addCell(label);
						}
			               
					   }
		            if (cremationTypeNo.equals("02")){
						label = new Label(1, 21, itemName);
			            sheet.addCell(label);
			            label = new Label(2, 21, itemBeCost+"");
			            sheet.addCell(label);
			            if (temp1>0) {
			            	label = new Label(3, 21, "-"+temp1);
				            sheet.addCell(label);
						}
			            
						
					 }
		            if (cremationTypeNo.equals("03")) {
						label = new Label(1, 18, itemName);
			            sheet.addCell(label);
			            label = new Label(2, 18, itemBeCost+"");
			            sheet.addCell(label);
			            if (temp1>0) {
			            	label = new Label(3, 18, "-"+temp1);
				            sheet.addCell(label);
						}
			            
					}
			
				}
				totalCost=totalBeCost-totalRealCost;
				String costChinese = change.NumberToChn(totalCost);
				label = new Label(1, 27, totalBeCost+"");
	            sheet.addCell(label);
	            label = new Label(5, 27, totalRealCost+"");
	            sheet.addCell(label);
	            label = new Label(1, 28, totalCost+"");
	            sheet.addCell(label);
	            label = new Label(5, 28, costChinese+"");
	            sheet.addCell(label);
				wwb.write();
				wwb.close();
				memo="成功生成逝者excel";
				
			} catch (Exception e) {
				e.printStackTrace();
				memo="生成逝者excel失败！";

			} finally {
				rwb.close();
				rs1.close();
				rs2.close();
				rs3.close();
				rs4.close();
				ps1.close();
				ps2.close();
				ps3.close();
				ps4.close();
				conn.close();
			
					
					
			}			
		}
		return memo;
	}
	public  String getTypeNumber(String typeNo,String itemNo){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select itemName from cremationserviceitem where typeNo=? and itemNo=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, typeNo);
				ps.setString(2, itemNo);
				
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					itemName=rs.getString("itemName");
				}
				else{
					itemName="";
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				message="获取火化服务名失败！";
				return message;
			}
		}
		return itemName;
	}	

}


