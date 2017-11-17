/**
 * 删除采购单界面对应的脚本
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
window.parent.document.getElementById("TabbedPanels1").children[0].children[3].onclick = function()
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
	  // 获取采购单主信息
	  getPurchaseMaster(purchaseNumber);
	  // 获取采购单明细信息
	  getPurchaseDetails(purchaseNumber);
  }
};

/**
 * 请求采购单主信息
 */
var getPurchaseMaster = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/closePurchase!getPurchaseMaster";
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
		document.getElementById("memo").value = data[i].memo;
	}
};

/**
 * 请求采购单明细信息
 */
var getPurchaseDetails = function(purchaseNumber)
{
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/closePurchase!getPurchaseDetails";
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
 * 保存删除信息
 */
var saveDeleteInfo = function()
{
	var purchaseNumber = document.getElementById("purchaseNumber").value;// 采购编号
	var data = "purchaseNumber=" + purchaseNumber;
	var url = "/FuneralManageSystem/deletePurchase!saveDeleteInfo";
	if (confirm("确定要删除吗？"))
	{
		sendRequest("post", url, data, getSaveDeleteInfoBack);
	}
	return false;
};

/**
 * 返回删除结果
 */
var getSaveDeleteInfoBack = function(result)
{
	var data = eval("(" + result + ")");
	if (data == "true")
	{
		alert("删除成功！");
		// 提交按钮不可用
		document.getElementById("saveButton").disabled = true;
	}
	else alert("删除失败！");
};