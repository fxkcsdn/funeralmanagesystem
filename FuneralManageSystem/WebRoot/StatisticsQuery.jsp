<%@ page language="java" contentType="text/html;charset=gbk"
	pageEncoding="GBK"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbK">
<title>��ӭʹ���������ܻ�ϵͳ</title>

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
    <li class="TabbedPanelsTab" tabindex="0">��ѯ���������Ϣ</li>
    <li class="TabbedPanelsTab" tabindex="0">��ѯ��������Ϣ</li>
    <li class="TabbedPanelsTab" tabindex="0">���ɻ���Ϣ���</li>
    
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
            <form name="form1" >
            <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">��ʼʱ�䣺</label>
              <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">����ʱ�䣺</label>
              <input type="text" name="endTime" id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
<input type="button" value="��ѯ"  onclick="return CremationInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="printServiceList" id="printServiceList" value="��ӡ����Ϣ��" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="���Ϊ Excel" onclick="CremationToExcel()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


           
            <hr>
  <center>�ڴ��ڼ乲��<input type="text" id="asd">������</center>
   <br> 
   <div id="tableExcel" style="display:none">
   
   <table id="cremationInfo" border="1" align="center">
   
   <thead>   
          
            <tr>
            <th colspan="12" align="center"><b><font style="font-size:24px">��̨�����ǹݻ𻯻�����Ϣͳ�Ʊ�</font></b>(��̨�ֹ�)</th> 
            </tr>
            <tr>
            <th>������</th> 
            <th>���</th>
            <th>����</th>
            <th>�Ա�</th>
            <th>����</th>
            <th>����</th>
            <th>סַ</th>
            <th>���֤��</th>
            
            <th>��������</th>
            <th>����ԭ��</th>
            <th>�����绰</th>
            <th>��Ʊ��</th>
            
            
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
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">��ʼʱ�䣺</label>
              <input type="text" name="startTime1" id="startTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">����ʱ�䣺</label>
              <input type="text" name="endTime1" id="endTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
<input type="button" value="��ѯ"  onclick="return BenefitInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="printServiceList" id="printServiceList" value="��ӡ������" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="���Ϊ Excel" onclick="BenefitToExcel()"> 

           
            <hr>
    <center>�ڴ��ڼ乲��<input type="text" id="qaq">������</center>
    <br>
   <div id="tableExcel2"  style="display:none">
   
   <table id="benefitInfo" border="1" align="center" >
   
   <thead>   
          
            <tr>
            <th colspan="14" align="center"><b><font style="font-size:24px">��̨�����ǹݻ�������Ϣͳ�Ʊ�</font></b>(��̨�ֹ�)</th> 
            </tr>
            <tr>
            <th>��ʱ��</th> 
            
            <th>��������</th>
            <th>�Ա�</th>
            <th>����</th>
            
            <th>סַ</th>
            <th>���֤��</th>
            <th>�������</th>
            <th>��������</th>
            <th>����ԭ��</th>
            <th>�����绰</th>
            <th>��Ʊ����</th>
            <th>ƾ֤��</th>
            <th>����ʱ��</th>
            
            
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
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="startTime">��ʼʱ�䣺</label>
              <input type="text" name="startTime2" id="startTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">����ʱ�䣺</label>
              <input type="text" name="endTime2" id="endTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            </p>
       <input type="button" value="��ѯ"  onclick="return personalInfoQuery()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="�����굥" onclick="ExcelById()">
       <div id="personalExcel" style="display:none">
   
   <table id="personalInfo" border="1" align="center">
   
   <thead>   
          
            <tr>
            <th colspan="12" align="center"><b><font style="font-size:24px">��̨�����ǹݻ𻯻�����Ϣͳ�Ʊ�</font></b>(��̨�ֹ�)</th> 
            </tr>
            <tr>
            <th>������</th> 
            <th>���</th>
            <th>����</th>
            <th>�Ա�</th>
            <th>����</th>
            
            <th>סַ</th>
            <th>���֤��</th>
            
            <th>��������</th>
            <th>����ԭ��</th>
            <th>�����绰</th>
            <th>��Ʊ��</th>
            <th>ѡ��</th>
            
            
            </tr>            
            </thead>
            <tbody id="cremationInfo1">
            
            
            
            </tbody>
  </table> 
   <input type="button" value="ȫѡ" onclick="return selectAll()"/>
   <input type="button" value="ȡ��" onclick="return cancelAll()"/>
   </div>
   
   <div id="divNumber2" class="pagination"></div>
       
            
          
        
	
</form>
    </div>
  </div>
</div>
<script>
	window.onload = initialization;
	function initialization() //ҳ�����
	{
		var myDate = new Date();
		var myYear = myDate.getFullYear();
		var myDay;
		var myMonth;
		var TmpMonth = myDate.getMonth()+1;
		if(TmpMonth<10)    //ԭ�������ؼ�Ҫ����ʾ��ʽΪ"2016-02-04"���˴�����ȡ���·ݽ��и�ʽƥ��
		{
			myMonth = "0"+TmpMonth;
		}
		else
		{
			myMonth = TmpMonth;
		}
		var TmpDay = myDate.getDate();
		if(TmpDay<10)     //ԭ�������ؼ�Ҫ����ʾ��ʽΪ"2016-02-04"���˴�����ȡ�����ڽ��и�ʽƥ��
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
	//����һ��ʱ��ؼ�
 </script>
 
 <script src="js/createHttpRequest.js">
	//����XMLHttpRequest����
</script>
</body>
</html>
