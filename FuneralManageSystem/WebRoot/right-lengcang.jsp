<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>欢迎使用殡葬智能化系统</title>
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
    <li class="TabbedPanelsTab" tabindex="0">本馆火化冷藏登记</li>
    <li class="TabbedPanelsTab" tabindex="0">非本馆火化冷藏登记</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <form name="form1" method="post" action="">
        <p>
          <label for="DealerCreditNumber11">经办人身份证号码：</label>
          <input type="text" name="DealerCreditNumber11" id="DealerCreditNumber11">
          <label for="DealerPhone11">经办人手机号码：</label>
          <input type="text" name="DealerPhone11" id="DealerPhone11">
        </p>
        <p>
          <label for="FridgeNumber11">冷藏冰柜号：</label>
          <input name="FridgeNumber11" type="text" id="FridgeNumber11">
          <label for="StorageStartTime11">冷藏起始时间：</label>
          <input type="text" name="StorageStartTime11" id="StorageStartTime11">
        </p>
        <p><label>冷藏是否结束：</label>
          <label>
            <input name="IsStorageEnd11" type="radio" id="IsStorageEnd11_0" value="是" checked="CHECKED">
            是</label>
          <label>
            <input type="radio" name="IsStorageEnd11" value="否" id="IsStorageEnd11_1">
            否</label>
          <label for="StorageEndTime11">冷藏结束时间：</label>
          <input type="text" name="StorageEndTime11" id="StorageEndTime11">
        </p>
        <p>
          <label for="CostInAll11">冷藏费用总计：</label>
          <input type="text" name="CostInAll11" id="CostInAll11">          
        元</p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="SaveSubmit11" id="SaveSubmit11" value="保存信息">
          <br>
        </p>
      </form>
      <p>&nbsp;</p>
    </div>
    <div class="TabbedPanelsContent">
      <form name="form2" method="post" action="">
        <p>
          <label for="DeadCreditNumber12">逝者身份证号码：</label>
          <input type="text" name="DeadCreditNumber12" id="DeadCreditNumber12">
          <label for="DeadName12">逝者姓名：</label>
          <input type="text" name="DeadName12" id="DeadName12">
        </p>
        <p>
          <label for="DealerCreditNumber12">经办人身份证号码：</label>
          <input type="text" name="DealerCreditNumber12" id="DealerCreditNumber12">
          <label for="DealerPhone12">经办人手机号码：</label>
          <input type="text" name="DealerPhone12" id="DealerPhone12">
        </p>
        <p>
          <label for="FridgeNumber12">冷藏冰柜号：</label>
          <input type="text" name="FridgeNumber12" id="FridgeNumber12">
          <label for="StorageStartTime12">冷藏起始时间：</label>
          <input type="text" name="StorageStartTime12" id="StorageStartTime12">
        </p>
        <p><label>冷藏是否结束：
          <input name="IsStorageEnd12" type="radio" id="IsStorageEnd12_0" value="是" checked="CHECKED">
是</label>
          <label>
            <input type="radio" name="IsStorageEnd12" value="否" id="IsStorageEnd12_1">
            否</label>
          <label for="StorageEndTime12">冷藏结束时间：</label>
          <input type="text" name="StorageEndTime12" id="StorageEndTime12">
        </p>
        <p>
          <label for="CostInAll12">冷藏费用总计：</label>
          <input type="text" name="CostInAll12" id="CostInAll12">
          元</p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="SaveSubmit12" id="SaveSubmit12" value="保存信息">
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
