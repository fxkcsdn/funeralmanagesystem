<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0144)file://C:\Documents and Settings\Administrator\Local Settings\Temporary Internet Files\Content.IE5\8PI3C9Y7\D__EBUSIN~1_二代证~1_IDCARD~1[1].HTM -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>欢迎使用殡葬智能化系统</title>
<SCRIPT language=JavaScript>
function ReadIDCard() {
   clearForm();
   var ret = CVR_IDCard.ReadCard();
   if (ret == "0"){
      fillForm();
      return;
   } 

   alert("读卡错误,错误原因:" + ret);
}

function fillForm() {   
  var pName=CVR_IDCard.Name;
  var pCardNo=CVR_IDCard.CardNo;

  document.form1.rentNumber.value = pName; 
  document.form1.deadId.value = pCardNo;
}

function clearForm() {
  document.form1.rentNumber.value = ""; 
  document.form1.deadId.value = "";
}
</SCRIPT>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<META content="MSHTML 6.00.2900.6287" name=GENERATOR></HEAD>
<body>
<OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
<label><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">租棺管理</li>
        <li class="TabbedPanelsTab" tabindex="0">记录用棺信息</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <form name="form1" id="form1" onsubmit="return validateForm1(this);">
            <p>逝者身份证号码：
  			  <input type="text" name="deadId" id="deadId">
  			  <input type="button" name="ReadCard" id="ReadCard" value="读取身份证号码" onclick="return ReadIDCard()" style="width: 125px; ">
  			  <label for="rentNumber">棺材号：</label>
              <input type="text" name="rentNumber" id="rentNumber">
            </p>
            <p>
              <label for="memberMobile">丧属手机号码：</label>
              <input type="text" name="memberMobile" id="memberMobile">
            </p>
            <p>
              <label for="contactMobile">经办人手机号码：</label>
              <input type="text" name="contactMobile" id="contactMobile">
            </p>
            <p>
              <label for="startTime">租棺起始时间：</label>
              <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
            </p>
            <hr>
            <p>&nbsp;</p>
            <p>
              <input type="submit" name="BorrowSubmit" id="BorrowSubmit" value="提交">
            </p>
            <p>&nbsp;</p>
          </form>
        </div>
        
        <form name="form2" id="form2" onsubmit="return validateForm2(this);">
          <p>
            <label for="deadId">逝者身份证号码：</label>
            <input type="text" name="deadId" id="deadId" onkeyup="return updateData(this);">
            <label for="rentNumber">棺材号：</label>
            <input type="text" name="rentNumber" id="rentNumber">
          </p>
          <p>
            <label for="memberMobile">丧属手机号码：</label>
            <input type="text" name="memberMobile" id="memberMobile">
          </p>
          <p>
            <label for="contactMobile">经办人手机号码：</label>
            <input type="text" name="contactMobile" id="contactMobile">
          </p>
          <p>
            <label for="startTime">租棺起始时间：</label>
            <input type="text" name="startTime" id="startTime">
          </p>
          <p>
            <label for="returnTime">租棺结束时间：</label>
            <input type="text" name="returnTime" id="returnTime" onchange="return calculateRentTime()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
            <label for="crystalUsedTime">用棺总时间：</label>
            <input type="text" name="crystalUsedTime" id="crystalUsedTime">
          	<label>小时</label>
          </p>
          <hr>
          <p>&nbsp;</p>
          <p>
            <input type="submit" name="BorrowSubmit2" id="BorrowSubmit2" value="提交">
          </p>
          <p>&nbsp;</p>
        </form>
        <p>&nbsp;</p>
      </div>
</div></label>
<p>&nbsp;</p>
</body>

<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
 
 <script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
 
<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>

<script>
//验证表单1
	function validateForm1(form1)
	{
		if(form1.deadId.value == "")
		{
			alert("逝者身份证号码不能为空！");
			form1.deadId.focus();
			return false;
		}
		if(form1.startTime.value=="")
		{
			alert("租棺起始时间不能为空！");
			form1.startTime.focus();
			return false;
		}
		
		url="deadId="+form1.deadId.value+"&";
		url=url+"memberMobile="+form1.memberMobile.value+"&";
		url=url+"contactMobile="+form1.contactMobile.value+"&";
		url=url+"startTime="+form1.startTime.value+"&";
		url=url+"rentNumber="+form1.rentNumber.value;
		
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateForm1Callback;
		http_request.open('POST',"ZuJie!rentCrystal",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
	}
	
//处理表单1返回数据
	function validateForm1Callback()
	{
		if(http_request.readyState==4)
			{
				if(http_request.status==200)
					{
						var json=eval("("+http_request.responseText+")");
						json=eval("("+json+")");
						alert(json.returnString);
					}
				else
					{
						alert("您所请求的页面没有响应！");
					}
			}
	}

//验证表单2
     function validateForm2(form2)
     {
     	if(form2.deadId.value=="")
     	{
     		alert("逝者身份证号码不能为空！");
     		form2.deadId.focus();
     		return false;
     	}
     	if(form2.returnTime.value=="")
     		{
     			alert("还棺时间不能为空！");
     			form2.returnTime.focus();
     			return false;
     		}
     	url="deadId="+form2.deadId.value+"&";
     	url=url+"returnTime="+form2.returnTime.value+"&";
     	url=url+"crystalUsedTime="+form2.crystalUsedTime.value;
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateForm2Callback;
		http_request.open('POST',"ZuJie!returnCrystal",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
     }
   //处理表单2返回数据
 	function validateForm2Callback()
 	{
 		if(http_request.readyState==4)
 		{
 			if(http_request.status==200)
 				{
 					var json=eval("("+http_request.responseText+")");
					json=eval("("+json+")");
					alert(json.returnString);
 				}
 			else
 				{
 					alert("您所请求的页面没有响应");
 				}
 		}
 	}
    
    //输入身份证显示信息
    function updateData(input)
    {
    	url="deadId="+form2.deadId.value;
    	http_request=createHttpRequest();
		http_request.onreadystatechange=updateDataCallback;
		http_request.open('POST',"ZuJie!getRenterInfo",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;//阻止页面刷新
    }
     //数据显示回调函数
     function updateDataCallback()
     {
    	 if(http_request.readyState==4)
 		{
 			if(http_request.status==200)
 				{
 					var json=eval("("+http_request.responseText+")");
 					json=eval("("+json+")");
 					if(json.returnString=="1")
 						{
 							form2.memberMobile.value=json.memberMobile;
 							form2.contactMobile.value=json.contactMobile;
 							form2.startTime.value=json.startTime;
 							form2.returnTime.value=json.returnTime;
 							if(form2.returnTime.value!="")
 								{
 									var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
 									var d2=new Date((form2.returnTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
 									var d3=d2-d1;
 									var h=Math.floor(d3/3600000);
 									form2.crystalUsedTime.value=h;
 								}
 							else
 								{
 									form2.crystalUsedTime.value="";
 								}
 							form2.rentNumber.value=json.rentNumber;
 						}
 					else
 						{
 							form2.memberMobile.value="";
 							form2.contactMobile.value="";
 							form2.startTime.value="";
 							form2.returnTime.value="";
 							form2.rentNumber.value="";
 							form2.crystalUsedTime.value="";
 						}
 				}
 			else
 				{
 					alert("您所请求的页面没有响应");
 				}
 		}
     }
//计算租棺时间
	function calculateRentTime()
	{
		if(form2.startTime.value!="" && form2.returnTime.value!="")
			{
				var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
				var d2=new Date((form2.returnTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
				var d3=d2-d1;
				var h=Math.floor(d3/3600000);
				form2.crystalUsedTime.value=h;
			}
		else
			{
				form2.crystalUsedTime.value="";
			}
	}

</script>
</html>
