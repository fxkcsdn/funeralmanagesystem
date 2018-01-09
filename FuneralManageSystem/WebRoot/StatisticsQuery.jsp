<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用殡葬智能化系统</title>

<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<style type="text/css">
body, {
	font-size:20px;
}
td,th{
	font-size: 14px;
}
</style>

</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
 	 <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.filePhoto.location.reload();">拍照</li>
    <li class="TabbedPanelsTab" tabindex="0">查询遗体基本信息</li>
    <li class="TabbedPanelsTab" tabindex="0">查询惠民补助信息</li>
    <li class="TabbedPanelsTab" tabindex="0">生成火化信息表格</li>
    
    
  </ul>
  <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent">
    <iframe id="filePhoto" name="filePhoto" src="testPhoto.htm" width="100%" height="90%">
    
    </iframe> 
    </div>
    <div class="TabbedPanelsContent">
            <form name="form1" >
            <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">开始时间：</label>
              <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">结束时间：</label>
              <input type="text" name="endTime" id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
<input type="button" value="查询"  onclick="return CremationInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="printServiceList" id="printServiceList" value="打印火化信息表" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="另存为 Excel" onclick="CremationToExcel()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


           
            <hr>
  <center>在此期间共有<input type="text" id="asd">条数据</center>
   <br> 
   <div id="tableExcel" style="display:none">
   
   <table id="cremationInfo" border="1" align="center">
   
   <thead>   
          
            <tr>
            <th colspan="12" align="center"><b><font style="font-size:24px">东台市殡仪馆火化基本信息统计表</font></b>(东台分馆)</th> 
            </tr>
            <tr>
            <th>火化日期</th> 
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>民族</th>
            <th>住址</th>
            <th>身份证号</th>
            
            <th>死亡日期</th>
            <th>死亡原因</th>
            <th>家属电话</th>
            <th>发票号</th>
            
            
            </tr>            
            </thead>
            <tbody id="cremationInfo1">
            
            
            
            </tbody>
  </table> 
   
   </div>
   <div id="divNumber" class="pagination"></div>
       
            
          
        
	
</form>
    </div>
    <div class="TabbedPanelsContent">
                  <form name="form2" >
            <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">开始时间：</label>
              <input type="text" name="startTime1" id="startTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">结束时间：</label>
              <input type="text" name="endTime1" id="endTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
<input type="button" value="查询"  onclick="return BenefitInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="printServiceList" id="printServiceList" value="打印惠民补助" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="另存为 Excel" onclick="BenefitToExcel()"> 

           
            <hr>
    <center>在此期间共有<input type="text" id="qaq">条数据</center>
    <br>
   <div id="tableExcel2"  style="display:none">
   
   <table id="benefitInfo" border="1" align="center" >
   
   <thead>   
          
            <tr>
            <th colspan="14" align="center"><b><font style="font-size:24px">东台市殡仪馆惠民补助信息统计表</font></b>(东台分馆)</th> 
            </tr>
            <tr>
            <th>火化时间</th> 
            
            <th>死者姓名</th>
            <th>性别</th>
            <th>年龄</th>
            
            <th>住址</th>
            <th>身份证号</th>
            <th>补贴金额</th>
            <th>死亡日期</th>
            <th>死亡原因</th>
            <th>家属电话</th>
            <th>发票号码</th>
            <th>凭证号</th>
            <th>办理时间</th>
            
            
            </tr>            
            </thead>
            <tbody id="benefitInfo1">
            
            
            
            </tbody>
  </table> 
   
   </div>
   <div id="divNumber1" class="pagination"></div>
       
            
          
        
	
</form>
      <p>&nbsp;</p>
    </div>
    <div class="TabbedPanelsContent">
            <form name="form3" >
  <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">开始时间：</label>
              <input type="text" name="startTime2" id="startTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">结束时间：</label>
              <input type="text" name="endTime2" id="endTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
       <input type="button" value="查询"  onclick="return personalInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="导出详单" onclick="ExcelById()">
       <div id="personalExcel" style="display:none">
   
   <table id="personalInfo" border="1" align="center">
   
   <thead>   
          
            <tr>
            <th colspan="12" align="center"><b><font style="font-size:24px">东台市殡仪馆火化基本信息统计表</font></b>(东台分馆)</th> 
            </tr>
            <tr>
            <th>火化日期</th> 
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            
            <th>住址</th>
            <th>身份证号</th>
            
            <th>死亡日期</th>
            <th>死亡原因</th>
            <th>家属电话</th>
            <th>发票号</th>
            <th>选择</th>
            
            
            </tr>            
            </thead>
            <tbody id="cremationInfo1">
            
            
            
            </tbody>
  </table> 
   <input type="button" value="全选" onclick="return selectAll()"/>
   <input type="button" value="取消" onclick="return cancelAll()"/>
   </div>
   
   <div id="divNumber2" class="pagination"></div>
       
                     	
</form>
    </div>

  </div>
  
</div>
<script>
	window.onload = initialization;
	function initialization() //页面加载
	{
		var myDate = new Date();
		var myYear = myDate.getFullYear();
		var myDay;
		var myMonth;
		var TmpMonth = myDate.getMonth()+1;
		if(TmpMonth<10)    //原生日历控件要求显示格式为"2016-02-04"，此处对所取的月份进行格式匹配
		{
			myMonth = "0"+TmpMonth;
		}
		else
		{
			myMonth = TmpMonth;
		}
		var TmpDay = myDate.getDate();
		if(TmpDay<10)     //原生日历控件要求显示格式为"2016-02-04"，此处对所取的日期进行格式匹配
		{
			myDay = "0"+TmpDay;
		}
		else
		{
			myDay = TmpDay;
		}
		document.getElementById("startTime").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("endTime").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("startTime1").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("endTime1").value=myYear+"-"+myMonth+"-"+myDay;
				document.getElementById("startTime2").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("endTime2").value=myYear+"-"+myMonth+"-"+myDay;
	}</script>
<script src="js/InfoQuery.js" type="text/javascript"></script>
<script src="js/paging.js"    type="text/javascript"></script>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>

 <script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
 
 <script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
</body>
</html>
