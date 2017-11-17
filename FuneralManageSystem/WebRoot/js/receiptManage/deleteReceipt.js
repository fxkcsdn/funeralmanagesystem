/**
 * 删除收货单界面对应的脚本
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
  var receiptNumber = window.parent.document.getElementById("receiptNumberStorage").value;// 收货单号
  // 将父窗口的type和收货单号置为空
  window.parent.document.getElementById("typeStorage").value = "";
  window.parent.document.getElementById("receiptNumberStorage").value = "";
  // 如果单击选项卡进入该页面
  if (type == "" && receiptNumber == "")
  {
    alert("请先选择一个收货单！");
    // 重新加载页面
    window.parent.location.reload("receiptManage.jsp");
  }
  else
  {
	 document.getElementById("receiptNumber").value = receiptNumber;
	 // 请求收货单主信息
	 getReceipt(receiptNumber);
	 // 请求收货单明细信息
	 getReceiptDetails(receiptNumber);
  }
};

/**
 * 请求收货单主信息
 */
var getReceipt = function(receiptNumber)
{
	var data = "receiptNumber=" + receiptNumber;
	var url = "/FuneralManageSystem/deleteReceipt!getReceipt";
	sendRequest("post", url, data, getReceiptBack);
};

/**
 * 获取收货单主信息
 */
var getReceiptBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") + ")");
	// 遍历所有收货单主信息
	for (var i = 0; i < data.length; i++)
	{
		document.getElementById("staffName").value = data[i].staffName;
		document.getElementById("receiptDate").value = data[i].receiptDate;
		document.getElementById("warehouseName").value = data[i].warehouseName;
		document.getElementById("purchaseNumber").value = data[i].purchaseNumber;
	}
};

/**
 * 请求收货单明细信息
 */
var getReceiptDetails = function(receiptNumber)
{
	var data = "receiptNumber=" + receiptNumber;
	var url = "/FuneralManageSystem/auditReceipt!getReceiptDetails";
	sendRequest("post", url, data, getReceiptDetailsBack);
};

/**
 * 获取收货单明细信息
 */
var getReceiptDetailsBack = function(result)
{
	var data = eval("(" + eval("(" + result + ")") +")");
	// 遍历所有明细信息
	for (var i = 0; i < data.length; i++)
	{
		var table = document.getElementById("receiptDetails");// 收货单明细表格
		var tr = table.insertRow();
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		td1.innerHTML = data[i].goodsType;
		td2.innerHTML = data[i].goodsName;
		td3.innerHTML = data[i].unit;
		td4.innerHTML = data[i].amount;
		td5.innerHTML = data[i].buyPrice;
		td6.innerHTML = data[i].amountIn;
	}
};

/**
 * 保存删除信息
 */
var saveDeleteInfo = function()
{
	var receiptNumber = document.getElementById("receiptNumber").value;// 收货单号
	var data = "receiptNumber=" + receiptNumber;
	var url = "/FuneralManageSystem/deleteReceipt!saveDeleteInfo";
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