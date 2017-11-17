/**
 * 取消审核界面所对应的脚本
 */

/**
 * 创建XMLHttpRequest对象
 */
var createXMLHttpRequest = function()
{
	var XMLHttpReq = false;
	// 如果浏览器有XMLHttpRequest对象，则直接创建
	if (window.XMLHttpRequest)
	{
		XMLHttpReq = new XMLHttpRequest();
	}
	// 如果是ie浏览器
	else if (window.ActiveXObject)
	{
		try 
		{
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			XMLHttpReq = new ActiveObject("Microsoft.XMLHTTP");
		}
	}
	return XMLHttpReq;
};
	
/**
 * ajax提交请求
 */
var sendRequest = function(method, url, data, getResult)
{
	var result = "";
	// 创建XMLHttpRequest对象
	var XMLHttpReq = createXMLHttpRequest();
	if (method == "post")
	{
		// 打开连接
		XMLHttpReq.open("post", url, false);
		XMLHttpReq.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		// 回调函数
		XMLHttpReq.onreadystatechange = function()
		{
			if(XMLHttpReq.readyState == 4)
 	 		{
 	 		 	//返回成功；
 	 		 	if(XMLHttpReq.status == 200)
 	 		 	{
 	 		 		result = XMLHttpReq.responseText;
 	 		 		getResult(result);
 	 		 	}
 	 		}
		}
		// 发送数据
		XMLHttpReq.send(data.toString());
	}
};

/**
 * 页面加载时触发该事件
 */
window.parent.document.getElementById("TabbedPanels1").children[0].children[5].onclick = function()
{
  var type = window.parent.document.getElementById("typeStorage").value;// 获取父页面type
  var purchaseNumber = window.parent.document.getElementById("purchaseNumberStorage").value;// 采购编号
  // 将父窗口的type和采购编号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("purchaseNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && purchaseNumber == "")
  {
    alert("请先选择一个采购单！");
    // 重新加载页面
    window.parent.location.reload("purchaseManage.jsp");
  }
  else
  {
	  document.getElementById("purchaseNumber").value = purchaseNumber;
	  // 根据type值显示相应的审批人
	  showAudit(type);
	  // 获取采购单主信息
	  getPurchaseMaster(purchaseNumber);
	  // 获取采购单明细信息
	  getPurchaseDetails(purchaseNumber);
  }
};

/**
 * 根据type值显示相应的审批人
 */
var showAudit = function(type)
{
	// 如果是财务审批
	if (type == "cancelFinanceAudit")
	{
		// 显示财务审批人，隐藏分馆长审批人和馆长审批人
		document.getElementById("finance").style.display = "";
		document.getElementById("financeCol").style.display = "";
		document.getElementById("viceCurator").style.display = "none";
		document.getElementById("viceCuratorCol").style.display = "none";
		document.getElementById("curator").style.display = "none";
		document.getElementById("curatorCol").style.display = "none";
	}
	// 如果是分馆长审批
	else if (type == "cancelViceCuratorAudit")
	{
		// 显示分馆长审批人，隐藏财务审批人和馆长审批人
		document.getElementById("finance").style.display = "none";
		document.getElementById("financeCol").style.display = "none";
		document.getElementById("viceCurator").style.display = "";
		document.getElementById("viceCuratorCol").style.display = "";
		document.getElementById("curator").style.display = "none";
		document.getElementById("curatorCol").style.display = "none";
	}
	// 如果是馆长审批
	else if (type == "cancelCuratorAudit")
	{
		// 显示馆长审批人，隐藏财务审批人和分馆长审批人
		document.getElementById("finance").style.display = "none";
		document.getElementById("financeCol").style.display = "none";
		document.getElementById("viceCurator").style.display = "none";
		document.getElementById("viceCuratorCol").style.display = "none";
		document.getElementById("curator").style.display = "";
		document.getElementById("curatorCol").style.display = "";
	}
};

/**
 * 请求采购单主信息
 */
var getPurchaseMaster = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/cancelAuditPurchase!getPurchaseMaster";
	sendRequest("post", url, data, getPurchaseMasterBack);
};

/**
 * 获取采购单主信息
 */
var getPurchaseMasterBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有采购单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("supplier").value = data[i].supplierName;
		document.getElementById("operator").value = data[i].operator;
		document.getElementById("createDate").value = data[i].createDate.substring(0, data[i].createDate.length - 5);
		// 如果是财务取消审核，则显示财务审批人
		if (document.getElementById("finance").style.display == "")
			document.getElementById("financeAudit").value = data[i].financeAudit;
		// 如果是分馆长取消审核
		else if (document.getElementById("viceCurator").style.display == "")
			document.getElementById("viceCuratorAudit").value = data[i].viceCuratorAudit;
		// 馆长取消审核
		else if (document.getElementById("curator").style.display == "")
			document.getElementById("curatorAudit").value = data[i].curatorAudit;
	}
};

/**
 * 请求采购单明细信息
 */
var getPurchaseDetails = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/cancelAuditPurchase!getPurchaseDetails";
	sendRequest("post", url, data, getPurchaseDetailsBack);
};

/**
 * 获取采购单明细信息
 */
var getPurchaseDetailsBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	// 遍历所有明细信息
	for (var i = 0; i < data.length; i++)
	{
		var table = document.getElementById("goodsInfo");// 采购单明细表格
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		td1.innerHTML = data[i].goodsType;
		td2.innerHTML = data[i].goodsName;
		td3.innerHTML = data[i].unit;
		td4.innerHTML = data[i].amount;
		// 如果入库价不为空
		if (data[i].buyPrice != "" && data[i].buyPrice != "null")
			td5.innerHTML = data[i].buyPrice;
		// 否则，置入库价列为空
		else td5.innerHTML = "";
	}
};

/**
 * 保存取消审核信息
 */
var saveCancelAuditInfo = function()
{
	var purchaseNumber = document.getElementById("purchaseNumber").value;// 采购编号
	var audit = "";// 审批人
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/cancelAuditPurchase!saveCancelAuditInfo";
	// 如果是财务审批人
	if (document.getElementById("finance").style.display == "")
	{
		audit = document.getElementById("financeAudit").value;// 财务审批人
		data += "&audit=" + audit + "&type=" + "financeAudit";
	}
	// 如果是分馆长审批人
	else if (document.getElementById("viceCurator").style.display == "")
	{
		audit = document.getElementById("viceCuratorAudit").value;// 分馆长审批人
		data += "&audit=" + audit + "&type=" + "viceCuratorAudit";
	}
	// 如果是馆长审批人
	else if (document.getElementById("curator").style.display == "")
	{
		audit = document.getElementById("curatorAudit").value;// 馆长审批人
		data += "&audit=" + audit + "&type=" + "curatorAudit";
	}
	// 如果审核通过
	if (document.getElementById("auditResult").value == "取消审核")
	{
		if (confirm("确定取消审核吗？"))
		{
			sendRequest("post", url, data, getSaveCancelAuditInfoBack);
		}
	}
	return false;
};

/**
 * 返回保存取消审核信息结果
 */
var getSaveCancelAuditInfoBack = function(result)
{
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		alert("取消审核成功！");
		// 提交按钮不可用
		document.getElementById("saveButton").disabled = true;
	}
	else alert("取消审核失败！");
};