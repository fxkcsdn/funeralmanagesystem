package com.FuneralManage.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class CremationToExcelService extends BaseService{

	private JSONArray returnString;
	private  String itemName;
	private String message;
	private String memo;

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


	public String ExcelById(String deadId )throws SQLException, Exception {
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
					
			PreparedStatement ps1 =null;
			PreparedStatement ps2 =null;
			PreparedStatement ps3 =null;
			PreparedStatement ps4 =null;
			ResultSet rs1=null;
			ResultSet rs2=null;
			ResultSet rs3=null;
			ResultSet rs4=null;
			String fileName= deadId+".xls";
			 File file2 = new File("D:/逝者详单信息Excel表");
		        if (file2.mkdir()) {
		            System.out.println("单文件夹 逝者详单信息Excel表 创建成功！创建后的文件目录为：" + file2.getPath() + ",上级文件为:" + file2.getParent());
		        }
			File file = new File("D:/逝者详单信息Excel表",fileName);
	//		File file = new File(fileName);
						
			try {
	            if (file.createNewFile()) {
	                System.out.println("多级层文件夹下文件创建成功！创建了的文件为:" + file.getAbsolutePath() + ",上级文件为:" + file.getParent());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			Label label = null;
			String sql1="select * from remainsin where deadID=?";
			String sql2="select urnName from deadchosenurn where deadID=?";
			String sql3="select goodsName from deadfuneralgoods where deadID=?";
			String sql4="select cremationTypeNo,cremationItemNo from deadserviceitem where deadID=?";
			try {
				
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
					//设定单元格高度与宽度 设定第一行高度200 设定第一列宽度30
//					sheet.setRowView(0, 2000);
//					sheet.setColumnView(0, 30); 
					//合并单元格       
					sheet.mergeCells(0, 0, 7, 0);
					//指定字体样式：字体TIMES,字号16,加粗显示。
					//WritableFont()属性参数
					//字体 WritableFont.TIMES
					//大小 18
					//是否为粗体 WritableFont.BOLD WritableFont.NO_BOLD
					//是否为斜体 true
					//样式 UnderlineStyle.NO_UNDERLINE 下划线
					//颜色 jxl.format.Colour.RED 字体颜色为红色
				    
					WritableFont font1 = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);
				    WritableCellFormat wc = new WritableCellFormat(font1); 
			        // 设置居中 
			        wc.setAlignment(Alignment.CENTRE); 
			        // 设置边框线 
			        wc.setBorder(Border.ALL, BorderLineStyle.THIN); 
			        // 设置单元格的背景颜色 
			        wc.setBackground(jxl.format.Colour.BLACK); 
			        label = new Label(0, 0, "东台市殡仪馆逝者信息表", wc);
					sheet.addCell(label);
					Calendar a=Calendar.getInstance();
					int year = a.get(Calendar.YEAR);
					
				while (rs1.next()) {
					String deadYear1=rs1.getString("deadID").substring(6, 10);
					int deadYear=Integer.parseInt(deadYear1);
					String dealerYear1=rs1.getString("dealerId").substring(6, 10);
					int dealerYear=Integer.parseInt(dealerYear1);
					System.err.println();
					String age = year-deadYear+"";
					String dealerage = year-dealerYear+"";
					String deadName = rs1.getString("deadName");
					label = new Label(1, 1, "姓名");
					sheet.addCell(label);
					label = new Label(1,2, deadName);
					sheet.addCell(label);
					label = new Label(2, 1, "性别");
					sheet.addCell(label);
					label = new Label(2,2, rs1.getString("deadSex"));
					sheet.addCell(label);
					sheet.mergeCells(3, 1, 4, 1);
					label = new Label(3, 1, "身份证号");
					sheet.addCell(label);
					sheet.mergeCells(3, 2, 4, 2);
					label = new Label(3,2, rs1.getString("deadId"));
					sheet.addCell(label);
					sheet.mergeCells(5, 1, 6, 1);
					label = new Label(5, 1, "住址");
					sheet.addCell(label);
					sheet.mergeCells(5, 2, 6, 2);
					label = new Label(5,2, rs1.getString("address"));
					sheet.addCell(label);
					label = new Label(7, 1, "出生日期");
					sheet.addCell(label);
					label = new Label(7,2, rs1.getString("deadId").substring(6, 14));
					sheet.addCell(label);
					label = new Label(8, 1,"年龄" );
					sheet.addCell(label);					
					label = new Label(8,2, age);
					sheet.addCell(label);
					label = new Label(0, 2, "逝者");
					sheet.addCell(label);
					label = new Label(0, 3, "领取人");
					sheet.addCell(label);

					label = new Label(1,3, rs1.getString("dealerName"));
					sheet.addCell(label);

					label = new Label(2,3, rs1.getString("deadSex"));
					sheet.addCell(label);
					sheet.mergeCells(3, 3, 4, 3);
					label = new Label(3,3, rs1.getString("dealerId"));
					sheet.addCell(label);
					sheet.mergeCells(5, 3, 6, 3);
					label = new Label(5,3, rs1.getString("dealerAddress"));
					sheet.addCell(label);
					if(rs1.getString("dealerId")!=""){
						label = new Label(7,3, rs1.getString("dealerId").substring(6, 14));
						sheet.addCell(label);
					}
					
					
					label = new Label(8,3, dealerage);
					sheet.addCell(label);
					label = new Label(0,4, "发票号");
					sheet.addCell(label);
					sheet.mergeCells(1, 4, 2, 4);
					label = new Label(1,4, rs1.getString("invoiceNo"));
					sheet.addCell(label);
					sheet.mergeCells(3, 4, 4, 4);
					label = new Label(3,4, "补助金额");
					sheet.addCell(label);
					sheet.mergeCells(5, 4, 6, 4);
					label = new Label(5,4, rs1.getString("subsidyMoney"));
					sheet.addCell(label);
					
					label = new Label(0,5, "死亡日期");
					sheet.addCell(label);
					sheet.mergeCells(1,5, 2, 5);
					label = new Label(1,5, rs1.getString("deadTime"));
					sheet.addCell(label);					
					sheet.mergeCells(3,5, 4, 5);
					label = new Label(3,5, "惠民补贴凭证号");
					sheet.addCell(label);
					sheet.mergeCells(5,5, 6, 5);
					label = new Label(5,5, rs1.getString("subsidyNo"));
					sheet.addCell(label);
					label = new Label(0,6, "死亡原因");
					sheet.addCell(label);
					sheet.mergeCells(1,6, 2, 6);
					label = new Label(1,6, rs1.getString("deadReason"));
					sheet.addCell(label);
					
					sheet.mergeCells(3,6, 4, 6);
					label = new Label(3,5, "亲属电话号码");
					sheet.addCell(label);
					sheet.mergeCells(5,6, 6, 6);
					label = new Label(5,5, rs1.getString("memberMobile"));
					sheet.addCell(label);
					
					sheet.mergeCells(3,7, 4, 7);
					label = new Label(3,7, "备注");
					sheet.addCell(label);
					sheet.mergeCells(5,7, 6, 7);
					label = new Label(5,7, rs1.getString("memo"));
					sheet.addCell(label);
					label = new Label(0,8, "承办人");
					sheet.addCell(label);
					sheet.mergeCells(1,8, 2, 8);
					label = new Label(1,8, rs1.getString("directorName"));
					sheet.addCell(label);
												
				}
								
				while (rs2.next()) {
					
					String urnName = rs2.getString("urnName");
					label = new Label(0, 7, "骨灰盒");
					sheet.addCell(label);
					label = new Label(1, 7, urnName);
					sheet.addCell(label);				
				}
					label = new Label(0, 9, "丧葬用品");
					sheet.addCell(label);
				rs3.last(); //移到最后一行

				int rowCount = rs3.getRow();//得到当前行号，也就是记录数

				rs3.beforeFirst();
						
		        int count =rowCount/7+1;		            											                       
		            
	            for (int q = 0; q < count; q++) {													
	            	for (int p = 0; p < 7; p++) {
	            		if (rs3.next()){
	            			String value = rs3.getString("goodsName");
	            			label = new Label(p, q+10, value);
	                    	sheet.addCell(label);
                
	                    	 }
	                     }
	                 }
		                                                     		            								
				sheet.mergeCells(0,15, 1, 15);
				label = new Label(0, 15, "服务项目");
				sheet.addCell(label);
				sheet.mergeCells(1,16, 2, 16);
				label = new Label(1, 16, "整容");
				sheet.addCell(label);
				sheet.mergeCells(3,16, 4, 16);
				label = new Label(3, 16, "告别");
				sheet.addCell(label);
				sheet.mergeCells(5,16, 6, 16);
				label = new Label(5, 16, "火化炉");
				sheet.addCell(label);
				sheet.mergeCells(1,17, 2, 17);
				sheet.mergeCells(3,17, 4, 17);
				sheet.mergeCells(5,17, 6, 17);
				while (rs4.next()) {
					String cremationTypeNo = rs4.getString("cremationTypeNo");		               
					String cremationItemNo = rs4.getString("cremationItemNo");
		            String itemName =   getTypeNumber(cremationTypeNo,cremationItemNo);
		            if (cremationTypeNo.equals("01")) {
		            	   label = new Label(1, 17, itemName);
			               sheet.addCell(label);
					   }
		            if (cremationTypeNo.equals("02")){
						label = new Label(3, 17, itemName);
			               sheet.addCell(label);
						
					 }
		            if (cremationTypeNo.equals("03")) {
						label = new Label(5, 17, itemName);
			               sheet.addCell(label);						
					}
			
				}
				workbook.write();
				workbook.close();
				memo="成功生成逝者excel";
			} catch (Exception e) {
				e.printStackTrace();
				memo="生成逝者excel失败！";

			} finally {
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


