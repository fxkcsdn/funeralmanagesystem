<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!-- saved from url=(0144)file://C:\Documents and Settings\Administrator\Local Settings\Temporary Internet Files\Content.IE5\8PI3C9Y7\D__EBUSIN~1_����֤~1_IDCARD~1[1].HTM -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��ӭʹ���������ܻ�ϵͳ</title>
<script type="text/javascript" src="js/right-zhenghuo.js" charset="UTF-8">
</script>
<script src="js/My97DatePicker/WdatePicker.js">
	//����һ��ʱ��ؼ�
</script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
 <script>
    	window.onload=initialization;
    	
	    function initialization()
	    {
	       
	       
	       
	       var url = location.search; //��ȡurl��"?"������ִ�
	      
	       if(url.indexOf("?")!=-1){
	       		var str=url.substr(1);         
	       		var first = url.indexOf("=");
	       		var second = url.indexOf("&");
	       		var last = url.lastIndexOf("=");
	       		var deadId=url.substring(first+1, second);
	       		var index = url.substring(last+1);
	       		
	       		if(index!="1"){	       		
	       		document.getElementById("printDeadInfoPage").click();
	       		document.form2.deadId.value=deadId;
	       		document.form2.deadId.onchange();
	       		
	       		}
	       		
	       		    		
	       }
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
		remainsInfo();
	       http_request=createHttpRequest();
			http_request.onreadystatechange=showAllFuneralGoodsCallBack;
			http_request.open('POST',"RegisterServiceAction!showAllFuneralGoods",false);
			http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			http_request.send(null);
			
			http_request=createHttpRequest();
			http_request.onreadystatechange=showUrnCallBack;
			http_request.open('POST',"RegisterServiceAction!showUrn",false);
			http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			http_request.send(null);
			
			http_request=createHttpRequest();
			http_request.onreadystatechange=getLeaveRoomCallBack;
			http_request.open('POST',"RegisterServiceAction!getLeaveRoom",false);
			http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			http_request.send(null);
			
			http_request=createHttpRequest();
			http_request.onreadystatechange=getSetServiceCallBack;
			http_request.open('POST',"RegisterServiceAction!getSetService",false);
			http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			http_request.send(null);
			return false;
	    }
    </script>
</head>
<META content="MSHTML 6.00.2900.6287" name=GENERATOR></HEAD>
<body>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
<embed id="LODOP_EM" type="application/x-print-lodop" witdh=0 heigth=0></embed>
</object>
<OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
<div id="TabbedPanels1" class="TabbedPanels">
	  <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0" id="registerDeadInfo">�ǼǹҺ���Ϣ</li>
        <li class="TabbedPanelsTab" tabindex="0" id="printDeadInfoPage">��ӡ������Ϣ</li>
        <li class="TabbedPanelsTab" tabindex="0" id="serviceRegisterPage">�Ǽ�ҵ����Ϣ</li>
        <li class="TabbedPanelsTab" tabindex="0" id="uodateDeadId">�޸��������֤��</li>
	  </ul>
	  <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
          <form name="form1" id="form1" onsubmit="return validateForm1(this);">
            <p>
              <label for="deadId">�������֤���룺</label>
              <input type="text" name="deadInfo.deadId" id="deadId">
              <input type="button" name="ReadCard" id="ReadCard" value="��ȡ���֤����" onclick="return ReadIDCard()" style="width: 125px; ">
              <label for="deadName" style="margin-left:85px">����������</label>
              <input style="margin-left:50px" type="text" name="deadInfo.deadName" id="deadName">&nbsp;&nbsp;&nbsp;
            </p>
            <p>
              
              <label for="deadAge">�������䣺</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="text" name="deadInfo.deadAge" id="deadAge">
              <label for="deadSex" style="margin-left:220px">�����Ա�</label>
              <select name="deadInfo.deadSex" id="deadSex" style="margin-left:50px">
              	<option selected>��ѡ��...</option>
                <option>��</option>
                <option>Ů</option>
              </select>
              </p>
            <p>
              <label for="inTime" >�������ʱ�䣺</label>
              <input style="margin-left:25px" type="text" name="deadInfo.inTime" id="inTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
            
                               
              <label for="deadAddress" style="margin-left:225px">���߼�ͥ��ַ��</label>
              <input type="text" name="deadInfo.deadAddress" id="deadAddress" >
              </p>
            <p>             
               <label for="memberMobile">ɥ���ֻ����룺</label>
              <input style="margin-left:25px" type="text" name="deadInfo.memberMobile" id="memberMobile">
              
              <label for="deadExtraInfo" style="margin-left:220px">��ע��</label>
              <input type="text" name="deadInfo.deadExtraInfo" id="deadExtraInfo" style="margin-left:100px">
            </p>

            <p>
             &nbsp;&nbsp;<input type="submit" name="Submit5" id="Submit5" value="�ύ">&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="printDeadInfo" id="printDeadInfo" disabled="true" value="��ӡ������Ϣ" onclick="return goToPrintDeadInfo()">&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="toReturnCoffin" id="toReturnCoffin" value="���뻹��" onclick="return goToReturnCoffin();">
            </p>
            <p>&nbsp;</p>
          </form>
          
        </div>
        <div class="TabbedPanelsContent">
          <form name="form2" method="post" action="">
            <p>
              <label for="deadId">�������֤���룺</label>
              <input type="text" name="deadId" id="deadId" onchange="return updateData(this)">&nbsp;&nbsp;&nbsp;
              <input type="button" name="ReadCard" id="ReadCard" value="��ȡ���֤����" onclick="return ReadIDCardForQRCode()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
            </p>
            <p>
              <label for="deadName">����������</label>
              <input type="text" name="deadName" id="deadName" disabled="true" style="margin-left:70px">&nbsp;&nbsp;&nbsp;
              <label for="deadNumber">�����ţ�</label>
              <input type="text" name="deadNumber" id="deadNumber" disabled="true" style="margin-left:45px">
            </p>
            <p>
              <label for="memberMobile">ɥ���ֻ����룺</label>
              <input type="text" name="memberMobile" id="memberMobile" disabled="true" style="margin-left:20px">&nbsp;&nbsp;&nbsp;
              <label for="inTime">�������ʱ�䣺</label>
              <input type="text" name="inTime" id="inTime" disabled="true">&nbsp;&nbsp;&nbsp;
            </p>
            <p>
         <% /*      <input type="button" name="produceQRCode" id="produceQRCode" value="���������ά��" onclick="return produceCodeCallback()" style="width: 124px; "> */%>
              <input type="button" name="printQRCode" id="printQRCode" value="��ӡ�����ά��" onclick="return printCodeImage()" style="width: 124px; ">&nbsp;&nbsp;
          <% /*     <input type="button" name="produceFamilyCode" id="produceFamilyCode" value="���ɼ�����ά��" onclick="return produceFamilyCodeCallback()" style="width: 124px; ">*/ %>&nbsp;&nbsp;
              <input type="button" name="printFamilyCode" id="printFamilyCode" value="��ӡ������ά��" onclick="return familyOutPut()" style="width: 124px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="gotoRegisterService" id="gotoRegisterService" value="����ҵ��Ǽ�" onclick="return gotoRegisterServicePage()" style="width: 124px; ">
            </p>
            <!--startprint-->
            <div id="codePrintArea">
              <img name="QRCode" id="QRCode" src="Images/code.png" width="100" height="100" alt="�ö�ά���޷���ʾ">
              <table width="250" height="150" id="deadInfoPrint">
              	<tr>
              		<td id="deadNumberShow"></td>
              		<td id="deadNamePrint"></td>
              	</tr>
              	<tr>
              		<td id="deadSexPrint"></td>
              		<td id="deadAgePrint"></td>
              	</tr>
              	<tr>
              		<td id="deadAddressPrint" colspan="2"></td>
              	</tr>
              </table>
            </div>
            <!--endprint-->
            
          </form>
          
        </div>
        <div class="TabbedPanelsContent">
          <form name="form3" method="post" id="form3" action="">
          <!--startprint1-->
            <p>
              <label for="deadId">�������֤���룺</label>
              <input type="text" name="deadInfo.deadId" id="deadId" onchange="return showData()">
              <input type="button" name="ReadCard" id="ReadCard" value="��ȡ���֤����" onclick="return ReadIDCardForService()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <label for="deadName">����������</label>
              <input type="text" name="deadInfo.deadName" id="deadName" >
            </p>
            <p>
              &nbsp;&nbsp;<label for="inTime">�������ʱ�䣺</label>
              <input type="text" name="inTime" id="inTime" disabled="true"/>&nbsp;
              <label for="deadNumber">�����ţ�</label>
              <input type="text" name="deadNumber" id="deadNumber" disabled="true"/>&nbsp;&nbsp;
              <label for="deadResidence">�������ڵأ�</label>&nbsp;&nbsp;
              <select name="deadInfo.deadResidence" id="deadResidence">
                <option selected>��ѡ��...</option>
                <option>�γ��ж�̨���ڶ���</option>
                <option>�γ��ж�̨��ʱ����</option>
                <option>�γ��ж�̨��������</option>
                <option>�γ��ж�̨��������</option>
                <option>�γ��ж�̨�а�����</option>
                <option>�γ��ж�̨����������</option>
                <option>�γ��ж�̨�и�����</option>
                <option>�γ��ж�̨��������</option>
                <option>�γ��ж�̨���½���</option>
                <option>�γ��ж�̨�������</option>
                <option>�γ��ж�̨��������</option>
                <option>�γ��ж�̨��ͷ����</option>
                <option>�γ��ж�̨�Џ�����</option>
                <option>�γ��ж�̨�ж�̨��</option>
                <option>�γ��ж�̨�п�����</option>
                <option>�γ��д����</option>
                <option>̩�����˻���</option>
              </select>
            </p>
            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="deadSex">�����Ա�</label>
              <input name="deadInfo.deadSex" id="deadSex" />&nbsp;
              <label for="deadAge">�������䣺</label>
              <input name="deadInfo.deadAge" id="deadAge" />&nbsp;&nbsp;
              <label for="relationship">�뾭���˹�ϵ��</label>
              <select name="deadInfo.operatorRelation" id="operatorRelation">
                <option>��ѡ��...</option>
                <option>��ż</option>
                <option>��/��</option>
                <option>Ů/ϱ</option>
                <option>���⣩����Ů</option>
                <option>��ĸ/����ĸ/����</option>
                <option>���⣩�游ĸ</option>
                <option>�ֵܽ���</option>
                <option>����</option>
              </select>
            </p>
            <p>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="deadTime">����ʱ�䣺</label>
                <input type="text" name="deadInfo.deadTime" id="deadTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">&nbsp;
                <label for="deadKind">�������ͣ�</label>
                <select name="deadInfo.deadKind" id="deadKind">
                  <option selected>����</option>
                  <option>������</option>
                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="deadReason">����ԭ��</label>&nbsp;&nbsp;&nbsp;&nbsp;
                <select name="deadInfo.deadReason" id="deadReason" onchange="return getPathogenyDetail()">
                  <option selected>��ѡ��</option>
                  <option>��Ȼ����</option>
                  <option>����</option>
                  <option>�¹�</option>
                  <option>����</option>
                  <option>��ɱ</option>
                  <option>��ɱ</option>
                  <option>��ˮ</option>
                  <option>�ж�</option>
                  <option>��Ȼ�ֺ�</option>
                  <option>����</option>
                </select>
                <select name="deadInfo.pathogeny" id="pathogeny" style="display:none">
              	  <option selected>��Ⱦ��</option>
              	  <option>����</option>
              	  <option>ѪҺ��</option>
              	  <option>����/����ϵͳ����</option>
              	  <option>Ƥ����</option>
              	  <option>�񾭲�</option>
              	  <option>����Ѫ�ܼ���</option>
              	  <option>����/����ϵͳ����</option>
                  <option>����ϵͳ����</option>
              	  <option>������ֳϵͳ����</option>
                  <option>����</option>
                </select>
              </p>
              <p>
               &nbsp; <label for="deadAddress">���߼�ͥ��ַ��</label>
                <input type="text" name="deadInfo.deadAddress" id="deadAddress">&nbsp;
                <label for="area">������������</label>
                <select name="deadInfo.area" id="area">
                  <option selected>��ѡ��...</option>
                  <option>�Ƕ�����</option>
                  <option>������</option>
                  <option>������</option>
                  <option>��̨��</option>
                  <option>��Ϫ����</option>
                  <option>������</option>
                  <option>������</option>
                  <option>ͷ����</option>
                  <option>������</option>
                  <option>������</option>
                  <option>�ڶ���</option>
                  <option>ʱ����</option>
                  <option>�����</option>
                  <option>������</option>
                  <option>������</option>
                  <option>�����</option>
                  <option>�˻���</option>
                  <option>����</option>
                </select>&nbsp;&nbsp;&nbsp;
                <label for="memberMobile">ɥ���ֻ����룺</label>
                <input type="text" name="deadInfo.memberMobile" id="memberMobile">
              </p>
              <p>
                <label for="deadProveUnit">����֤�����ߵ�λ��</label>
                <input type="text" name="deadInfo.deadProveUnit" id="deadProveUnit">&nbsp;&nbsp;&nbsp;
                <label for="deadExtraInfo">��ע��</label>
                <input type="text" name="deadInfo.deadExtraInfo" id="deadExtraInfo">&nbsp;&nbsp;
                <label for="ashesDisposition">�ǻ�ȥ��</label>&nbsp;&nbsp;&nbsp;&nbsp;
              	<select name="deadInfo.ashesDisposition" id="ashesDisposition">
                  <option>��ѡ��...</option>
                  <option>��ɢ[�������������ɽ���]</option>
                  <option>��Ӫ�Թ�Ĺ[�ǻ���]</option>
                  <option>�����Թ�Ĺ[�ǻ���]</option>
                  <option>���д��</option>
                  <option>��ԭ��</option>
                  <option>����</option>
             	</select>
              </p>
            <hr>
            <p>
              <label for="dealerId">���������֤���룺</label>
              <input type="text" name="deadInfo.dealerId" id="dealerId">
              <input type="button" name="ReadDealerCard" id="ReadDealerCard" value="��ȡ���֤����" onclick="return ReadForm3DealerCard()" style="width: 125px; ">&nbsp;&nbsp;
              <label for="dealerName">������������</label>
              <input type="text" name="deadInfo.dealerName" id="dealerName">&nbsp;&nbsp;&nbsp;
            </p>
            
            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="directorName">�а���������</label>
              <input type="text" name="deadInfo.directorName" id="directorName">&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="dealerAddress">������סַ��</label>
              <input type="text" name="deadInfo.dealerAddress" id="dealerAddress" style="width: 311px; ">&nbsp;&nbsp;&nbsp;
            </p>
            <hr>

            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="invoiceNo"><b>��Ʊ���룺</b></label>
              <input type="text" name="invoiceNo" id="invoiceNo" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <label for="subsidyNo"><b>������ƾ֤���룺</b></label>
              <input type="text" name="subsidyNo" id="subsidyNo" style="width: 133px; ">&nbsp;&nbsp;
              </p>
              <p>
              &nbsp;&nbsp;&nbsp;&nbsp;<label for="subsidyMoney"><b>��������</b></label>
              <input type="text" name="subsidyMoney" id="subsidyMoney" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;             
              &nbsp;&nbsp;&nbsp;&nbsp;
              <label for="benefitTime"><b>�������������ڣ�</b></label>
              <input type="text" name="benefitTime" id="benefitTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" >&nbsp;&nbsp;
              <input type="button" value="ȷ����Ϣ" id="decideInvoiceAndSubsidy" onclick="return decideInvoice()">
            </p>
            <hr>
            <p>
              <label for="remainsCarryBeCost" >�������Ӧ�շ��ã�</label>
              <input type="input" id="remainsCarryBeCost" value="0" style="width: 87px; " readonly/>Ԫ&nbsp;&nbsp;
              <label for="remainsCarryRealCost" ><label for="remainsCarryBeCost" >�������ʵ�շ��ã�</label>
              <input type="input" id="remainsCarryRealCost" value="0" style="width:87px;" onchange="return remainsCarryChangeWholeCost();">Ԫ
              <input type="hidden" id="lastRemainsCarryRealCost" value="0"><br>
              
              <label for="rentCrystalBeCost">���ñ���Ӧ�շ��ã�</label>
              <input type="input" id="rentCrystalBeCost" value="0" style="width: 87px; " readonly/>Ԫ&nbsp;&nbsp;
              <label for="rentCrystalRealCost">���ñ���ʵ�շ��ã�</label>
              <input type="input" id="rentCrystalRealCost" value="0" style="width:87px;" onchange="return rentCrystalChangeWholeCost();">Ԫ
              <input type="hidden" id="lastRentCrystalRealCost" value="0"><br>
              
              <label for="rentCrystalCarBeCost">���˱���Ӧ�շ��ã�</label>
              <input type="input" id="rentCrystalCarBeCost" value="0" style="width: 87px; " readonly/>Ԫ&nbsp;&nbsp;
              <label for="rentCrystalCarRealCost"><label for="rentCrystalCarBeCost">���˱���ʵ�շ��ã�</label>
              <input type="input" id="rentCrystalCarRealCost" value="0" style="width:87px;" onchange="return rentCrystalCarChangeWholeCost();">Ԫ
              <input type="hidden" id="lastRentCrystalCarRealCost" value="0"><br>
  <!--            
              <label for="watchSpiritVillaBeCost">�������Ӧ�շ��ã�</label>
              <input type="input" id="watchSpiritVillaBeCost" value="0" style="width: 87px; " readonly/>Ԫ&nbsp;&nbsp;
              <label for="watchSpiritVillaRealCost">�������ʵ�շ��ã�</label>
              <input type="input" id="watchSpiritVillaRealCost" value="0" style="width:87px;" onchange="return watchSpiritVillaChangeWholeCost();">Ԫ
              <input type="hidden" id="lastWatchSpiritVillaRealCost" value="0"><br>
              
              
              <label for="watchSpiritCoffinBeCost">�����ù�Ӧ�շ��ã�</label>
              <input type="input" id="watchSpiritCoffinBeCost" value="0" style="width: 87px; " readonly/>Ԫ&nbsp;&nbsp;
              <label for="watchSpiritCoffinRealCost">�����ù�ʵ�շ��ã�</label>
              <input type="input" id="watchSpiritCoffinRealCost" value="0" style="width:87px;" onchange="return watchSpiritCoffinChangeWholeCost();">Ԫ
              <input type="hidden" id="lastWatchSpiritCoffinRealCost" value="0">&nbsp;&nbsp;&nbsp;&nbsp;-->
              </p>


            <hr>
            <p>
              <input type="checkbox" id="setServiceCheckBox" onchange="return chooseSetService()">
              <label for="setService">�ײ����ͣ�</label>
			  <select name="setService" id="setService" disabled="true" onchange="return showSetServiceDetail()">
				<option>-��ѡ��-</option>
			  </select>&nbsp;&nbsp;
			  <input type="hidden" id="setServiceDetailContent" style="width: 40%; ">
			  <input type="hidden" id="setServiceAllCost" value="0">
            </p>
            <p>
              <input type="checkbox" id="urnChooseBox" checked="checked" onchange="return cancelUrnCost()">
              <label for="urnChoose">�ǻҺ����ͣ�</label>
			  <select name="urnChoose" id="urnChoose" onchange="return getUrnBeCost()">
				<option>-��ѡ��-</option>
			  </select>&nbsp;&nbsp;
			  <label for="urnBeCost">Ӧ�շ��ã�</label>
        	  <input type="text" name="urnBeCost" id="urnBeCost" value="0" style="width: 92px;" onkeyup="return changeUrnBeCost()"/>Ԫ&nbsp;&nbsp;
        	  <label for="urnRealCost">��ע��</label>
        	  <input type="text" name="urnRealCost" id="urnRealCost" value="0" style="width: 86px; " onchange="return changeUrnRealCost()">Ԫ
        	  <input name="preUrnBeCost" id="preUrnBeCost" type="hidden" value="0"/>
        	  <input name="preUrnRealCost" id="preUrnRealCost" type="hidden" value="0"/>
            </p>
            <p>
              <input type="checkbox" id="makeBeautyBox" checked="checked" onchange="return cancelBeautyCost()">
			  <label for="makeBeautyGrade">�������ͣ�</label>&nbsp;&nbsp;
			  <select name="makeBeautyGrade" id="makeBeautyGrade" onChange="return getMakeBeautyBeCost()">
				<option>-��ѡ��-</option>
				<option>����</option>
			  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	  <label for="makeBeautyBeCost">Ӧ�շ��ã�</label>
        	  <input type="text" name="makeBeautyBeCost" id="makeBeautyBeCost" value="0" style="width: 92px;" onkeyup="return changeBeautyBeCost()"/>Ԫ&nbsp;&nbsp;
        	  <label for="makeBeautyRealCost">��ע��</label>
        	  <input type="text" name="makeBeautyRealCost" id="makeBeautyRealCost" value="0" onchange="return changeBeautyRealCost()" style="width: 86px; ">Ԫ
        	  <input name="preMakeBeautyBeCost" id="preMakeBeautyBeCost" type="hidden" value="0"/>
        	  <input name="preMakeBeautyRealCost" id="preMakeBeautyRealCost" type="hidden" value="0"/>
            </p>
            <p>
              <input type="checkbox" id="leaveRoomBox" checked="checked" onchange="return cancelLeaveRoomCost()">
			  <label for="leaveRoomGrade">��������ͣ�</label>
			  <select name="leaveRoomGrade" id="leaveRoomGrade" onChange="return getLeaveRoomBeCost()">
				<option>-��ѡ��-</option>
			  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <label for="leaveRoomBeCost">Ӧ�շ��ã�</label>
        	  <input type="text" name="leaveRoomBeCost" id="leaveRoomBeCost" value="0" style="width: 92px;" onkeyup="return changeLeaveRoomBeCost()"/>Ԫ&nbsp;&nbsp;
        	  <label for="leaveRoomRealCost">��ע��</label>
        	  <input type="text" name="leaveRoomRealCost" id="leaveRoomRealCost" value="0" onchange="return changeLeaveRoomRealCost()" style="width: 86px; ">Ԫ
        	  <input name="preLeaveRoomBeCost" id="preLeaveRoomBeCost" type="hidden" value="0"/>
        	  <input name="preLeaveRoomRealCost" id="preLeaveRoomRealCost" type="hidden" value="0"/>
        	  <input name="preSetFarewellBeCost" id="preSetFarewellBeCost" type="hidden" value="0"/>
			</p>
			<p>
              <input type="checkbox" id="cremationBox" checked="checked" onchange="return cancelCremationCost()">
			  <label for="cremationGrade">��¯���ͣ�</label>
			  <select name="cremationGrade" id="cremationGrade" onChange="return getCremationBeCost()">
				<option>-��ѡ��-</option>
				<option>��ͨ¯</option>
				<option>���¯</option>
				<option>VIP��</option>
			  </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <label for="cremationBeCost">Ӧ�շ��ã�</label>
        	  <input type="text" name="cremationBeCost" id="cremationBeCost" value="0" style="width: 92px;" onkeyup="return changeCremationBeCost()"/>Ԫ&nbsp;&nbsp;
        	  <label for="cremationRealCost">��ע��</label>
        	  <input type="text" name="cremationRealCost" id="cremationRealCost" value="0" onchange="return changeCremationRealCost()" style="width: 86px; ">Ԫ
        	  <input name="preCremationBeCost" id="preCremationBeCost" type="hidden" value="0"/>
        	  <input name="preCremationRealCost" id="preCremationRealCost" type="hidden" value="0"/>
        	  <input name="preSetFurnaceBeCost" id="preSetFurnaceBeCost" type="hidden" value="0"/>
			</p>
            <p>
            <div id="funeralGoodsPart">
              <table align="center" border="1" width="95%" id="allFuneralGoods">
              	<tr>
              		<td align="center" colspan="13">
              			<label for="funeralGoods"><b>ɥ����Ʒ������С��Ʒѡ��</b></label>
              		</td>
              	</tr>
              	<tr align="center">
              		<td><b>ɥ����Ʒ����</b></td>
              		<td><b>Ӧ�ս��</b></td>
              		<td><b>��ע</b></td>
              		<td><b>���</b></td>
              		<td><b>���</b></td>              		
              		<td><b></b></td>
              		<td><b>&nbsp;</b></td>
              		<td><b></b></td>
              		<td><b>ɥ����Ʒ����</b></td>
              		<td><b>Ӧ�ս��</b></td>
              		<td><b>��ע</b></td>
              		<td><b>���</b></td>
              		<td><b>���</b></td>
              	</tr>
              </table>
             </div>
            </p>
            <hr>
            <p>
              <label for="allBeCost"><b>�𻯷������Ӧ�գ�</b></label>
              <input type="text" name="allBeCost" id="allBeCost" value="0" style="width: 100px;" onkeyup="return changeAllBeCost()"/>Ԫ&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="hidden" id="allBeCostHidden" value="0">
              <label for="allRealCost"><b>�𻯷�����ñ�ע��</b></label>
              <input type="text" name="allRealCost" id="allRealCost" value="0" style="width: 100px;" onkeyup="return changeAllRealCost()"/>Ԫ
            </p>
            <p>
              
              <input type="button" value="����" style="margin-left:10px" onclick="return sumCost()">
              <label for="sumBeCost" style="margin-left:50px"><b>��Ӧ�գ�</b></label>
			  <input type="text" name="sumBeCost" id="sumBeCost" value="0" style="width: 100px;" readonly/>Ԫ
			  <label for="sumRealCost" style="margin-left:170px"><b>�ܱ�ע��</b></label>
			  <input type="text" name="sumRealCost" id="sumRealCost" value="0" style="width: 100px;" readonly/>Ԫ
            </p>
            <p>
            <label for="theWholeCost" style="margin-left:120px"><b>�ܷ��ã�</b></label>
              <input type="text" name="theWholeCost" id="theWholeCost" value="0" style="width: 100px;" readonly/>Ԫ
              <input type="hidden" id="lastWholeCost" value="0">
            </p>
            
            <hr>
            <!--endprint1-->
            <input type="button" name="createSetOrder" id="createSetOrder" value="�����ײ͵���" disabled="true" onclick="return getFunneralGoodsCost()" style="width: 125px; "><p align="center">
              <input type="button" name="printServiceList" id="printServiceList" value="���ɷ����굥" onclick="return outPutServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <input type="button" name="printServiceList" id="printServiceList" value="��ӡ�����굥" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;
              <input type="button" name="printSetServiceList" id="printSetServiceList" value="��ӡ�ײ͵���" disabled="true" onclick="return outPrintSetServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <input type="button" name="ensureChoose" id="ensureChoose" value="ȷ�Ϸ���" onclick="return decideYourChoose()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <br>
            </p>
            <p align="center">
            <br>
			  &nbsp;<a href="javascript:;" style="text-decoration:none;color:black;" onclick="javascript:CreatePrintPage();LODOP.PREVIEW();">˰Ʊ��ӡ</a>&nbsp;&nbsp;&nbsp;
			  <a href="javascript:;" style="text-decoration:none;color:black;" onclick="javascript:CreateCremationPrint();LODOP.PREVIEW();">��֤��ӡ</a>&nbsp;&nbsp;&nbsp;
              <a href="javascript:;" style="text-decoration:none;color:black;" onclick="javascript:CreateSubsidyPrintPage();LODOP.PREVIEW();">��������ӡ</a>&nbsp;&nbsp;&nbsp;
              <a href="javascript:;" style="text-decoration:none;color:black;" onclick="javascript:setDetailPrint();LODOP.PREVIEW();">�ײ������ӡ</a>
			</p>
			<hr>
            <!--- style="display:none"----------------------------------------------------------------------------- ��ӡ���� ---------------------------------------------------------------------------------------------->
            <div id="goodsPartList">
            <table  border="0" width="95%" id="basicInfo" style="font-size:12px;border-collapse:collapse;border:0px solid #000;margin-left:30;">
            	
            	<tr>
              		<td align="center" colspan="12">
              			<label for="funeralGoods"><b><font style="font-size:22px">��̨�����ǹݷ����շѵ�</font></b></label>(��̨�ֹ�)
              		</td>
              	</tr>
              	<tr>
              		<td><b>������ţ�</b></td>
	              	<td id="currentDateTh2"></td>
	              	<td colspan="1"><b>No:</b></td>
	              	<td id="no" colspan="2"></td>
	              	<td colspan="1"><b>����:</b></td>
	              	<td id="taxDate" colspan="6"></td>
              	</tr>
              	<tr>
	              	<td colspan="1"><b>����:</b></td>
	              	<td colspan="1" id="name"></td>
	              	<td colspan="1"><b>�Ա�:</b></td>
	              	<td colspan="1" id="sex"></td>
	              	<td colspan="1"><b>����:</b></td>
	              	<td colspan="1" id="age"></td>
	              	<td colspan="1"><b>סַ:</b></td>
	              	<td colspan="5" id="address"></td>
              	</tr>
            	
            </table>
            <table  border="2" width="95%" id="allGoods" style="font-size:12px;border-collapse:collapse;border:1px solid #000;margin-left:30;">
              	<tr >
              		<td><b><font style="font-size:16px">����</font></b></td>
              		<td><b><font style="font-size:16px">ժҪ</font></b></td>
              		<td><b><font style="font-size:16px">Ӧ��</font></b></td>
              		<td><b><font style="font-size:16px">��ע</font></b></td>
              		
              		<td><b><font style="font-size:16px">����</font></b></td>
              		<td><b><font style="font-size:16px">ժҪ</font></b></td>
              		<td><b><font style="font-size:16px">Ӧ��</font></b></td>
              		<td><b><font style="font-size:16px">��ע</font></b></td>
              		
              		<td><b><font style="font-size:16px">����</font></b></td>
              		<td><b><font style="font-size:16px">ժҪ</font></b></td>
              		<td><b><font style="font-size:16px">Ӧ��</font></b></td>
              		<td><b><font style="font-size:16px">��ע</font></b></td>
              	</tr>
              	<tr>
              	    <td ><b>��¯</b></td>
	              	<td id="cremation"></td>
	              	<td id="cremation1"></td>
	              	<td id="cremation2"></td>
	              	
	              	<td ><b>�����</b></td>
	              	<td id="farewell"></td>
	              	<td id="farewell1"></td>
	              	<td id="farewell2"></td>
	              	
	              	<td><b>�ǻҺ�</b></td>
	              	<td id="urn"></td>
	              	<td id="urn1"></td>
	              	<td id="urn2"></td>
              	</tr>
              	<tr>
	              	<td ><b>����</b></td>
	              	<td id="face"></td>
	              	<td id="face1"></td>
	              	<td id="face2"></td>
	              	
	              	<td ><b>���ñ���</b></td>
	              	<td id="crystal"></td>
	              	<td id="crystal1"></td>
	              	<td id="crystal2"></td>
	              	
	              	<td ><b>����</b></td>
	              	<td id="watch"></td>
	              	<td id="watch1"></td>
	              	<td id="watch2"></td>
              	</tr>
              	<tr>
	              	<td><b>�������</td>
	              	<td id="car"></td>
	              	<td id="car1"></td>
	              	<td id="car2"></td>
	              	<td><b>�ù׽���</td>
	              	<td id="car3"></td>
	              	<td id="car4"></td>
	              	<td id="car5"></td>
	              	<td></td>
	              	<td></td>
	              	<td></td>
	              	<td></td>
              	</tr>
              	
              	<tbody id="all"></tbody>
              	
              	<tr>
              		<td><b>����Ӧ��</b></td>
	              	<td colspan="1" id="beCost"></td>
	              	
	              	<td><b>��ע</b></td>
	              	<td colspan="1" id="charge"></td>
	              	
	              	<td><b>����ʵ��</b></td>
	              	<td colspan="1" id="total"></td>
	              	
	              	<td colspan="1"><b>��д</b></td>
	              	<td colspan="5" id="capitalMoney"></td>
              	</tr>
              	<tr>
	              	<td colspan="1"><b>��ƱԱ</b></td>
	              	<td colspan="3"></td>
	              	<td colspan="1"><b>����Ա</b></td>
	              	<td colspan="7"></td>
              	</tr>
              	<tr>
	              	<td colspan="4"><b>(��������  ȷ��ǩ��)�ͻ�������ǩ��</b></td>
	              	<td colspan="8"></td>
              	</tr>
              </table>
            </div>
            <!------------------------------------------------------------ ��ӡ���� ------------------------------------------------------------------------->
            <div id="setGoodsPartList">
	            <table  border="0" width="95%" id="setBasicInfo" style="font-size:12px;border-collapse:collapse;border:0px solid #000;margin-left:30px;">
	            	<tr>
	              		<td align="center" colspan="12">
	              			<label><b><font style="font-size:22px">��̨�����ǹݷ����շѵ�</font></b></label>(��̨�ֹ�)
	              		</td>
	              	</tr>
	              	<tr>
	              		<td><b>������ţ�</b></td>
	              		<td id="currentDateTh"></td>
		              	<td colspan="1"><b>No:</b></td>
		              	<td id="setNo" colspan="2"></td>
		              	
		              	<td colspan="1"><b>����:</b></td>
		              	<td id="setTaxDate" colspan="6"></td>
	              	</tr>
	              	<tr>
		              	<td colspan="1"><b>����:</b></td>
		              	<td colspan="1" id="setName"></td>
		              	<td colspan="1"><b>�Ա�:</b></td>
		              	<td colspan="1" id="setSex"></td>
		              	<td colspan="1"><b>����:</b></td>
		              	<td colspan="1" id="setAge"></td>
		              	<td colspan="1"><b>סַ:</b></td>
		              	<td colspan="5" id="setAddress"></td>
	              	</tr>
	            </table>
	            <table border="2" width="95%" height="22%" id="setAllGoods" style="font-size:12px;border-collapse:collapse;border:1px solid #000;margin-left:30;">
	            	<tr>
	            		<td colspan="1" width="15%"><b>��Ŀ</b></td>
	            		<td colspan="1"><b>���</b></td>
	            		<td colspan="8" width="80%"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem1"></td>
	            		<td colspan="1" id="itemPrice1"></td>
	            		<td colspan="8" id="setServiceDetail"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem2"></td>
	            		<td colspan="1" id="itemPrice2"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem3"></td>
	            		<td colspan="1" id="itemPrice3"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem4"></td>
	            		<td colspan="1" id="itemPrice4"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem5"></td>
	            		<td colspan="1" id="itemPrice5"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem6"></td>
	            		<td colspan="1" id="itemPrice6"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem7"></td>
	            		<td colspan="1" id="itemPrice7"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem8"></td>
	            		<td colspan="1" id="itemPrice8"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="15%" id="setItem9"></td>
	            		<td colspan="1" id="itemPrice9"></td>
	            		<td colspan="8"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="10%"><b>�ϼ�</b></td>
	            		<td colspan="1" id="setAllCost"></td>
	            		<td colspan="1"><b>��д</b></td>
	            		<td colspan="7" id="setAllCostUp" width="70%"></td>
	            	</tr>
	            	<tr>
	            		<td colspan="1" height="10%"><b>��ƱԱ</b></td>
	            		<td colspan="1" ></td>
	            		<td colspan="1"><b>����Ա</b></td>
	            		<td colspan="1" width="10%"></td>
	            		<td colspan="2"><b>(�������� ȷ��ǩ��)</b></td>
	            		<td colspan="1"><b>�ͻ�ǩ��</b></td>
	            		<td colspan="3" width="30%"></td>
	            	</tr>
	            </table>
            </div>
          </form>
          <p>&nbsp;</p>
        </div>
        <div class="TabbedPanelsContent">
         <form  name= "form4" id="form4" action="" >
         <div>
          <p>
          <label for="startTime">��ʼʱ�䣺</label>
              <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">����ʱ�䣺</label>
              <input type="text" name="endTime" id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              <input type="button" value="��ѯ" onclick="return remainsInfo()">
         </p>
         </div>
         <div>
         <center>��ǰ��<input type="text" id="asd">���ѹҺ�</center>
         <br>
         <table id="remainsInfo" border="1" align="center" style="display:none">
         <thead>
         <tr>
            <th colspan="5" align="center"><b><font style="font-size:24px">���߹Һ���Ϣ��</font></b></th> 
            </tr>
            <tr>
            	<th>����ʱ��</th>
            	<th>���</th>
            	<th>���֤��</th>
            	<th>����</th>
            	<th>����</th>
            </tr>
         </thead>
         <tbody id="remainsInfoBody">
                                    
         </tbody>         
         </table>
         <div id="divNumber" class="pagination" style="display:none" align="center"></div>
         </div>
         <br>
         <hr>
         <br>
         <label for="wrongDeadId">��������֤��:</label>
         <input type="text" id="wrongDeadId" >
         <label for="latestDeadId" style="margin-left:96px">�µ����֤��:</label>
         <input type="text" id="latestDeadId">
         <input type="button" value="����޸�" onclick="return updateDeadId()">
         </form>
         
        </div>
         
	</div>

<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
<script src="js/paging.js"    type="text/javascript"></script>

<script src="js/AjaxJson.js"    type="text/javascript"></script>


<script src="js/createHttpRequest.js">
	//����XMLHttpRequest����
</script>

</html>
