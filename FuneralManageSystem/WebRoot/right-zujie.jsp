<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0144)file://C:\Documents and Settings\Administrator\Local Settings\Temporary Internet Files\Content.IE5\8PI3C9Y7\D__EBUSIN~1_����֤~1_IDCARD~1[1].HTM -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>��ӭʹ���������ܻ�ϵͳ</title>
<SCRIPT language=JavaScript>
function ReadIDCard() {
   clearForm();
   var ret = CVR_IDCard.ReadCard();
   if (ret == "0"){
      fillForm();
      return;
   } 

   alert("��������,����ԭ��:" + ret);
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
        <li class="TabbedPanelsTab" tabindex="0">��׹���</li>
        <li class="TabbedPanelsTab" tabindex="0">��¼�ù���Ϣ</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <form name="form1" id="form1" onsubmit="return validateForm1(this);">
            <p>�������֤���룺
  			  <input type="text" name="deadId" id="deadId">
  			  <input type="button" name="ReadCard" id="ReadCard" value="��ȡ���֤����" onclick="return ReadIDCard()" style="width: 125px; ">
  			  <label for="rentNumber">�ײĺţ�</label>
              <input type="text" name="rentNumber" id="rentNumber">
            </p>
            <p>
              <label for="memberMobile">ɥ���ֻ����룺</label>
              <input type="text" name="memberMobile" id="memberMobile">
            </p>
            <p>
              <label for="contactMobile">�������ֻ����룺</label>
              <input type="text" name="contactMobile" id="contactMobile">
            </p>
            <p>
              <label for="startTime">�����ʼʱ�䣺</label>
              <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
            </p>
            <hr>
            <p>&nbsp;</p>
            <p>
              <input type="submit" name="BorrowSubmit" id="BorrowSubmit" value="�ύ">
            </p>
            <p>&nbsp;</p>
          </form>
        </div>
        
        <form name="form2" id="form2" onsubmit="return validateForm2(this);">
          <p>
            <label for="deadId">�������֤���룺</label>
            <input type="text" name="deadId" id="deadId" onkeyup="return updateData(this);">
            <label for="rentNumber">�ײĺţ�</label>
            <input type="text" name="rentNumber" id="rentNumber">
          </p>
          <p>
            <label for="memberMobile">ɥ���ֻ����룺</label>
            <input type="text" name="memberMobile" id="memberMobile">
          </p>
          <p>
            <label for="contactMobile">�������ֻ����룺</label>
            <input type="text" name="contactMobile" id="contactMobile">
          </p>
          <p>
            <label for="startTime">�����ʼʱ�䣺</label>
            <input type="text" name="startTime" id="startTime">
          </p>
          <p>
            <label for="returnTime">��׽���ʱ�䣺</label>
            <input type="text" name="returnTime" id="returnTime" onchange="return calculateRentTime()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
            <label for="crystalUsedTime">�ù���ʱ�䣺</label>
            <input type="text" name="crystalUsedTime" id="crystalUsedTime">
          	<label>Сʱ</label>
          </p>
          <hr>
          <p>&nbsp;</p>
          <p>
            <input type="submit" name="BorrowSubmit2" id="BorrowSubmit2" value="�ύ">
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
	//����һ��ʱ��ؼ�
 </script>
 
<script src="js/createHttpRequest.js">
	//����XMLHttpRequest����
</script>

<script>
//��֤��1
	function validateForm1(form1)
	{
		if(form1.deadId.value == "")
		{
			alert("�������֤���벻��Ϊ�գ�");
			form1.deadId.focus();
			return false;
		}
		if(form1.startTime.value=="")
		{
			alert("�����ʼʱ�䲻��Ϊ�գ�");
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
	
//�����1��������
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
						alert("���������ҳ��û����Ӧ��");
					}
			}
	}

//��֤��2
     function validateForm2(form2)
     {
     	if(form2.deadId.value=="")
     	{
     		alert("�������֤���벻��Ϊ�գ�");
     		form2.deadId.focus();
     		return false;
     	}
     	if(form2.returnTime.value=="")
     		{
     			alert("����ʱ�䲻��Ϊ�գ�");
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
   //�����2��������
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
 					alert("���������ҳ��û����Ӧ");
 				}
 		}
 	}
    
    //�������֤��ʾ��Ϣ
    function updateData(input)
    {
    	url="deadId="+form2.deadId.value;
    	http_request=createHttpRequest();
		http_request.onreadystatechange=updateDataCallback;
		http_request.open('POST',"ZuJie!getRenterInfo",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;//��ֹҳ��ˢ��
    }
     //������ʾ�ص�����
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
 									var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//ת����javascript�ı�׼ʱ��
 									var d2=new Date((form2.returnTime.value+":00").replace(/-/g,"/"));//ת����javascript�ı�׼ʱ��
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
 					alert("���������ҳ��û����Ӧ");
 				}
 		}
     }
//�������ʱ��
	function calculateRentTime()
	{
		if(form2.startTime.value!="" && form2.returnTime.value!="")
			{
				var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//ת����javascript�ı�׼ʱ��
				var d2=new Date((form2.returnTime.value+":00").replace(/-/g,"/"));//ת����javascript�ı�׼ʱ��
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
