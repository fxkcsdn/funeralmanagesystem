<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>��ӭʹ���������ܻ�ϵͳ</title>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">���ݻ���صǼ�</li>
    <li class="TabbedPanelsTab" tabindex="0">�Ǳ��ݻ���صǼ�</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <form name="form1" method="post" action="">
        <p>
          <label for="DealerCreditNumber11">���������֤���룺</label>
          <input type="text" name="DealerCreditNumber11" id="DealerCreditNumber11">
          <label for="DealerPhone11">�������ֻ����룺</label>
          <input type="text" name="DealerPhone11" id="DealerPhone11">
        </p>
        <p>
          <label for="FridgeNumber11">��ر���ţ�</label>
          <input name="FridgeNumber11" type="text" id="FridgeNumber11">
          <label for="StorageStartTime11">�����ʼʱ�䣺</label>
          <input type="text" name="StorageStartTime11" id="StorageStartTime11">
        </p>
        <p><label>����Ƿ������</label>
          <label>
            <input name="IsStorageEnd11" type="radio" id="IsStorageEnd11_0" value="��" checked="CHECKED">
            ��</label>
          <label>
            <input type="radio" name="IsStorageEnd11" value="��" id="IsStorageEnd11_1">
            ��</label>
          <label for="StorageEndTime11">��ؽ���ʱ�䣺</label>
          <input type="text" name="StorageEndTime11" id="StorageEndTime11">
        </p>
        <p>
          <label for="CostInAll11">��ط����ܼƣ�</label>
          <input type="text" name="CostInAll11" id="CostInAll11">          
        Ԫ</p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="SaveSubmit11" id="SaveSubmit11" value="������Ϣ">
          <br>
        </p>
      </form>
      <p>&nbsp;</p>
    </div>
    <div class="TabbedPanelsContent">
      <form name="form2" method="post" action="">
        <p>
          <label for="DeadCreditNumber12">�������֤���룺</label>
          <input type="text" name="DeadCreditNumber12" id="DeadCreditNumber12">
          <label for="DeadName12">����������</label>
          <input type="text" name="DeadName12" id="DeadName12">
        </p>
        <p>
          <label for="DealerCreditNumber12">���������֤���룺</label>
          <input type="text" name="DealerCreditNumber12" id="DealerCreditNumber12">
          <label for="DealerPhone12">�������ֻ����룺</label>
          <input type="text" name="DealerPhone12" id="DealerPhone12">
        </p>
        <p>
          <label for="FridgeNumber12">��ر���ţ�</label>
          <input type="text" name="FridgeNumber12" id="FridgeNumber12">
          <label for="StorageStartTime12">�����ʼʱ�䣺</label>
          <input type="text" name="StorageStartTime12" id="StorageStartTime12">
        </p>
        <p><label>����Ƿ������
          <input name="IsStorageEnd12" type="radio" id="IsStorageEnd12_0" value="��" checked="CHECKED">
��</label>
          <label>
            <input type="radio" name="IsStorageEnd12" value="��" id="IsStorageEnd12_1">
            ��</label>
          <label for="StorageEndTime12">��ؽ���ʱ�䣺</label>
          <input type="text" name="StorageEndTime12" id="StorageEndTime12">
        </p>
        <p>
          <label for="CostInAll12">��ط����ܼƣ�</label>
          <input type="text" name="CostInAll12" id="CostInAll12">
          Ԫ</p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="SaveSubmit12" id="SaveSubmit12" value="������Ϣ">
        </p>
      </form>
      <p>&nbsp;</p>
    </div>
  </div>
</div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
</html>
